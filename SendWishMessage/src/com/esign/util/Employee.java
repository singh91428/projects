package com.esign.util;

import java.util.Date;

public class Employee {
	private Integer eid;
	private String ename;
	private String eemail;
	private Date dob;
	private Integer status;
	private Date annidate;
	
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEemail() {
		return eemail;
	}
	public void setEemail(String eemail) {
		this.eemail = eemail;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getAnnidate() {
		return annidate;
	}
	public void setAnnidate(Date annidate) {
		this.annidate = annidate;
	}
	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", eemail=" + eemail + ", dob=" + dob + ", status="
				+ status + ", annidate=" + annidate + "]";
	}
	
}
