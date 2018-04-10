package ca.myseneca.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.myseneca.dataaccess.DataAccess;
import ca.myseneca.model.Department;
import ca.myseneca.model.Employee;
/*
 * @author Anaswara Naderi Vadakkeperatta
 * @author Jonathan Chik
 * 
 * This page is the servlet for the employee creation 
 * It will get the users input and validate the inputs
 * If valid, it will create an employee and call the database 
 * program to insert the employee. 
 */

@WebServlet("/NewEmployee")
public class NewEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*
	 * Method to handle the creation of a new Employee
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {				
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			
			String employeeIdString = request.getParameter("employeeId");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String hireDateString = request.getParameter("hireDate");
			String jobId = request.getParameter("jobId");
			String salaryString = request.getParameter("salary");
			String commissionPctString = request.getParameter("commissionPct");
			String managerIdString = request.getParameter("managerId");
			String departmentString = request.getParameter("department");
			
			try {
				long employeeId = Long.parseLong(employeeIdString);
				Date hireDate = dateformat.parse(hireDateString);
				BigDecimal salary = new BigDecimal(salaryString);	
				BigDecimal commissionPct = new BigDecimal(commissionPctString);	
				long managerId = Long.parseLong(managerIdString);
				
				//Validate Job - Job ID Unselected Error
				if (jobId.equals("default")) {				
					String text = "Please select a Job ID.";
					request.setAttribute("type", "error");
					request.setAttribute("message", text);
					getServletContext().getRequestDispatcher("/NewEmployee.jsp").forward(request, response);
					return;
				}
				
				// Validate Department - Department Unselected Error
				if (departmentString.equals("default")) {					
					String text = "Please select a Department.";
					request.setAttribute("type", "error");
					request.setAttribute("message", text);
					getServletContext().getRequestDispatcher("/NewEmployee.jsp").forward(request, response);
					return;
				}
				
				// Validate Employee ID - Employee Exists Error
				if (checkEmployeeExists(employeeId)) {					
					String text = "Employee ID already exists.";
					request.setAttribute("type", "error");
					request.setAttribute("message", text);
					getServletContext().getRequestDispatcher("/NewEmployee.jsp").forward(request, response);
					return;
				}
								
				// Validate Manager ID - Manager Non-Exist Error
				if (!checkEmployeeExists(managerId)) {					
					String text = "Manager ID does not exist.";
					request.setAttribute("type", "error");
					request.setAttribute("message", text);
					getServletContext().getRequestDispatcher("/NewEmployee.jsp").forward(request, response);
					return;
				}
				
				// Create the Department object to associate to the Employee
				Department department = null;
				List<Department> departmentList = DataAccess.getDepartmentsByName(departmentString);
				if (departmentList != null) {
					department = departmentList.get(0);
				}
				
				Employee employee = new Employee(employeeId, firstname, lastname, email, 
												 phoneNumber, hireDate, jobId, salary, 
												 commissionPct, managerId, department);
				
				boolean pass = DataAccess.addEmployee(employee);
				
				// Check if Database successful in adding
				if (pass) {					
					String text = "Employee successfully added.";
					request.setAttribute("type", "success");
					request.setAttribute("message", text);
					getServletContext().getRequestDispatcher("/NewEmployee.jsp").forward(request, response);
				} else {					
					String text = "Employee was not added. Please try again.";
					request.setAttribute("type", "error");
					request.setAttribute("message", text);
					getServletContext().getRequestDispatcher("/NewEmployee.jsp").forward(request, response);
					return;
				}
			} catch (NumberFormatException e) {
				//Invalid Type Error				
				String text = " Invalid. Please try again.";
				request.setAttribute("type", "error");
				request.setAttribute("message", text);
				getServletContext().getRequestDispatcher("/NewEmployee.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			request.setAttribute("exception", e);
			getServletContext().getRequestDispatcher("/errorpage.jsp").forward(request, response);
		}
	}
	
	/*
	 * Checks if an Employee exists in the Database
	 * @param empId the Employee ID to check
	 * @return true or false depending if the Employee exists
	 */
	private boolean checkEmployeeExists(long empId) {
		List<Long> idList = DataAccess.getAllEmployeeIds();
		
		for (Long id : idList) {
			if (id == empId) {
				return true;
			}
		}
		
		return false;
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
