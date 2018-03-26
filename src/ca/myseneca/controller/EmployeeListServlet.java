package ca.myseneca.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

import ca.myseneca.dataaccess.DataAccess;
import ca.myseneca.model.Employee;


@WebServlet("/ShowEmpListPage")
public class EmployeeListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Employee> empList = null;
	public EmployeeListServlet() {
		// TODO Auto-generated constructor stub
	}
	    @Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		
	        if (request.getParameter("allEmpButton") != null) {

	        	//Get all employees 	
	            empList  = DataAccess.getAllEmployees();
	            
	            
	        } else if (request.getParameter("empByIDButton") != null) {
	        	
	           // daObject.getEmpByDepartmentID;
	    
	        }// else {
	            // ???
	        //}
	        
	        System.out.println("######test");
	        System.out.println(empList.toString());
	     // set employee list attribute to the employee list page 
            request.setAttribute("employeeList", empList );
            getServletContext().getRequestDispatcher("/ShowEmployeesList.jsp")
	        .forward(request, response);
            
           
 
            //System.out.println("hows" + data_rtrvd);
	    }

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
	    		throws ServletException, IOException {
	        doPost(request, response);
	    } 
	}
