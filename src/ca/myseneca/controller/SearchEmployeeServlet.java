package ca.myseneca.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ca.myseneca.dataaccess.DataAccess;
import ca.myseneca.model.Employee;

/*
 * @author Anaswara Naderi Vadakkeperatta
 * @author Jonathan Chik
 * 
 * This page is the servlet for search employees based on user input 
 * Search for Employee 
 * in any part of a name, email address, phone number or department:  
 */

@WebServlet("/SearchEmployee")
public class SearchEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Employee> empList = null;

	/*
	 * Method to handle the search function to search for an Employee
	 * based on name, email, address, phone number, or department
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// need to get the search value from user and proceed further
			String searchString = request.getParameter("search");

			if (searchString != null) {
				// call the data access layer to pass the search string
				empList = DataAccess.searchEmployee(searchString);
			}
			
			if (empList != null) {
				request.setAttribute("employeeList", empList);
				getServletContext().getRequestDispatcher("/SearchEmployeeResult.jsp").forward(request, response);
				// response.sendRedirect("LoginSuccess.jsp");
			}
		} catch (Exception e) {
			request.setAttribute("exception", e);
			getServletContext().getRequestDispatcher("/errorpage.jsp").forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doPost(request, response);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			getServletContext().getRequestDispatcher("/errorpage.jsp").forward(request, response);
		}
	}

}
