<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<title>Confirmation Page</title>
</head>
<body>
	<div class="container">
		<%@ include file="navbar.jsp" %>
		<br/> <br/>

		<h1>Confirmation Page</h1>
		<p>Following info has been updated/deleted</p>

		<% 
		   	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		   	Employee emp = null;
			//long empId = Long.parseLong(request.getParameter("ID"));
			int pass = (int)request.getAttribute("pass");
			if (pass > 0) {
			 emp = (Employee) request.getAttribute("employee");
			} 
				
		 %>

		<form>
			<div class="form-group">
				<label class="new-emp-label">Employee ID</label> <input class="form-control" name="employeeId" type="number" value="<%=emp.getEmployeeId()%>" readonly/> 
				<br/> 
				<label class="new-emp-label">First Name</label> <input class="form-control" name="firstname" type="text" value="<%=emp.getFirstName()%>" readonly/> 
				<br/>
				<label class="new-emp-label">Last Name</label> <input class="form-control" name="lastname" type="text" value="<%=emp.getLastName()%>" readonly/> 
				<br/> 
				<label class="new-emp-label">Email</label> <input class="form-control" name="email" type="text" value="<%=emp.getEmail()%>" readonly/> 
				<br/> 
				<label class="new-emp-label">Phone Number</label> <input class="form-control" name="phoneNumber" value="<%=emp.getPhoneNumber()%>" type="text" readonly/>
				<br/> 
				<label class="new-emp-label">Hire Date</label> <input class="form-control" name="hireDate" type="text" value="<%=df.format(emp.getHireDate())%>" readonly/> 
				<br/> 
				<label class="new-emp-label">Job ID</label>
				<select name="jobId" class="form-control select-box" disabled>
					<option value="default" disabled selected></option>
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
				<label class="new-emp-label">Salary</label> <input class="form-control" name="salary" type="text" value="<%=emp.getSalary()%>" readonly/> 
				<br/> 
				<label class="new-emp-label">Commission Pct</label> <input class="form-control" name="commissionPct" type="text" value="<%=emp.getCommissionPct()%>" readonly/>
				<br/>
				<label class="new-emp-label">Manager ID:</label> <input class="form-control" name="managerId" type="number" value="<%=emp.getManagerId()%>" readonly/> 
				<br/> 
				<label class="new-emp-label">Department</label>
				<select name="department" class="form-control select-box" disabled>
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
				<a href="ShowEmployeesList.jsp" class="btn btn-secondary">Return</a>
				<br/> <br/> <br/>
			</div>
		</form>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
