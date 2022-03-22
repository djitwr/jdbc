package cn.stack.model;

import java.util.Date;

public class User {
	private int id;
	private String userName;
	private String password;
	private String phoneNo;
	private String sex;
	private Date regDate;
	private String picName;

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public User(int id, String userName, String password, String phoneNo, String sex, Date regDate, String picName) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.phoneNo = phoneNo;
		this.sex = sex;
		this.regDate = regDate;
		this.picName = picName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", phoneNo=" + phoneNo
				+ ", sex=" + sex + ", regDate=" + regDate + ", picName=" + picName + "]";
	}

	
}
