<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="ca.myseneca.model.*" %>
<%@ page import="ca.myseneca.dataaccess.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page errorPage="errorpage.jsp" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">  -->
<link href="styles/bootstrap.css" rel="stylesheet" type="text/css">
<link href="styles/main.css" rel="stylesheet" type="text/css">
<title>Employee List</title>
</head>
<body>
	<div class="container">
		<%@ include file="navbar.jsp" %>
		<br/> <br/>
		
		<h1>Employee List</h1>
		<br/> <br/>
		
		<table class="fixed table-striped ">
			<tr>
				<th>Employee ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Phone Number</th>
				<th style="width: 101px; ">Hire Date</th>
				<th>Job ID</th>
				<th>Salary</th>
				<th>Commission Pct</th>
				<th>Manager ID</th>
				<th>Department ID</th>
			</tr>
			<%
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
				@SuppressWarnings("unchecked")
				//Retrieving session attribute
				List<Employee> employeeList = null;
				HttpSession Session_emplist = request.getSession(false);
				if (Session_emplist != null) {
					employeeList = (List<Employee>) Session_emplist.getAttribute("empList");
				}
				//List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
				if (employeeList != null) {
					for (Employee employee : employeeList) {
			%>
						<tr>
							<td><a href="UpdateEmployee.jsp?ID=<%=employee.getEmployeeId()%>"><%=employee.getEmployeeId()%></a></td>
							<td><%=employee.getFirstName()%></td>
							<td><%=employee.getLastName()%></td>
							<td><%=employee.getEmail()%></td>
							<td><%=employee.getPhoneNumber()%></td>
							<td><%=df.format(employee.getHireDate())%></td>
							<td><%=employee.getJobId()%></td>
							<td><%=employee.getSalary()%></td>
							<td><%=employee.getCommissionPct()%></td>
							<td><%=employee.getManagerId()%></td>
							<td>
								<%
									if (employee.getDepartment() != null) {
										out.print(employee.getDepartment().getDepartmentId());
									}
								%>
							</td>
						</tr>
			<%
					}
				}
			%>
		</table>

		<br/>
		<a href="EmployeeList.jsp" class="btn btn-secondary">Return</a>
		<br/> <br/> <br/>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>