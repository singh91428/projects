package com.esign.test;
import java.util.Date;
import java.util.TimerTask;

import com.esign.util.Employee;

public class Testing extends TimerTask
{
	private String to;
	private String name;
	private String sub;
	private String body;
	private String filename;
	public Testing(String to,String name,String sub,String body,Employee e,String filename) {
		this.to=to;
		this.name=name;
		this.sub=sub+" "+this.name;
		this.body=body;
		this.filename=filename;
	}
	public void run()
	{
		System.out.println("RUN EXECUTED");
                GMailServer sender = new GMailServer(Constants.setFrom, Constants.setPassword);

	            try {
			    sender.sendMail(this.sub,this.name,this.body,Constants.setFrom,this.to,this.filename);
			    System.out.println(this.to);
			}
                   catch (Exception e) {
			     e.printStackTrace();
			}  

				System.out.println("Email Sent Succesfully...");

	        }
}
