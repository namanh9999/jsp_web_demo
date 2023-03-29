package model;

public class Product{
	
	private String productID;
	private String productName;
	private Author author;
	private int publishYear;
	private double cost;
	private double price;
	private int quantity;
	private Type type;
	private Enum<EnumClass.Language> language;
	private String description;
	
	
	
	public Product() {
	}
	public Product(String productID, String productName, Author author, int publishYear, double cost, double price,
			int quantity, Type type, Enum<EnumClass.Language> language, String description) {
		this.productID = productID;
		this.productName = productName;
		this.author = author;
		this.publishYear = publishYear;
		this.cost = cost;
		this.price = price;
		this.quantity = quantity;
		this.type = type;
		this.language = language;
		this.description = description;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public int getPublishYear() {
		return publishYear;
	}
	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Enum<EnumClass.Language> getLanguage() {
		return language;
	}
	public void setLanguage(EnumClass.Language language) {
		this.language = language;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", author=" + author
				+ ", publishYear=" + publishYear + ", cost=" + cost + ", price=" + price + ", quantity=" + quantity
				+ ", type=" + type + ", language=" + language + ", description=" + description + "]";
	}
	
	
}