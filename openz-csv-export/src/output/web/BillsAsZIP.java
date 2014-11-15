package output.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import output.BillHandler;

/**
 * Servlet implementation class UpdateCSVDirectory
 */
@WebServlet(description = "Update files in the CSV directory", urlPatterns = { "/Bills.zip" })
public class BillsAsZIP extends HttpServlet {
	
	BillHandler billHandler;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8164342217548336731L;

	/**
     * Default constructor. 
     */
    public BillsAsZIP() {
    	billHandler = new BillHandler();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     System.out.println("Get executed");
		// Set response content type
		 //update folder
		 
		//update folder
		String message = "";
		try {
			updateFolder();
		} catch (IOException e) {
			message = e.getLocalizedMessage();
			System.out.println(message);
		}
	      response.setContentType("application/zip");

	      File file = updateFolder();
	      System.out.println("File retrieved is located at: " + file.toString());
	      FileInputStream fileIn = new FileInputStream(file);
	      ServletOutputStream out = response.getOutputStream();
	       
	      byte[] outputByte = new byte[4096];
	      //copy binary contect to output stream
	      while(fileIn.read(outputByte, 0, 4096) != -1)
	      {
	      	out.write(outputByte, 0, 4096);
	      }
	      fileIn.close();
	      out.flush();
	      out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post executed");
		doGet(request,response);
	}
	
	private File updateFolder() throws IOException{
		return billHandler.flushCSV();
	}
}
