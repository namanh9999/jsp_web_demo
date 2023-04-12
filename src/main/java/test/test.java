package test;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.AuthorDao;
import dao.CustomerDao;
import dao.OrderDao;
import dao.ProductDao;
import model.Author;
import model.Customer;
import model.EnumClass;
import model.Order;
import model.Product;

public class test {

	public static  String getLastTime() {
		String date = "";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		   LocalDateTime now = LocalDateTime.now();
		   System.out.println(dtf.format(now));
		return date; 
	}
	
	public static void main(String[] args) {
		getLastTime();
	}
}
