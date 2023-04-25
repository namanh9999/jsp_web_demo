package model;

import java.sql.Date;

public class Product {

	private String productID;
	private String productName;
	private String author;
	private Date publishYear;
	private double cost;
	private double price;
	private int quantity;
	private String type;
	private String country;
	private String language;
	private String description;
	private String path;
	public Product() {
	}

	public Product(String productID, String productName, String author, Date publishYear, double cost, double price,
			int quantity, String type, String language, String country, String description,
			String path) {
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
		this.country = country;
		this.path = path;

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(Date publishYear) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", author=" + author
				+ ", publishYear=" + publishYear + ", cost=" + cost + ", price=" + price + ", quantity=" + quantity
				+ ", type=" + type + ", country=" + country + ", language=" + language + ", description=" + description
				+ ", path=" + path + "]";
	}

}