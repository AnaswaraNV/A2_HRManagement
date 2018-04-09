package ca.myseneca.dataaccess;

import javax.persistence.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.myseneca.model.*;

/*
 * 
 */

public class DataAccess {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	Scanner keyboard = new Scanner(System.in);

	/*
	 * Set the entity manager object using createEntity manager method
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
	
	/*
	 * Checks if the username and password entered are valid
	 * @param credentials the Security object made from username and password
	 * @return true or false depending if the username and password match
	 */
	public static boolean validateCredentials(Security credentials) {
		boolean isValidCredential = false; 

		List<Security> validLogins = null;
		
		validLogins = queryValidLogins();
		closeEntityManager();
		
		String secId = credentials.getSecId();
		String secPwd = credentials.getSecPassword();
		
		if(validLogins != null) { 
			for (Iterator<Security> loginIterator = validLogins.iterator(); loginIterator.hasNext(); ) {
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

	/*
	 * Get the details from security db for active login
	 */
	private static List<Security> queryValidLogins() {
		getEmf();

		//list of valid logins
		List<Security> validLogins = null;

			// get the named query
			TypedQuery<Security> query = em.createNamedQuery("Security.findValid", Security.class);

			validLogins = query.getResultList();
			
		return validLogins;
	}

	/*
	 * Gets all the Departments in the Database
	 * @return a list of Departments that are in the Database
	 */
	public static List<Department> getAllDepartments() {
		List<Department> departmentList = new ArrayList<Department>();
		
		getEmf();
		
		TypedQuery<Department> query = em.createNamedQuery("Department.findAll", Department.class);
		
		departmentList = query.getResultList();
		
		closeEntityManager();
		
		return departmentList;
	}
	
	/*
	 * Gets all the Department Names in the Database
	 * @return a list of Department Names
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getAllDepartmentNames() {
		List<String> departmentList = new ArrayList<String>();
		
		getEmf();
		
		Query query = em.createQuery("SELECT DISTINCT d.departmentName FROM Department d ORDER BY d.departmentName");
		departmentList = query.getResultList();
		
		closeEntityManager();
		
		return departmentList;
	}
	
	/*
	 * Get the Departments that matches a Department name given
	 * @param departmentName the Department name to search for
	 * @return a list of Departments that match the name
	 */
	public static List<Department> getDepartmentsByName(String departmentName) {
		List<Department> departmentList = new ArrayList<Department>();
		
		getEmf();
		
		TypedQuery<Department> query = em.createQuery("SELECT d FROM Department d WHERE d.departmentName = :depName", Department.class);
		query.setParameter("depName", departmentName);
		
		departmentList = query.getResultList();
		
		closeEntityManager();
		
		return departmentList;
	}
	
	/*
	 * Adds an Employee to the Database
	 * @param emp the Employee object to add
	 * @return true or false depending if successful
	 */
	public static boolean addEmployee(Employee emp) {
		getEmf();
		
		em.getTransaction().begin();
		
		em.persist(emp);
		em.getTransaction().commit();
		
		closeEntityManager();
		
		return true;
	}
	
	/*
	 * Updates existing Employee information
	 * @param emp the Employee object to update
	 * @return the number of rows that were affected by the Update
	 */
	public static int updateEmployee(Employee emp) {
		getEmf();
		
		em.getTransaction().begin();
		
		Query query = em.createQuery("UPDATE Employee e SET e.firstName = :firstname, e.lastName = :lastname, e.email = :email, e.phoneNumber = :phone, e.hireDate = :hireDate, e.jobId = :jobId, e.salary = :salary, e.commissionPct = :commPct, e.managerId = :managerId WHERE e.employeeId = :empId");
		query.setParameter("firstname", emp.getFirstName());
		query.setParameter("lastname", emp.getLastName());
		query.setParameter("email", emp.getEmail());
		query.setParameter("phone", emp.getPhoneNumber());
		query.setParameter("hireDate", emp.getHireDate());
		query.setParameter("jobId", emp.getJobId());
		query.setParameter("salary", emp.getSalary());
		query.setParameter("commPct", emp.getCommissionPct());
		query.setParameter("managerId", emp.getManagerId());
		//query.setParameter("depId", emp.getDepartment().getDepartmentId());
		query.setParameter("empId", emp.getEmployeeId());
		int count = query.executeUpdate();
		
		em.getTransaction().commit();
		
		closeEntityManager();
		
		return count;
	}
	
	/*
	 * Deletes an Employee from the Database
	 * @param emp the Employee object to delete
	 * @return the number of rows that were affected by the Delete
	 */
	public static int deleteEmployee(Employee emp) {
		getEmf();
		
		em.getTransaction().begin();
		
		Query query = em.createQuery("DELETE FROM Employee e WHERE e.employeeId = :empId");
		query.setParameter("empId", emp.getEmployeeId());
		int count = query.executeUpdate();
		
		em.getTransaction().commit();
		
		closeEntityManager();
		
		return count;
	}
	
	// Get All Employees - Statement
	/*
	 * Gets all Employees in the Database
	 * @return an ArrayList of Employees in the Database
	 */
	public static List<Employee> getAllEmployees() {
		List<Employee> employeeList = new ArrayList<Employee>();

		getEmf();

		// get the named query
		TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll", Employee.class);

		employeeList = query.getResultList();
		closeEntityManager();
			
		return employeeList;
	}

	/*
	 * Get all Employee IDs existing in the Database
	 * @return a list of Employee IDs
	 */
	@SuppressWarnings("unchecked")
	public static List<Long> getAllEmployeeIds() {
		List<Long> idList = new ArrayList<Long>();
		
		getEmf();
		
		Query query = em.createQuery("SELECT DISTINCT e.employeeId FROM Employee e");
		idList = query.getResultList();
		
		return idList;
	}
	
	/*
	 * Gets all the Job IDs 
	 * @return a list of Job IDs
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getAllJobNames() {
		List<String> jobList = new ArrayList<String>();
		
		getEmf();
		
		Query query = em.createQuery("SELECT DISTINCT e.jobId FROM Employee e ORDER BY e.jobId");
		jobList = query.getResultList();
		
		return jobList;
	}
	
	// Get Employees by Department - PreparedStatement
	/*
	 * Gets the Employees that are in a specific Department in the Database
	 * @param depid the Department ID to be searched
	 * @return an List of Employees in the corresponding Department
	 */
	public static List<Employee> getEmployeesByDepartmentId(int depId) {
		List<Employee> employeeList = new ArrayList<Employee>();

		//getting entity manager
		getEmf();

		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e INNER JOIN e.department d WHERE d.departmentId = :depId ORDER BY e.employeeId", Employee.class);
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

	/*
	 * Get the details from security Database for active login
	 * @param searchString 
	 */
	public static List<Employee> searchEmployee(String searchString) {
		List<Employee> employeeList = new ArrayList<Employee>();
		List<Employee> empList = new ArrayList<Employee>();
		
		getEmf();
		
		TypedQuery<Employee> query = em.createQuery(
				"SELECT e FROM Employee e JOIN e.department d", Employee.class);
				
		employeeList = query.getResultList();
		
		for (Iterator<Employee> employeeIterator = employeeList.iterator();
				employeeIterator.hasNext();) {
				Employee singleEmployee = employeeIterator.next();
				//StringBuilder sb = new StringBuilder();
				//sb.append(".*\b");
				//sb.append(searchString);
				//sb.append("\b.*");
				boolean valid = RegExValidation(singleEmployee, searchString.toUpperCase());
				
				if (valid) {
					empList.add(singleEmployee);
				}
		 }

		closeEntityManager();
		
		return empList;
	}

	/*
	 * 
	 */
	private static boolean RegExValidation(Employee singleEmployee, String searchString) {
		boolean isFound = false;
		Pattern p = Pattern.compile(searchString);
		
		//getting name, email address, phone number, department
		StringBuilder employeeDetails = new StringBuilder();
		employeeDetails.append(singleEmployee.getFirstName() + " ");
		employeeDetails.append(singleEmployee.getLastName() + " ");
		employeeDetails.append(singleEmployee.getEmail() + " ");
		employeeDetails.append(singleEmployee.getPhoneNumber() + " ");
		employeeDetails.append(singleEmployee.getDepartment().getDepartmentId());		
		
		// creating matcher object
		Matcher m = p.matcher(employeeDetails.toString().toUpperCase());
		if (m.find( )) {
			isFound = true;
		}
		
		return isFound;
	}

}
