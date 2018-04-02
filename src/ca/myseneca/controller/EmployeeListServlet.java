package ca.myseneca.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import java.util.regex.Pattern;

import ca.myseneca.dataaccess.DataAccess;
import ca.myseneca.model.Employee;

/*
 * 
 */

@WebServlet("/ShowEmpList")
public class EmployeeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Employee> empList = null;

	public EmployeeListServlet() {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getParameter("allEmpButton") != null) {
				// Get all employees
				empList = DataAccess.getAllEmployees();
			} else if (request.getParameter("empByIDButton") != null) {
				// getting the id from user input
				String depId = request.getParameter("depId");
				int depId1 = depIdValidation(depId);
				// Get employees by department id
				empList = DataAccess.getEmployeesByDepartmentId(depId1);
			}
			
			// System.out.println("######test");
			// System.out.println(empList.toString());
			// set employee list attribute to the employee list page
			request.setAttribute("employeeList", empList);
			getServletContext().getRequestDispatcher("/ShowEmployeesList.jsp").forward(request, response);
		} catch (Exception e) {
			response.sendRedirect("errorpage.jsp");
		}
		// System.out.println("hows" + data_rtrvd);
	}

	private int depIdValidation(String depId) {
		int departmentID = 0;

		if (depId != null) {
			boolean valid = Pattern.matches("[\\d]{2,}", depId);
			if (valid) {
				departmentID = Integer.parseInt(depId);
			}
		}

		return departmentID;
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
