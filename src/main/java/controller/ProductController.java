package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.CategoriesDao;
import dao.ProductDao;
import model.Categories;
import model.Product;

/**
 * Servlet implementation class ProductController
 */
@MultipartConfig
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
		}

		String action = request.getParameter("action");
		if(action.equals("showAllProduct")) {
			selectAllProduct(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
		}

		String action = request.getParameter("action");
		if (action.equals("addProduct")) {
			addProduct(request, response);
			writeMainVideo(request, response);
		}else if(action.equals("showAllProduct")) {
			selectAllProduct(request, response);
		}
	}

	private void selectAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url  = "";
		HttpSession session = request.getSession();
		ArrayList<Product> list = ProductDao.getInstance().selectAll();
		session.setAttribute("allProduct", list);
		url = "/Admin/AdminManager/ProductManager.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "";
		String e_file = "";
		try {
			HttpSession session = request.getSession();
			String productID = request.getParameter("productID").trim();
			String productName = ProductDao.getInstance()
					.capitalizeAllFirstLetters(request.getParameter("productName").trim());
			System.out.println("product name : " + productName);
			String author = ProductDao.getInstance().capitalizeAllFirstLetters(request.getParameter("author").trim());
			Date publishYear = Date.valueOf(request.getParameter("publishYear"));
			double cost = Double.valueOf(request.getParameter("cost"));
			double price = Double.valueOf(request.getParameter("price"));
			int quantity = Integer.valueOf(request.getParameter("quantity"));
			String type = request.getParameter("type").trim();
			Categories ct = CategoriesDao.getInstance().selectByName(type);
			String country = request.getParameter("country").trim();
			String language = request.getParameter("language").trim();
			String description = request.getParameter("description").trim();
			Part film = request.getPart("path");
			// BeanUtils.populate(pd, request.getParameterMap());
			String setMain = request.getParameter("setMainVideo");
			System.out.println("set Main  : " + setMain);

			String folder = getServletContext().getRealPath("/") + "Product/Path";
			System.out.println("This is the real path : " + folder);
			String fileName = Path.of(film.getSubmittedFileName()).getFileName().toString();
			String filePath = folder + "/" + fileName;
			
			Product pd = new Product(productID, productName, author, publishYear, cost, price, quantity, ct, language,
					country, description, fileName);
			if (ProductDao.getInstance().checkFilmName(productName)) {
				session.setAttribute("Product", pd);
				request.setAttribute("e_productName", "Film's name already exist");
				request.setAttribute("setMain", setMain);
				url = "/Product/Futher.jsp";
			} else {
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				if (isMultipart) {
					if (isMp4File(fileName)) {
						ProductDao.getInstance().insert(pd);
						film.write(filePath);
					} else {
						e_file = "File must be a mp4 file ! Please select again";

						request.setAttribute("e_file", e_file);
						request.setAttribute("Product", pd);
						url = "/Product/Futher.jsp";
					}
				} else {
					e_file = "File is not multipart file";
					request.setAttribute("e_file", e_file);
					request.setAttribute("Product", pd);
					url = "/Product/Futher.jsp";
				}
				url = "/Admin/index.jsp";
			}
			// check and add mainVideo
			writeMainVideo(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			url = "/Product/Futher.jsp";
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/*
	 * private void uploadFilm(Product pd, Part part, HttpServletRequest request,
	 * String setMainVideo, String url) throws IOException { String e_file = "";
	 * String folder = getServletContext().getRealPath("Path"); String fileName =
	 * Path.of(part.getSubmittedFileName()).getFileName().toString(); boolean
	 * isMultipart = ServletFileUpload.isMultipartContent(request); String filePath
	 * = folder + "/" + fileName; File file = new File(filePath); if (isMultipart ==
	 * true && isMp4File(file) == true) { ProductDao.getInstance().insert(pd);
	 * part.write(filePath); System.out.println("insert successfully"); if
	 * (setMainVideo.equals("on")) { folder =
	 * getServletContext().getRealPath("MainVideo"); filePath = folder + "/" +
	 * fileName; part.write(filePath);
	 * System.out.println("Write main video successfully"); } } else { e_file =
	 * "File is not mp4 file"; request.setAttribute("e_file", e_file); url =
	 * "/Product/Futher.jsp"; } }
	 */
	private boolean isMp4File(String fileName) {
		boolean result = false;
		if (fileName.endsWith(".mp4")) {
			result = true;
		}
		return result;
	}

	private boolean writeMainVideo(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		boolean result = false;
		String setMain = request.getParameter("setMainVideo");
		if (!(setMain == null)) {
			Part film = request.getPart("path");
			System.out.println("this is part 2" + film);
			String folder2 = getServletContext().getRealPath("/") + "Product/MainVideo";
			System.out.println(folder2);
			String filePath2 = folder2 + "/" + "mainVideo.mp4";

			System.out.println("filePath2 " + folder2);
			File file = new File(filePath2);
			if (file.exists()) {
				file.delete();
			}
			ProductDao.getInstance().updateMainVideo("mainVideo.mp4");
			film.write(filePath2);
			result = true;
		}
		return result;
	}
}