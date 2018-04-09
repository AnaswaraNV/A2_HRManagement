<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="ca.myseneca.model.*" %>
<%@ page import="ca.myseneca.dataaccess.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="styles/main.css" rel="stylesheet" type="text/css">
<title>Update Employee</title>
</head>
<body>
	<div class="container">
		<%@ include file="navbar.jsp" %>
		<br/> <br/>

		<h1>Update Employee</h1>

		<p>Update an existing employee's information</p>

		<% 
		   	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		   	
			long empId = Long.parseLong(request.getParameter("ID"));
			Employee emp = null;
			
			List<Employee> employeeList = DataAccess.getAllEmployees();
			if (employeeList != null) {
				for (Employee e : employeeList) {
					if (e.getEmployeeId() == empId) {
						emp = e;
						break;
					}
				}
			}			
		 %>

		<form action="UpdateEmployee" method="post">
			<div class="form-group">
				<label class="new-emp-label">Employee ID</label> <input class="form-control" name="employeeId" type="number" value="<%=emp.getEmployeeId()%>" readonly/> 
				<br/> 
				<label class="new-emp-label">First Name</label> <input class="form-control" name="firstname" type="text" value="<%=emp.getFirstName()%>" required/> 
				<br/>
				<label class="new-emp-label">Last Name</label> <input class="form-control" name="lastname" type="text" value="<%=emp.getLastName()%>" required/> 
				<br/> 
				<label class="new-emp-label">Email</label> <input class="form-control" name="email" type="text" value="<%=emp.getEmail()%>" required/> 
				<br/> 
				<label class="new-emp-label">Phone Number</label> <input class="form-control" name="phoneNumber" value="<%=emp.getPhoneNumber()%>" type="text" required/>
				<br/> 
				<label class="new-emp-label">Hire Date</label> <input class="form-control" name="hireDate" type="text" value="<%=df.format(emp.getHireDate())%>" required/> 
				<br/> 
				<label class="new-emp-label">Job ID</label>
				<select name="jobId" class="form-control select-box" required>
					<%
						List<String> jobList = DataAccess.getAllJobNames();
						
						for (String job : jobList) {
							if (job.equals(emp.getJobId())) {
								out.print("<option value='" + job + "' selected>"+ job + "</option>");
							} else {
								out.print("<option value='" + job + "' selected>"+ job + "</option>");
							}
						}
					 %>			
				</select>
				<br/> 
				<label class="new-emp-label">Salary</label> <input class="form-control" name="salary" type="number" min="0" value="<%=emp.getSalary()%>" required/> 
				<br/> 
				<label class="new-emp-label">Commission Pct</label> <input class="form-control" name="commissionPct" type="number" min="0" max="1" step="0.01" value="<%=emp.getCommissionPct()%>" required/>
				<br/>
				<label class="new-emp-label">Manager ID:</label> <input class="form-control" name="managerId" type="number" value="<%=emp.getManagerId()%>" required/> 
				<br/> 
				<label class="new-emp-label">Department</label>
				<select name="department" class="form-control select-box" required>
					<%
						List<String> departmentList = DataAccess.getAllDepartmentNames();
						
						for (String dep : departmentList) {
							if (dep.equals(emp.getDepartment().getDepartmentName())) {
								out.print("<option value='" + dep + "' selected>"+ dep + "</option>");
							} else {
								out.print("<option value='" + dep + "'>"+ dep + "</option>");
							}
						}
					 %>			
				</select>
				<br/> <br/>
				<input class="btn btn-secondary" name="updateButton" type="submit" value="Update Employee"/>
				<input class="btn btn-secondary" name="deleteButton" type="submit" value="Delete Employee"
					   onClick="return confirm('Are you sure you want to delete?')"/>
				<br/> <br/>
				<% String message = " " ;
					message = (String) request.getParameter("message");
				%>
				<p style="color:red">${message}</p>
				<br/>
				<input class="btn btn-secondary" type="button" value="Return" onclick="window.history.back()">
				<br/> <br/> <br/>
				<input class="form-control" name="message" type="text" value="" style="visibility: hidden;"/>
			
			</div>
		</form>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>