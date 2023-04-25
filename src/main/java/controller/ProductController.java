package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.ProductDao;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = getServletContext().getRequestDispatcher("Product/product.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
		}

		String action = request.getParameter("action");
		if (action.equals("addProduct")) {
			addProduct(request, response);
		}
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
			String country = request.getParameter("country").trim();
			String language = request.getParameter("language").trim();
			String description = request.getParameter("description").trim();
			Part film = request.getPart("path");
			System.out.println("this is part on part Film" + film);
			// BeanUtils.populate(pd, request.getParameterMap());
			String setMain = request.getParameter("setMainVideo");
			System.out.println(setMain);
			String folder = getServletContext().getRealPath("/") + "Product/Path";

			System.out.println("this is folder" + folder);

			String fileName = Path.of(film.getSubmittedFileName()).getFileName().toString();
			System.out.println("this is fileName" + fileName);

			String filePath = folder + "/" + fileName;
			System.out.println("this is filepath" + filePath);
			Product pd = new Product(productID, productName, author, publishYear, cost, price, quantity, type, language,
					country, description, fileName);
			if (ProductDao.getInstance().checkFilmName(productName) == true) {
				session.setAttribute("Product", pd);
				request.setAttribute("e_productName", "Film's name already exist");
				request.setAttribute("setMain", setMain);
				url = "/Product/Futher.jsp";
			} else {

				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				if (isMultipart == true) {
					if (isMp4File(fileName) == true) {
						ProductDao.getInstance().insert(pd);
						film.write(filePath);
						System.out.println("insert successfully");
					} else {
						e_file = "Not have to mp4";
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
				System.out.println("import success");
			}

			if (!(setMain == null)) {
				folder = getServletContext().getRealPath("/") + "Product/MainVideo";
				System.out.println("this is folder " + folder);
				System.out.println("this is file name " + fileName);
				String filePath2 = folder + "/" + "mainVideo.mp4";
				System.out.println("this is filePath for main video " + filePath2);
				File file = new File(filePath2);
				if (file.exists() == true) {
					file.delete();
				}
				ProductDao.getInstance().updateMainVideo(fileName);
				System.out.println("film : " + film);
				film.write(filePath2);
				System.out.println("Write main video successfully");
				url = "/Admin/index.jsp";
			}
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
}