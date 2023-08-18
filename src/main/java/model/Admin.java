package model;

import java.sql.Date;

public class Admin {
	private String adminID;
	private String userName;
	private String fullName;
	private Date dayOfBirth;
	private String address;
	private String password;
	private String emailRegister;
	private String phoneNumber;
	private String creator;
	private Date dateCreated;
	private Date lastLoginDate;
	private int failed;
	private boolean valid;

	public Admin() {
	}

	

	public Admin(String adminID, String userName, String fullName, Date dayOfBirth, String address, String password,
			String emailRegister, String phoneNumber, String creator, Date dateCreated, Date lastLoginDate, int failed,
			boolean valid) {
		this.adminID = adminID;
		this.userName = userName;
		this.fullName = fullName;
		this.dayOfBirth = dayOfBirth;
		this.address = address;
		this.password = password;
		this.emailRegister = emailRegister;
		this.phoneNumber = phoneNumber;
		this.creator = creator;
		this.dateCreated = dateCreated;
		this.lastLoginDate = lastLoginDate;
		this.failed = failed;
		this.valid = valid;
	}



	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public Date getDayOfBirth() {
		return dayOfBirth;
	}



	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
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

	public String getEmailRegister() {
		return emailRegister;
	}

	public void setEmailRegister(String emailRegister) {
		this.emailRegister = emailRegister;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public int getFailed() {
		return failed;
	}

	public void setFailed(int failed) {
		this.failed = failed;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}



}
