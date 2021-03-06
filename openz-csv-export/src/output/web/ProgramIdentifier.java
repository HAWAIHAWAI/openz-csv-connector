package output.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProgramIdentifier
 */
@WebServlet(description = "Returns the name of the program to enable validation at import side", urlPatterns = { "/ProgramIdentifier" })
public class ProgramIdentifier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the HttpServlet implementation.
	 * @see HttpServlet#HttpServlet()
	 */
	public ProgramIdentifier() {
		super();
	}

	/**
	 * Name of the program as GET-response.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.write("openz-csv-export");
		out.flush();
		out.close();
	}

	/**
	 * Name of the program as POST-response.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
