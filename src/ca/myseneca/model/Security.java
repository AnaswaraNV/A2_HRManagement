package ca.myseneca.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SECURITY database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Security.findAll", query="SELECT s FROM Security s") , 
@NamedQuery(name="Security.findValid", query="SELECT s FROM Security s WHERE s.secStatus = 'A'")
})
public class Security implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="EMPLOYEE_ID", nullable=false, length=30,
    updatable=false, insertable=false)
	private long employeeId;

	@Column(name="SEC_ID")
	private String secId;

	@Column(name="SEC_PASSWORD")
	private String secPassword;

	@Column(name="SEC_STATUS")
	private String secStatus;

	//bi-directional one-to-one association to Employee
	@OneToOne
	@JoinColumn(name="EMPLOYEE_ID")
	private Employee employee;

	public Security() {
	}

	public long getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getSecId() {
		return this.secId;
	}

	public void setSecId(String secId) {
		this.secId = secId;
	}

	public String getSecPassword() {
		return this.secPassword;
	}

	public void setSecPassword(String secPassword) {
		this.secPassword = secPassword;
	}

	public String getSecStatus() {
		return this.secStatus;
	}

	public void setSecStatus(String secStatus) {
		this.secStatus = secStatus;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}