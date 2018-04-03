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
	
	private static List<Department> departmentList = new ArrayList<Department>();
	private static List<Employee> employeeList = new ArrayList<Employee>();


	/*
	 * 
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

	/*
	 * Gets all the Departments in the Database
	 * @return a list of Departments that are in the Database
	 */
	public static List<Department> getAllDepartments() {
		departmentList.clear();
		
		getEmf();
		
		TypedQuery<Department> query = em.createNamedQuery("Department.findAll", Department.class);
		
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
		getEmf();
		
		List<Department> dep = null;
		
		TypedQuery<Department> query = em.createQuery("SELECT d FROM Department d WHERE d.departmentName = :depName", Department.class);
		query.setParameter("depName", departmentName);
		
		dep = query.getResultList();
		
		closeEntityManager();
		
		return dep;
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
	 * @param depid the Department ID to be searched
	 * @return an List of Employees in the corresponding Department
	 */
	public static List<Employee> getEmployeesByDepartmentId(int depId) {

		employeeList.clear();

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

	/*
	 * 
	 */
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
