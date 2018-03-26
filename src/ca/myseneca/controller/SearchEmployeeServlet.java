package ca.myseneca.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ca.myseneca.dataaccess.DataAccess;
import ca.myseneca.model.Security;

@WebServlet("/SearchEmployeePage")
public class SearchEmployeeServlet extends HttpServlet {

	/**
	 *  to remove warning
	 */
	private static final long serialVersionUID = 1L;

	public SearchEmployeeServlet() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
     	// get request parameters for email and password
        String Id = request.getParameter("search");

        // store data in User object
        Security credentials = new Security();
        credentials.setSecId(Id);

        // check the email and password from database
        boolean pass = DataAccess.validateCredentials(credentials);

        if (pass) {
        	// set User object in request object and and forward to LoginSuccess.jsp page
            request.setAttribute("credentials", credentials );
        
            getServletContext().getRequestDispatcher("/SearchEmployeeResult.jsp")
            .forward(request, response);
            //response.sendRedirect("LoginSuccess.jsp");
            
            //If user is valid , we have to set the username as an application level 
            //variable , then only user name can be retrieved in all the jsp pages 
            ServletContext application = getServletConfig().getServletContext();  

            String userId = credentials.getSecId();  
            application.setAttribute("userId", userId);  

        } else {
        	
        	//If login not successful, show an error 
        	RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            PrintWriter out= response.getWriter();
            out.println("<p style=\"color:red;\">Either email or password is wrong. "
            		+ "Please try again.</p>");
            rd.include(request, response);
            //rd.forward(request, response);
        }
        
    

	}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
    		throws ServletException, IOException {
        doPost(request, response);
    } 

}
