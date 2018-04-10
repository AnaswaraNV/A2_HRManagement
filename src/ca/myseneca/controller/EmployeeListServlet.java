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
 * @author Anaswara Naderi Vadakkeperatta
 * @author Jonathan Chik
 * 
 * This page is the servlet for getting employee list 
 * It based on the users choice. 
 * One option is to show all employees 
 * Other is to show employees based on department id
 */

@WebServlet("/ShowEmpList")
public class EmployeeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Employee> empList = null;

	public EmployeeListServlet() {

	}

	/* 
	 * @Param request
	 * @Param response
	 * Function to handle employee list based on users choice 
	 */
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
			

			// setting the user id as a session variable
			HttpSession Session_Emplist = request.getSession();
			Session_Emplist.setAttribute("empList", empList);
			
			getServletContext().getRequestDispatcher("/ShowEmployeesList.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			getServletContext().getRequestDispatcher("/errorpage.jsp").forward(request, response);
		}
	}

	
	/**
	 * Function validating department id 
	 * @param depId
	 * @return
	 */
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
			request.setAttribute("exception", e);
			getServletContext().getRequestDispatcher("/errorpage.jsp").forward(request, response);
		}
	}
}
