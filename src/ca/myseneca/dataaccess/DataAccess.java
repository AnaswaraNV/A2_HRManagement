/**
 * @author Anaswara 
 * this page fetch the credential from thedb  
 */
package ca.myseneca.dataaccess;

import javax.persistence.*;
import java.util.*;
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
		String secId = credentials.getSecId();
		String secPwd = credentials.getSecPassword();
		
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

		return isValidCredential;
	}
	
	/**
	 * Set the entity managet object using createEntity manager method
	 */
	public static void getEmf() {
		// Create the EntityManager
		emf = Persistence.createEntityManagerFactory("JPAtesting");
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

		try {
			// get the named query
			TypedQuery<Security> query = em.createNamedQuery("Security.findValid", Security.class);

			validLogins = query.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeEntityManager();
		}
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
			
			try {
				// get the named query
				TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll", Employee.class);

				employeeList = query.getResultList();

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				closeEntityManager();
			}
			
			return employeeList;
		}
		
		// Get Employees by Department - PreparedStatement
		/*
		 * Gets the Employees that are in a specific Department in the Database
		 * 
		 * @param depid the Department ID to be searched
		 * 
		 * @return an ArrayList of Employees in the corresponding Department
		 */
		/*public static ArrayList<Employee> getEmployeesByDepartmentID(int depid) {
			employeeList.clear();
			getEmf();
			
			try {
				// get the named query
				TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll", Employee.class);

				employeeList = (ArrayList<Employee>) query.getResultList();

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				closeEntityManager();
			}
			
			return employeeList;
			employeeList.clear();
			if (connection != null) {
				try {
					ResultSet resultSet = null;

					String query = "SELECT * FROM EMPLOYEES WHERE DEPARTMENT_ID = ? ORDER BY EMPLOYEE_ID";

					//prepare the statement and set all the values
					pstatement = connection.prepareStatement(query);
					pstatement.setInt(1, depid);

					resultSet = pstatement.executeQuery();

					while (resultSet.next()) {
						Employee e = new Employee(resultSet.getInt("EMPLOYEE_ID"), resultSet.getString("FIRST_NAME"),
								resultSet.getString("LAST_NAME"), resultSet.getString("EMAIL"),
								resultSet.getString("PHONE_NUMBER"), resultSet.getString("HIRE_DATE"),
								resultSet.getString("JOB_ID"), resultSet.getDouble("SALARY"),
								resultSet.getDouble("COMMISSION_PCT"), resultSet.getInt("MANAGER_ID"),
								resultSet.getInt("DEPARTMENT_ID"));
						employeeList.add(e);
					}
				} catch (SQLException e) {
					DBUtil.printSQLException(e);
				}
			}

			return employeeList;
		}*/
}
