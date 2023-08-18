package model;

import java.sql.Date;

public class Order {
	 private String orderID;
	 private Customer customer;
	 private String buyAddress;
	 private String deliAddress;
	 private Enum<EnumClass.OrderStatus>  orderStatus;
	 private Enum<EnumClass.Payments> payments;
	 private Enum<EnumClass.PaymentStatus> paymentStatus;
	 private double amountPaid;
	 private double missingAmount;
	 private Date orderDate;
	 private Date shipDate;

	public Order() {

	}

	public Order(String orderID, Customer customer, String buyAddress, String deliAddress, Enum<EnumClass.OrderStatus> orderStatus,
			Enum<EnumClass.Payments> payments, Enum<EnumClass.PaymentStatus> paymentStatus, double amountPaid, double missingAmount, Date orderDate, Date shipDate) {
		this.orderID = orderID;
		this.customer = customer;
		this.buyAddress = buyAddress;
		this.deliAddress = deliAddress;
		this.orderStatus = orderStatus;
		this.payments = payments;
		this.paymentStatus = paymentStatus;
		this.amountPaid = amountPaid;
		this.missingAmount = missingAmount;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
	}


	 public  String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	 public  String getBuyAddress() {
		return buyAddress;
	}

	public void setBuyAddress(String buyAddress) {
		this.buyAddress = buyAddress;
	}

	 public  String getDeliAddress() {
		return deliAddress;
	}

	public void setDeliAddress(String deliAddress) {
		this.deliAddress = deliAddress;
	}

	 public  Enum<EnumClass.OrderStatus> getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(EnumClass.OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	 public  Enum<EnumClass.Payments> getPayments() {
		return payments;
	}

	public void setPayments(EnumClass.Payments payments) {
		this.payments = payments;
	}

	 public  Enum<EnumClass.PaymentStatus> getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(EnumClass.PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	 public  double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	 public  double getMissingAmount() {
		return missingAmount;
	}

	public void setMissingAmount(double missingAmount) {
		this.missingAmount = missingAmount;
	}

	 public  Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	 public  Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	@Override
	public String toString() {
		return "order [orderID=" + orderID + ", customer ID=" + customer.getCustomerID() + ", buyAddress=" + buyAddress + ", deliAddress="
				+ deliAddress + ", orderStatus=" + orderStatus + ", payments=" + payments + ", paymentStatus="
				+ paymentStatus + ", amountPaid=" + amountPaid + ", missingAmount=" + missingAmount + ", orderDate="
				+ orderDate + ", shipDate=" + shipDate + "]";
	}


		public static  String StringHanding(String value) {
		if (value.equals("Vietname")) {
			return "VN";
		} else if (value.equals("EngLish")) {
			return "EN";
		} else if (value.equals("Confirmed")) {
			return "Con";
		} else if (value.equals("Delivered")) {
			return "Del";
		} else if (value.equals("Pending")) {
			return "Pe";
		} else if (value.equals("Shipped")) {
			return "Sh";
		} else if (value.equals("Failed")) {
			return "Fai";
		} else if (value.equals("Pending_Payment")) {
			return "Pen";
		} else if (value.equals("Successful")) {
			return "Suc";
		} else if (value.equals("Credit_card")) {
			return "Cre";
		} else if (value.equals("Debit_card")) {
			return "Deb";
		} else if (value.equals("Google_Pay")) {
			return "Goog";
		}
		return "Pay";
	}


}
