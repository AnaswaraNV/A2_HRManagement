package ca.myseneca.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ca.myseneca.dataaccess.DataAccess;
import ca.myseneca.model.Department;
import ca.myseneca.model.Employee;

/*
 * 
 */

@WebServlet("/UpdateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String message = " ";
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
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/UpdateEmployee.jsp");
					PrintWriter out = response.getWriter();
					out.println("<p style=\"color:red;\">Please select a Job ID");
					rd.include(request, response);
					return;
				}
				
				// Validate Department - Department Unselected Error
				if (departmentString.equals("default")) {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/UpdateEmployee.jsp");
					PrintWriter out = response.getWriter();
					out.println("<p style=\"color:red;\">Please select a Department");
					rd.include(request, response);
					return;
				}
								
				// Validate Manager ID - Manager Non-Exist Error
				if (!checkEmployeeExists(managerId)) {
					//message = "Manager Id does not exist!";
					//request.setAttribute("message", message);
				    RequestDispatcher rd = getServletContext().getRequestDispatcher("/UpdateEmployee.jsp");
					PrintWriter out = response.getWriter();
					out.println("<p style=\"color:red;\">Manager ID does not exist.");
					rd.include(request, response);
					return;
				}
				
				Department department = null;
				List<Department> departmentList = DataAccess.getDepartmentsByName(departmentString);
				if (departmentList != null) {
					department = departmentList.get(0);
				}
				
				Employee employee = new Employee(employeeId, firstname, lastname, email, 
												 phoneNumber, hireDate, jobId, salary, 
												 commissionPct, managerId, department);
				
				if (request.getParameter("updateButton") != null) {
					int pass = DataAccess.updateEmployee(employee);
					
					request.setAttribute("pass", pass);				
					
					request.setAttribute("employee", employee);
					if (pass > 0) { 
						//if success update the employee list session attribute
						updateEmployeeList(request);
						getServletContext().getRequestDispatcher("/Confirmation.jsp").forward(request, response);
					}
					else {

						String message = "Updation not successful!";
						request.setAttribute("message", message);
						getServletContext().getRequestDispatcher("/UpdateEmployee.jsp").forward(request, response);
					}
					
					// getServletContext().getRequestDispatcher("/ShowEmployeesList.jsp").forward(request, response);
				} else if (request.getParameter("deleteButton") != null) {
					int pass = DataAccess.deleteEmployee(employee);
					
					request.setAttribute("pass", pass);				
					
					request.setAttribute("employee", employee);
					if (pass > 0) { 
						//if success update the employee list session attribute
						updateEmployeeList(request);
						getServletContext().getRequestDispatcher("/Confirmation.jsp").forward(request, response);
					}
					else {

						String message = "Deletion not successful!";
						request.setAttribute("message", message);
						getServletContext().getRequestDispatcher("/UpdateEmployee.jsp").forward(request, response);
					}
				}			
			} catch (NumberFormatException e) {
				//Invalid Type Error
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/UpdateEmployee.jsp");
				PrintWriter out = response.getWriter();
				out.println("<p style=\"color:red;\">Input Invalid. Please try again.</p>");
				rd.include(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("exception", e);
			getServletContext().getRequestDispatcher("/errorpage.jsp").forward(request, response);
					
		}
	}
	
	
	/*
	 * 
	 * @param request
	 */
	private void updateEmployeeList(HttpServletRequest request) {
		List<Employee> employeeList = DataAccess.getAllEmployees();
		if (employeeList != null) {	
			//updating the employee list session attribute after retrieving it
			HttpSession Session_emplist = request.getSession();
		   Session_emplist.setAttribute("empList", employeeList);
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
			response.sendRedirect("errorpage.jsp");
		}
	}

}
