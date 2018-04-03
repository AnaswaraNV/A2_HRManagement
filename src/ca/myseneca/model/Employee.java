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
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e ORDER BY e.employeeId")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMPLOYEE_ID")
	private long employeeId;

	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "HIRE_DATE")
	private Date hireDate;

	@Column(name = "JOB_ID")
	private String jobId;

	@Column(name = "SALARY")
	private BigDecimal salary;

	@Column(name = "COMMISSION_PCT")
	private BigDecimal commissionPct;
	
	@Column(name = "MANAGER_ID")
	private long managerId;
	
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
	 * 11 Argument Constructor for Employee
	 * @param employeeId the ID of the Employee
	 * @param firstname the First Name of the Employee
	 * @param lastname the Last Name of the Employee
	 * @param email the Email of the Employee
	 * @param phoneNumber the Phone Number of the Employee
	 * @param hireDate the Hire Date of the Employee
	 * @param jobId the Job ID for the Employee
	 * @param salary the Salary of the Employee
	 * @param commissionPct the Commission PCT of the Employee
	 * @param managerId the Manager ID of the Employee
	 */
	public Employee(long employeeId, String firstName, String lastName, String email, String phoneNumber, Date hireDate, String jobId, BigDecimal salary, BigDecimal commissionPct, long managerId,  Department department) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.jobId = jobId;
		this.salary = salary;
		this.commissionPct = commissionPct;
		this.managerId = managerId;
		this.department = department;
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
	 * Gets the Manager ID of the Employee
	 * @return the Manager Id
	 */
	public long getManagerId() {
		return this.managerId;
	}

	/*
	 * Sets the Manager Id to the given value
	 * @param managerId the new Manager Id to be set
	 */
	public void setManagerId(long managerId) {
		this.managerId = managerId;
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
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", jobId=" + jobId + ", salary="
				+ salary + ", commissionPct=" + commissionPct + ", managerId=" + managerId + ", department="
				+ department + ", security=" + security + "]";
	}

}