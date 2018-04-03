<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="ca.myseneca.model.*" %>
<%@ page import="ca.myseneca.dataaccess.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="styles/main.css" rel="stylesheet" type="text/css">
<title>New Employee</title>
</head>
<body>
	<div class="container">
		<%@ include file="navbar.jsp" %>
		<br/> <br/>

		<h1>New Employee</h1>

		<p>Create a new employee</p>

		<form action="NewEmployee" method="post">
			<div class="form-group">
				<label class="new-emp-label">Employee ID:</label> <input class="form-control" name="employeeId" type="text" required/> 
				<br/> 
				<label class="new-emp-label">First Name:</label> <input class="form-control" name="firstname" type="text" required/> 
				<br/>
				<label class="new-emp-label">Last Name:</label> <input class="form-control" name="lastname" type="text" required/> 
				<br/> 
				<label class="new-emp-label">Email:</label> <input class="form-control" name="email" type="text" required/> 
				<br/> 
				<label class="new-emp-label">Phone Number:</label> <input class="form-control" name="phoneNumber" type="text" placeholder="000.000.0000" required/>
				<br/> 
				<label class="new-emp-label">Hire Date:</label> <input class="form-control" name="hireDate" type="text" placeholder="yyyy-mm-dd" required/> 
				<br/> 
				<label class="new-emp-label">Job ID:</label> <input class="form-control" name="jobId" type="text" required/> 
				<br/> 
				<label class="new-emp-label">Salary:</label> <input class="form-control" name="salary" type="text" required/> 
				<br/> 
				<label class="new-emp-label">Commission Pct:</label> <input class="form-control" name="commissionPct" type="text" required/>
				<br/>
				<label class="new-emp-label">Manager ID:</label> <input class="form-control" name="managerId" type="text" required/> 
				<br/> 
				<label class="new-emp-label">Department:</label>
				<select name="department" class="form-control select-box">
					<option value="default"></option>
					<%
						List<Department> departmentList = DataAccess.getAllDepartments();
						
						for (Department dep : departmentList) {
							out.print("<option value='" + dep.getDepartmentName() + "'>"+ dep.getDepartmentName() + "</option>");
						}
					 %>			
				</select>
				<br/> <br/>
				<input class="btn btn-secondary" name="createbutton" type="submit" value="Create New Employee"/>
				<input class="btn btn-secondary" name="clearButton" type="reset" value="Clear"/>
				<br/> <br/> <br/>
			</div>
		</form>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>