package ca.myseneca.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SearchEmployeePage")
public class SearchEmployeeServlet extends HttpServlet {

	/**
	 *  to remove warning
	 */
	private static final long serialVersionUID = 1L;

	public SearchEmployeeServlet() {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
     	// need to get the search value from user and proceed further 
        String Id = request.getParameter("search");

        // check the email and password from database
       // boolean pass = DataAccess.(credentials);

       // if (pass) {
        	// set User object in request object and and forward to LoginSuccess.jsp page
           // request.setAttribute("credentials", credentials );
        
            getServletContext().getRequestDispatcher("/SearchEmployeeResult.jsp")
            .forward(request, response);
            //response.sendRedirect("LoginSuccess.jsp");
            

        //} else {
        	
        	//If login not successful, show an error 
        	RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            PrintWriter out= response.getWriter();
            out.println("<p style=\"color:red;\">Either email or password is wrong. "
            		+ "Please try again.</p>");
            rd.include(request, response);
            //rd.forward(request, response);
        }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
    		throws ServletException, IOException {
        doPost(request, response);
    } 

}
