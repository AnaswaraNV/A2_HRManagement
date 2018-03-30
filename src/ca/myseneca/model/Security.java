package ca.myseneca.model;

import java.io.Serializable;
import javax.persistence.*;

/*
 * @author Anaswara Naderi Vadakkeperatta
 * @author Jonathan Chik
 * 
 * The persistent class for the SECURITY database table.
 */

@Entity
@NamedQueries({
	@NamedQuery(name="Security.findAll", query="SELECT s FROM Security s") , 
	@NamedQuery(name="Security.findValid", query="SELECT s FROM Security s WHERE s.secStatus = 'A'")
})
public class Security implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMPLOYEE_ID", nullable = false, length = 30, updatable = false, insertable = false)
	private long employeeId;

	@Column(name = "SEC_ID")
	private String secId;

	@Column(name = "SEC_PASSWORD")
	private String secPassword;

	@Column(name = "SEC_STATUS")
	private String secStatus;

	// Bidirectional one-to-one association to Employee
	@OneToOne
	@JoinColumn(name = "EMPLOYEE_ID")
	private Employee employee;

	
	/*
	 * Default Constructor for Security
	 */
	public Security() {
	}

	/*
	 * Gets the Employee Id associated with the Security
	 * @return the Employee Id
	 */
	public long getEmployeeId() {
		return this.employeeId;
	}
	
	/*
	 * Sets the Employee Id associated with the Security
	 * @param employeeId the new Employee Id to be set
	 */
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	/*
	 * Gets the Security Id of the Security
	 * @return the Security Id
	 */
	public String getSecId() {
		return this.secId;
	}

	/*
	 * Sets the Security Id to the given value
	 * @param secId the new Security Id to be set
	 */
	public void setSecId(String secId) {
		this.secId = secId;
	}

	/*
	 * Gets the Security Password of the Security
	 * @return the Security Password
	 */
	public String getSecPassword() {
		return this.secPassword;
	}

	/*
	 * Sets the Security Password to the given value
	 * @param secPassword the new Security Password to be set
	 */
	public void setSecPassword(String secPassword) {
		this.secPassword = secPassword;
	}

	/*
	 * Gets the Security Status of the Security
	 * @return the Security Status
	 */
	public String getSecStatus() {
		return this.secStatus;
	}

	/*
	 * Sets the Security Status to the given value
	 * @param secStatus the new Security Status to be set
	 */
	public void setSecStatus(String secStatus) {
		this.secStatus = secStatus;
	}

	/*
	 * Gets the Employee associated to the Security
	 * @return the Employee
	 */
	public Employee getEmployee() {
		return this.employee;
	}

	/*
	 * Sets the Employee associated with the Security
	 * @param employee the new Employee associated to the Security 
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}