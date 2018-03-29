/**
 * @author Anaswara 
 * 
 * This page is the servlet for getting user name and password 
 * It will pass the credentials to the Security db program 
 * and validate the credentials 
 */
package ca.myseneca.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import ca.myseneca.dataaccess.DataAccess;
import ca.myseneca.model.Security;

@WebServlet("/EmployeeListPage")
public class SignInServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	try {
     	// get request parameters for email and password
        String Id = request.getParameter("Id");
        String pwd = request.getParameter("password");

        // store data in User object
        Security credentials = new Security();
        credentials.setSecId(Id);
        credentials.setSecPassword(pwd);

        // check the email and password from database
        boolean pass = DataAccess.validateCredentials(credentials);

        if (pass) {
        	// set User object in request object and and forward to LoginSuccess.jsp page
            request.setAttribute("credentials", credentials );
        
            //setting the user id as a session variable
            String userId = credentials.getSecId();  
            HttpSession Session_UserId=request.getSession();  
            Session_UserId.setAttribute("userId",userId);  

            getServletContext().getRequestDispatcher("/EmployeeList.jsp")
            .forward(request, response);
                          

        } else {
        	
        	//If login not successful, show an error 
        	RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            PrintWriter out= response.getWriter();
            out.println("<p style=\"color:red;\">Either email or password is wrong. "
            		+ "Please try again.</p>");
            rd.include(request, response);
            //rd.forward(request, response);
        }
        
	}catch(Exception e) {
		response.sendRedirect("errorpage.jsp");
	}

	}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
    		throws ServletException, IOException {
    	try { 
    		doPost(request, response);
    	}catch(Exception e) {
    		response.sendRedirect("errorpage.jsp");
    	}
    } 
}
