<%@ page import="ca.myseneca.model.*" %>
<%@ page import="ca.myseneca.dataaccess.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
				<label class="new-emp-label">Employee ID</label> <input class="form-control" name="employeeId" type="text" value="<%=emp.getEmployeeId()%>" readonly/> 
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
				<label class="new-emp-label">Job ID</label> <input class="form-control" name="jobId" type="text" value="<%=emp.getJobId()%>" required/> 
				<br/> 
				<label class="new-emp-label">Salary</label> <input class="form-control" name="salary" type="text" value="<%=emp.getSalary()%>" required/> 
				<br/> 
				<label class="new-emp-label">Commission Pct</label> <input class="form-control" name="commissionPct" type="text" value="<%=emp.getCommissionPct()%>" required/>
				<br/>
				<label class="new-emp-label">Manager ID:</label> <input class="form-control" name="managerId" type="text" value="<%=emp.getManagerId()%>" required/> 
				<br/> 
				<label class="new-emp-label">Department</label>
				<select name="department" class="form-control select-box">
					<%
						List<Department> departmentList = DataAccess.getAllDepartments();
						
						for (Department dep : departmentList) {
							if (dep.getDepartmentId() == emp.getDepartment().getDepartmentId()) {
								out.print("<option value='" + dep.getDepartmentName() + "' selected>"+ dep.getDepartmentName() + "</option>");
							} else {
								out.print("<option value='" + dep.getDepartmentName() + "'>"+ dep.getDepartmentName() + "</option>");
							}
						}
					 %>			
				</select>
				<br/> <br/>
				<input class="btn btn-secondary" type="button" value="Return" onclick="window.history.back()">
				<br/> <br/> <br/>
			</div>
		</form>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
