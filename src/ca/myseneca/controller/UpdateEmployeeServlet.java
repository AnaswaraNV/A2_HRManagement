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
 * 
 */

@WebServlet("/UpdateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateEmployeeServlet() {

    }

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
			
			long employeeId = Long.parseLong(employeeIdString);
			Date hireDate = dateformat.parse(hireDateString);
			BigDecimal salary = new BigDecimal(salaryString);	
			BigDecimal commissionPct = new BigDecimal(commissionPctString);	
			long managerId = Long.parseLong(managerIdString);
			
			Department department = null;
			List<Department> departmentList = DataAccess.getDepartmentsByName(departmentString);
			if (departmentList != null) {
				department = departmentList.get(0);
			}
			
			Employee employee = new Employee(employeeId, firstname, lastname, email, phoneNumber, hireDate, jobId, salary, commissionPct, managerId, department);
			
			if (request.getParameter("updateButton") != null) {
				int pass = DataAccess.updateEmployee(employee);
				System.out.println(pass);
				System.out.println("here$########return status" + pass);
				
				request.setAttribute("pass", pass);				
				
				request.setAttribute("employee", employee);
				if (pass > 0) { 
					String message = "testing label!";
					request.setAttribute("message", message);
					getServletContext().getRequestDispatcher("/Confirmation.jsp").forward(request, response);
				}
				else {
					System.out.println("not page $$$$$$$$$$4");
					String message = "Updation not successful!";
					request.setAttribute("message", message);
					getServletContext().getRequestDispatcher("/UpdateEmployee.jsp").forward(request, response);
				}
				
				// getServletContext().getRequestDispatcher("/ShowEmployeesList.jsp").forward(request, response);
			} else if (request.getParameter("deleteButton") != null) {
				int pass = DataAccess.deleteEmployee(employee);
				System.out.println("here$########return status" + pass);
				
				request.setAttribute("pass", pass);				
				
				request.setAttribute("employee", employee);
				if (pass > 0) { 
					getServletContext().getRequestDispatcher("/Confirmation.jsp").forward(request, response);
				}
				else {
					System.out.println("not page $$$$$$$$$$4");
					String message = "Deletion not successful!";
					request.setAttribute("message", message);
					getServletContext().getRequestDispatcher("/UpdateEmployee.jsp").forward(request, response);
				}

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
			response.sendRedirect("errorpage.jsp");
		}
	}

}
