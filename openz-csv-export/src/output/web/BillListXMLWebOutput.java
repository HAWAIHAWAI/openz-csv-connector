package output.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import output.BillHandler;
import output.xml.BillList;

/**
 * Class for updating files in the CSV directory.
 * 
 * @author Christian Gl�ser
 *
 */
@WebServlet(description = "Update files in the CSV directory", urlPatterns = { "/Bills.xml" })
public class BillListXMLWebOutput extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3963294382704114429L;

	private BillHandler billHandler;

	public BillListXMLWebOutput() {
		billHandler = new BillHandler();
	}

	/**
	 * Converting Bill objects to XML for the GET-response.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.write(BillList.convertBillsToXML());
		out.flush();
		out.close();
	}

	/**
	 * Converting Bill objects to XML for the POST-response.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
