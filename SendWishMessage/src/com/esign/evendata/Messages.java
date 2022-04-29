package com.esign.evendata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.esign.dao.MysqlConnection;

public class Messages {
	private static Connection con;
	private static ResultSet rs;
	private static Statement st;
	private static String msg;
	public static String getBirthdayMessage() {
		try {
			int random=RandomTesting.getRandomno();
			con=MysqlConnection.getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select dobmsg from message where msgid="+random);
			if(rs.next()) {
				msg=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
	public static String getAnniversaryMessage() {
		try {
			int random=RandomTesting.getRandomno();
			con=MysqlConnection.getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select annimsg from message where msgid="+random);
			if(rs.next()) {
				msg=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
}
