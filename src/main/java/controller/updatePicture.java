package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.CustomerDao;
import model.Customer;

/**
 * Servlet implementation class updatePicture
 */
public class updatePicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatePicture() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession() ;
		
		String action = request.getParameter("action1");
		if(action.equals("avatar")) {
			Customer cm = CustomerDao.getInstance().selectByID(session.getAttribute("userID")+"");
			uploadPicture(cm , action, request, response);
		}
	}
	private boolean uploadPicture(Customer cm, String userID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result = false;
		String e_error = "";

		String url = "";
		// Get the folder to store uploaded files
		String folder = getServletContext().getRealPath("avatar");

		// Set maximum file size
		int maxFileSize = 5 * 1024;

		// Set maximum memory size
		int maxMemSize = 5 * 1024;

		// Check if the request contains multipart content
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		// Get the content type of the request
		String contentType = request.getContentType();

		try {
			// If the request contains multipart content
			if (isMultipart) {
				// Create a new disk file item factory
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(maxMemSize);

				// Create a new servlet file upload object
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(maxFileSize);

				// Parse the request and get the list of file items
				List<FileItem> fileItems = upload.parseRequest(request);

				// Loop through each file item
				for (FileItem fileItem : fileItems) {
					// If the file item is not a form field
					if (!fileItem.isFormField()) {
						// Get the file name
						String fileName = fileItem.getName();

						// If the file name is not empty
						if (fileName != null && !fileName.isEmpty()) {
							// Create a new file object with the file path
							String filePath = folder + "/" + fileName;
							File file = new File(filePath);

							// Check if the file type is an image
							if (isImage(file)) {
								// Write the file to the server
								fileItem.write(file);
								cm.setAvatarPath(filePath);
								CustomerDao.getInstance().avatarUpdate(cm, userID, filePath);
								result = true;
								System.out.println("Avatar is imported.");
								url = "/CustomerDirec/changeInfor";
							} else {
								e_error = "This is not an image file.";
								request.setAttribute("e_error", e_error);
								url = "/CustomerDirec/changeInfor";
							}
						}
					}
				}
			} else {
				System.out.println("This is not a multipart request.");
			}
		} catch (FileUploadException e) {
			System.out.println("File upload exception occurred: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO exception occurred: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
		return result;
	}

	private boolean isImage(File file) {
		boolean result = false;
		String mimeType = new MimetypesFileTypeMap().getContentType(file);
		if (mimeType.startsWith("image/")) {
			result = true;
		}
		return result;
	}

}
