package com.esign.evendata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.esign.dao.MysqlConnection;
import com.esign.util.Employee;

public class EmployeeData {
	public static int event;
	public static String dobmsg;
	public static String annimsg;
	private  static Connection con;
	private static ResultSet rs;
	private static PreparedStatement ps;
	
	//get Employee that having birthday today
	
	public static List<Employee> getEmployeeHavingDOBToday() {
		List<Employee> empHavingDob=new ArrayList<Employee>();
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		String ndf=sdf.format(date);
		con=MysqlConnection.getConnection();
		try {
			ps=con.prepareStatement("select * from employee where date_format(dob,'%m-%d')=date_format(?,'%m-%d')");
			ps.setString(1,ndf);
			rs=ps.executeQuery();
			while(rs.next()) {
				Employee emp=new Employee();
				event++;
				emp.setEid(rs.getInt(1));
				emp.setEname(rs.getString(2));
				emp.setEemail(rs.getString(3));
				emp.setDob(rs.getDate(4));
				emp.setAnnidate(rs.getDate(5));
				emp.setStatus(rs.getInt(6));
				empHavingDob.add(emp);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return empHavingDob;
	}
	
	public static List<Employee> getEmployeeHavingAnniversaryToday() {
		List<Employee> empHavingAnni=new ArrayList<Employee>();
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		String ndf=sdf.format(date);
		con=MysqlConnection.getConnection();
		try {
			ps=con.prepareStatement("select * from employee where date_format(annidate,'%m-%d')=date_format(?,'%m-%d')");
			ps.setString(1,ndf);
			rs=ps.executeQuery();
			while(rs.next()) {
				Employee emp=new Employee();
				event++;
				emp.setEid(rs.getInt(1));
				emp.setEname(rs.getString(2));
				emp.setEemail(rs.getString(3));
				emp.setDob(rs.getDate(4));
				emp.setAnnidate(rs.getDate(5));
				emp.setStatus(rs.getInt(6));
				empHavingAnni.add(emp);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return empHavingAnni;
	}
	
	
	public static void main(String[] args) {
		List<Employee> empdob=getEmployeeHavingDOBToday();
		List<Employee> empanni=getEmployeeHavingAnniversaryToday();
		
		for(Employee e:empdob) {
			System.out.println(e);
		}
		System.out.println();
		for(Employee e:empanni) {
			System.out.println(e);
		}
		
	}
}
