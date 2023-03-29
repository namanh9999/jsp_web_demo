package test;

import java.sql.Date;
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
	public static void main(String[] args) {
		Author a1 = new Author("03", "Vu Ba Trung Anh", new Date(2005 - 1900, 1, 28), "He is a machanic");
		ArrayList<Author> list = AuthorDao.getInstance().selectAll();
		for (Author a : list) {
			System.out.println(a.toString());
		}
		Customer cs = CustomerDao.getInstance().selectByID("KH01");
		Customer cs2 = CustomerDao.getInstance().selectByID("KH02");
		System.out.println(cs.toString());

		  Order od1 = new
		  Order("DH3",cs2,"Israel","VietNam",EnumClass.OrderStatus.Confirmed,
		  EnumClass.Payments.Credit_card, EnumClass.PaymentStatus.Successful, 2000.0,
		  0.0,new Date(2023-22-03),new Date(2023-10-04));
		  OrderDao.getInstance().insert(od1);
		
		//OrderDao.getInstance().remove(od1);

		ArrayList<Order> orderList = OrderDao.getInstance().selectAll();

		for (Order ord : orderList) {
			System.out.println(ord.toString());
		}
		
		ArrayList<Product> arr = ProductDao.getInstance().selectAll(); 
		for(Product pr : arr) {
			System.out.println(pr.toString());
		}
	}

}
