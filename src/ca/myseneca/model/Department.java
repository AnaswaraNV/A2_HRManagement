package ca.myseneca.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/*
 * @author Anaswara Naderi Vadakkeperatta
 * @author Jonathan Chik
 * 
 * The persistent class for the DEPARTMENTS database table.
 */

@Entity
@Table(name = "DEPARTMENTS")
@NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DEPARTMENT_ID")
	private long departmentId;

	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;

	@Column(name = "LOCATION_ID")
	private java.math.BigDecimal locationId;

	@Column(name = "MANAGER_ID")
	private java.math.BigDecimal managerId;

	// Bidirectional many-to-one association to Employee
	@OneToMany(mappedBy = "department")
	private List<Employee> employees;

	/*
	 * Default Constructor for Department
	 */
	public Department() {
	}

	/*
	 * Gets the Department Id of the Department
	 * @return the Department Id
	 */
	public long getDepartmentId() {
		return this.departmentId;
	}

	/*
	 * Sets the Department Id to the given value
	 * @param departmentId the new Department Id to be set
	 */
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	/*
	 * Gets the Department Name of the Department
	 * @return the Department Name
	 */
	public String getDepartmentName() {
		return this.departmentName;
	}

	/*
	 * Sets the Department Name to the given value
	 * @param departmentName the new Department Name to be set 
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/*
	 * Gets the Location Id of the Department
	 * @return the Location Id
	 */
	public java.math.BigDecimal getLocationId() {
		return this.locationId;
	}

	/*
	 * Sets the Location Id of the Department
	 * @param locationId the new Location Id to be set
	 */
	public void setLocationId(java.math.BigDecimal locationId) {
		this.locationId = locationId;
	}

	/*
	 * Gets the Manager Id of the Department
	 * @return the Manager Id
	 */
	public java.math.BigDecimal getManagerId() {
		return this.managerId;
	}

	/*
	 * Sets the Manager Id of the Department
	 * @param managerId the new Manager Id to be set
	 */
	public void setManagerId(java.math.BigDecimal managerId) {
		this.managerId = managerId;
	}

	/*
	 * Gets the Employees in the Department
	 * @return the Employee list
	 */
	public List<Employee> getEmployees() {
		return this.employees;
	}

	/*
	 * Sets the Employees in the Department
	 * @param employees the new Employee list to be set
	 */
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	/*
	 * Adds an Employee to the Employee list
	 * @param employee the Employee to be added to the list
	 * @return the Employee that was added to the Employee list
	 */
	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setDepartment(this);

		return employee;
	}

	/*
	 * Removes an Employee from the Employee list
	 * @param employee the Employee to be removed from the list
	 * @return the Employee that was removed from the list
	 */
	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setDepartment(null);

		return employee;
	}
	
	/* 
    * Displays the Department Information
	 */
	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", locationId="
				+ locationId + ", managerId=" + managerId + ", employees=" + employees + "]";
	}

}