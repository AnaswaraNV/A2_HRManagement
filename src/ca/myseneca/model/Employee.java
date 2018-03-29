package ca.myseneca.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/*
 * @author Anaswara Naderi Vadakkeperatta
 * @author Jonathan Chik
 * 
 * The persistent class for the EMPLOYEES database table.
 */

@Entity
@Table(name = "EMPLOYEES")
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMPLOYEE_ID")
	private long employeeId;

	@Column(name = "COMMISSION_PCT")
	private BigDecimal commissionPct;

	private String email;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Temporal(TemporalType.DATE)
	@Column(name = "HIRE_DATE")
	private Date hireDate;

	@Column(name = "JOB_ID")
	private String jobId;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "MANAGER_ID")
	private BigDecimal managerId;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	private BigDecimal salary;

	// Bidirectional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department department;

	// Bidirectional one-to-one association to Security
	@OneToOne(mappedBy = "employee")
	private Security security;

	@Transient
	private String fullName;
	
	
	/*
	 * Default Constructor for Employee
	 */
	public Employee() {
	}

	/*
	 * Gets the Employee Id of the Employee
	 * @return the Employee Id
	 */
	public long getEmployeeId() {
		return this.employeeId;
	}

	/*
	 * Sets the Employee Id to the given value
	 * @param employeeId the new Employee Id to be set
	 */
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	/*
	 * Gets the Commission PCT of the Employee
	 * @return the Commission PCT
	 */
	public BigDecimal getCommissionPct() {
		return this.commissionPct;
	}

	/*
	 * Sets the Commission PCT to the given value
	 * @param commissionPct the new Commission PCT to be set
	 */
	public void setCommissionPct(BigDecimal commissionPct) {
		this.commissionPct = commissionPct;
	}

	/*
	 * Gets the Email of the Employee
	 * @return the Email
	 */
	public String getEmail() {
		return this.email;
	}

	/*
	 * Sets the Email to the given value
	 * @param email the new Email to be set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * Gets the First Name of the Employee
	 * @return the First Name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/*
	 * Sets the First Name to the given value
	 * @param firstName the new First Name to be set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/*
	 * Gets the Hire Date of the Employee
	 * @return the Hire Date
	 */
	public Date getHireDate() {
		return this.hireDate;
	}

	/*
	 * Sets the Hire Date to the given value
	 * @param hireDate the new Hire Date to be set
	 */
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	/*
	 * Gets the Job ID of the Employee
	 * @return the Job Id
	 */
	public String getJobId() {
		return this.jobId;
	}

	/*
	 * Sets the Job Id to the given value
	 * @param jobId the new Job Id to be set
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/*
	 * Gets the Last Name of the Employee
	 * @return the Last Name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/*
	 * Sets the Last Name to the given value
	 * @param lastName the new Last Name to be set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/*
	 * Gets the Manager ID of the Employee
	 * @return the Manager Id
	 */
	public BigDecimal getManagerId() {
		return this.managerId;
	}

	/*
	 * Sets the Manager Id to the given value
	 * @param managerId the new Manager Id to be set
	 */
	public void setManagerId(BigDecimal managerId) {
		this.managerId = managerId;
	}

	/*
	 * Gets the Phone Number of the Employee
	 * @return the Phone Number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/*
	 * Sets the Phone Number to the given value
	 * @param phoneNumber the new Phone Number to be set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/*
	 * Gets the Salary of the Employee
	 * @return the Salary
	 */
	public BigDecimal getSalary() {
		return this.salary;
	}

	/*
	 * Sets the Salary to the given value
	 * @param salary the new Salary to be set
	 */
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	/*
	 * Gets the Department of the Employee
	 * @return the Department
	 */
	public Department getDepartment() {
		return this.department;
	}

	/*
	 * Sets the Department associated to the Employee 
	 * @param department the Department associated to the Employee 
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/*
	 * Gets the Security associated to the Employee
	 * @return the Security
	 */
	public Security getSecurity() {
		return this.security;
	}

	/*
	 * Sets the Security associated to the Employee 
	 * @param security the new Security associated to the Employee 
	 */
	public void setSecurity(Security security) {
		this.security = security;
	}

	/*
    * Displays the Employee Information
	 */
	@Override
	public String toString() {
		return "[" + employeeId +  " " + commissionPct + " " + email + " "
 				+ " " + firstName + " " + hireDate + " " + jobId + " " + lastName
				+ " "+ managerId + " " + phoneNumber + " " + salary + " "
				+ department + " "  + security + "]";
	}


}