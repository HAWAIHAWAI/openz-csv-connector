package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csv.BillHandler;

/**
 * Servlet implementation class UpdateCSVDirectory
 */
@WebServlet(description = "Update files in the CSV directory", urlPatterns = { "/UpdateCSVDirectory" })
public class UpdateCSVDirectory extends HttpServlet {
	
	BillHandler billHandler;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8164342217548336731L;

	/**
     * Default constructor. 
     */
    public UpdateCSVDirectory() {
    	billHandler = new BillHandler();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
		// Set response content type
		 //update folder
		 
		//update folder
		String message = "";
		try {
			updateFolder();
		    message = "Folder update successfulget";
		} catch (IOException e) {
			message = e.getLocalizedMessage();
		}
	      response.setContentType("text/html");

	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
	      out.println("<h1>"+message+"</h1>");
	      out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response content type
		//update folder
		 String message = "";
		try {
			updateFolder();
		    message = "Folder update successfulpost";
		} catch (IOException e) {
			message = e.getLocalizedMessage();
		}
		

	      response.setContentType("text/html");

	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
	      out.println("<h1>"+message+"</h1>");
	      out.flush();
	}
	
	private void updateFolder() throws IOException{
		billHandler.flushCSV();
	}
}
