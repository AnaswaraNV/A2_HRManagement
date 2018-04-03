package ca.myseneca.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ca.myseneca.dataaccess.DataAccess;
import ca.myseneca.model.Employee;

@WebServlet("/SearchEmployee")
public class SearchEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Employee> empList = null;

	public SearchEmployeeServlet() {

	}

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
			response.sendRedirect("errorpage.jsp");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doPost(request, response);
		} catch (Exception e) {
			response.sendRedirect("errorpage.jsp");
		}
	}

}
