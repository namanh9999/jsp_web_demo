package model;

import java.sql.Date;

public class Verify {

	private Customer customer;
	private Date recently ;
	private Date lastTime ;
	private String code;
	private boolean verify;
private String reason ;
	public Verify() {
	}
	public Verify(Customer customer, Date recently, Date lastTime, String code, boolean verify, String reason) {
		this.customer = customer;
		this.recently = recently;
		this.lastTime = lastTime;
		this.code = code;
		this.verify = verify;
		this.reason = reason;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getRecently() {
		return recently;
	}
	public void setRecently(Date recently) {
		this.recently = recently;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isVerify() {
		return verify;
	}
	public void setVerify(boolean verify) {
		this.verify = verify;
	}
	@Override
	public String toString() {
		return "Verify [customer=" + customer + ", recently=" + recently + ", lastTime=" + lastTime + ", code=" + code
				+ ", verify=" + verify + "]";
	}


}
