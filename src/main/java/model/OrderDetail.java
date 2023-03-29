package model;

public class OrderDetail {
	
	private String orDetailID;
	private Order order;
	private Product productID;
	private int quantity;
	private double price;
	private double discount;
	private double cost;
	private double tax;
	private double totalCost;
	
	
	public OrderDetail() {
	}
	public OrderDetail(String orDetailID, Order order, Product productID, int quantity, double price, double discount,
			double cost, double tax, double totalCost) {
		this.orDetailID = orDetailID;
		this.order = order;
		this.productID = productID;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
		this.cost = cost;
		this.tax = tax;
		this.totalCost = totalCost;
	}
	public String getOrDetailID() {
		return orDetailID;
	}
	public void setOrDetailID(String orDetailID) {
		this.orDetailID = orDetailID;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProductID() {
		return productID;
	}
	public void setProductID(Product productID) {
		this.productID = productID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	@Override
	public String toString() {
		return "OrderDetail [orDetailID=" + orDetailID + ", order=" + order + ", productID=" + productID + ", quantity="
				+ quantity + ", price=" + price + ", discount=" + discount + ", cost=" + cost + ", tax=" + tax
				+ ", totalCost=" + totalCost + "]";
	}
	
}
