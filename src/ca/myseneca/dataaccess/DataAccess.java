/**
 * @author Anaswara 
 * this page fetch the credential from thedb  
 */
package ca.myseneca.dataaccess;

import javax.persistence.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.myseneca.model.*;

public class DataAccess {


	private static EntityManagerFactory emf;
	private static EntityManager em;
	Scanner keyboard = new Scanner(System.in);
	private static List<Employee> employeeList = new ArrayList<Employee>();


	public static boolean validateCredentials(Security credentials) {
		boolean isValidCredential = false; 

		List<Security> validLogins = null;
		
		validLogins = queryValidLogins();
		closeEntityManager();
		
		String secId = credentials.getSecId();
		String secPwd = credentials.getSecPassword();
		
		if(validLogins != null) { 
			for (Iterator<Security> loginIterator = validLogins.iterator();
					loginIterator.hasNext();) {
				Security item = loginIterator.next();
				//System.out.println("item.getSecId() : " + item.getSecId());
				//System.out.println("item.getSecPassword() : " + item.getSecId());
	
				if (item.getSecId().equals(secId) 
						&& item.getSecPassword().equals(secPwd)
						&& item.getSecId() != null && !item.getSecId().isEmpty()
						&& item.getSecPassword() != null && !item.getSecPassword().isEmpty()) {
					isValidCredential = true;
				}
	
			}
		}

		return isValidCredential;
	}

	/**
	 * Set the entity managet object using createEntity manager method
	 */
	public static void getEmf() {
		// Create the EntityManager
		emf = Persistence.createEntityManagerFactory("HRManagement");
		em = emf.createEntityManager();
	}

	/*
	 * close the entity manager
	 */
	public static void closeEntityManager() {
		// Close the EntityManager
		em.close();
		emf.close();
	}

	/**
	 * Get the details from security db for active login
	 */
	private static List<Security> queryValidLogins() {

		//get entity manager
		getEmf();

		//list of valid logins
		List<Security> validLogins = null;

			// get the named query
			TypedQuery<Security> query = em.createNamedQuery("Security.findValid", Security.class);

			validLogins = query.getResultList();
			
		return validLogins;
	}

	// Get All Employees - Statement
	/*
	 * Gets all Employees in the Database
	 * 
	 * @return an ArrayList of Employees in the Database
	 */
	public static List<Employee> getAllEmployees() {

		employeeList.clear();

		getEmf();

			// get the named query
			TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll", Employee.class);

			employeeList = query.getResultList();
			closeEntityManager();
		return employeeList;
	}


	// Get Employees by Department - PreparedStatement
	/*
	 * Gets the Employees that are in a specific Department in the Database
	 * 
	 * @param depid the Department ID to be searched
	 * 
	 * @return an List of Employees in the corresponding Department
	 */
	public static List<Employee> getEmployeesByDepartmentId(int depId) {

		employeeList.clear();

		//getting entity manager
		getEmf();

			TypedQuery<Employee> query = em.createQuery(
					"SELECT e FROM Employee e INNER JOIN e.department d WHERE d.departmentId = :depId ", Employee.class);

			query.setParameter("depId", depId);

			// countries = query.setMaxResults(10).getResultList();
			employeeList = query.getResultList();

			closeEntityManager();
			
		return employeeList;
	}

	/*	StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("sales_tax");
		// set parameters
		storedProcedure.registerStoredProcedureParameter("subtotal", Double.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("tax", Double.class, ParameterMode.OUT);
		storedProcedure.setParameter("subtotal", 1f);
		// execute SP
		storedProcedure.execute();
		// get result
		Double tax = (Double)storedProcedure.getOutputParameterValue("tax");*/

	/**
	 * Get the details from security db for active login
	 * @param searchString 
	 */
	public static List<Employee> searchEmployee(String searchString) {

		employeeList.clear();
		List<Employee> empList = new ArrayList<Employee>();
		
		getEmf();
		
			TypedQuery<Employee> query = em.createQuery(
					"SELECT e FROM Employee e JOIN e.department d", Employee.class);
					
			employeeList = query.getResultList();
			
			for (Iterator<Employee> employeeIterator = employeeList.iterator();
					employeeIterator.hasNext();) {
					Employee singleEmployee = employeeIterator.next();
					boolean valid = RegExValidation(singleEmployee, searchString);
					
					if (valid) {
						empList.add(singleEmployee);
					}
			 }

			closeEntityManager();
		return empList;
	}

	private static boolean RegExValidation(Employee singleEmployee, String searchString) {
		boolean isFound = false;
		Pattern p = Pattern.compile((searchString));
		
	    // creating matcher object
	      Matcher m = p.matcher(singleEmployee.toString());
	      if (m.find( )) {
	    	 isFound = true;
	      }
		return isFound;
	}

}
