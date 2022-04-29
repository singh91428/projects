package com.esign.test;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import com.esign.evendata.EmployeeData;
import com.esign.evendata.Messages;
import com.esign.evendata.RandomTesting;
import com.esign.util.Employee;

public class DBScheduler
{
	public void callScheduler() throws Exception
	{

		System.out.println("Scheduler Starterd...");
		ReadPropertiesFile.readConfig();
		Timer timer = new Timer();
		List<Employee> empdob=EmployeeData.getEmployeeHavingDOBToday();
		for(Employee e:empdob) {
			int random=RandomTesting.getRandomno();
			//Testing.setValuesforBirthday(e);
			String filename="D:\\Javaprograms\\wishmsg\\Birthday\\"+random+".jpg";
			timer.scheduleAtFixedRate(new Testing(e.getEemail(),e.getEname(),"Happy Birthday",Messages.getBirthdayMessage(), e,filename), getTimePrecision(Constants.delay), getTimePrecision(Constants.timetoquery));	
		}
		List<Employee> empanni=EmployeeData.getEmployeeHavingAnniversaryToday();
		for(Employee e:empanni) {
			int random=RandomTesting.getRandomno();
			//Testing.setValuesforAnniversary(e);
			String filename="D:\\Javaprograms\\wishmsg\\Anniversary\\"+random+".jpg";
			timer.scheduleAtFixedRate(new Testing(e.getEemail(),e.getEname(),"Happy Anniversary",Messages.getAnniversaryMessage(), e,filename), getTimePrecision(Constants.delay), getTimePrecision(Constants.timetoquery));	
		}
	}

	public long getTimePrecision(String value) throws Exception
	{
		long  l = 0;
		String val="";
		try
		{
			if(value.endsWith("d") || value.endsWith("D"))
			{
				val  = value.substring(0,value.length()-1);
				l = Long.parseLong(val) * 24 * 60 * 60 * 1000;
			}

			else if(value.endsWith("h") || value.endsWith("H"))
			{

				val  = value.substring(0,value.length()-1);
				l = Long.parseLong(val) * 60 * 60 * 1000;

			}
			else if(value.endsWith("m") || value.endsWith("M"))
			{
				val  = value.substring(0,value.length()-1);
				l = Long.parseLong(val) * 60 * 1000;
			}
			else if(value.endsWith("s") || value.endsWith("S"))
			{

				val  = value.substring(0,value.length()-1);
				l = Long.parseLong(val) * 1000;
			}
			else
			{

				l = Long.parseLong(value);
			}

		}
		catch(Exception e)
		{

			throw new Exception(e);
		}

		return l;
	}
	public static void main(String a[]) throws Exception
	{
		DBScheduler dbs = new DBScheduler();
		dbs.callScheduler();
	}

}
