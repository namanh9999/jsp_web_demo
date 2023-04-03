package model;

import java.sql.Date;

public class Customer {
	private String customerID;
	private String userName;
	private String passWord;
	private String fullName;
	private String gender;
	private String address;
	private String deliAddress;
	private String shipAddress;
	private String buyAddress;
	private Date birth;
	private String phoneNumber;
	private String email;
	private boolean emailRegister;

	public Customer() {
	}


	public Customer(String customerID, String userName, String passWord, String fullName, String gender, String address,
			String deliAddress, String shipAddress, String buyAddress, Date birth, String phoneNumber, String email,
			boolean emailRegister) {
		this.customerID = customerID;
		this.userName = userName;
		this.passWord = passWord;
		this.fullName = fullName;
		this.gender = gender;
		this.address = address;
		this.deliAddress = deliAddress;
		this.shipAddress = shipAddress;
		this.buyAddress = buyAddress;
		this.birth = birth;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.emailRegister = emailRegister;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeliAddress() {
		return deliAddress;
	}

	public void setDeliAddress(String deliAddress) {
		this.deliAddress = deliAddress;
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public String getBuyAddress() {
		return buyAddress;
	}

	public void setBuyAddress(String buyAddress) {
		this.buyAddress = buyAddress;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailRegister() {
		return emailRegister;
	}

	public void setEmailRegister(boolean emailRegister) {
		this.emailRegister = emailRegister;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", userName=" + userName + ", passWord=" + passWord
				+ ", fullName=" + fullName + ", gender=" + gender + ", address=" + address + ", deliAddress=" + deliAddress
				+ ", shipAddress=" + shipAddress + ", buyAddress=" + buyAddress + ", birth=" + birth
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", emailRegister=" + emailRegister + "]";
	}

}
