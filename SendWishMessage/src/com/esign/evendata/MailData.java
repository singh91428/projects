package com.esign.evendata;

import java.util.List;

import com.esign.util.Employee;

public class MailData {
	public static String to;
	public static String name;
	public static String sub;
	public static String body;
	public static void dataForDateOfBirth() {
		List<Employee> empdob=EmployeeData.getEmployeeHavingDOBToday();
		for(Employee e:empdob) {
			to=e.getEemail();
			name=e.getEname();
			sub="Happy Birthday "+name;
			body=Messages.getBirthdayMessage();
		}
	}
	public static void dataForAnnivaersary() {
		List<Employee> empanni=EmployeeData.getEmployeeHavingAnniversaryToday();
		for(Employee e:empanni) {
			to=e.getEemail();
			name=e.getEname();
			sub="Happy Anniversary "+name;
			body=Messages.getAnniversaryMessage();
		}
	}
}
