<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<a class="navbar-brand" href="EmployeeList.jsp"> 
		<img id="HRMLogo" src="images/hrmlogo.png" alt="Logo">
	</a>
	<ul class="nav navbar-nav">
		<li class="nav-item">
			<a class="nav-link" href="EmployeeList.jsp">Employee List</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="NewEmployee.jsp">New Employee</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="SearchEmployee.jsp">Search Employee</a>
		</li>
	</ul>
	<div class="ml-auto">
		<%
			//receiving the global user Id
			//Retrieving session attribute
			String userId = null;
			HttpSession Session_UserId = request.getSession(false);
			if (Session_UserId != null) {
				userId = (String) Session_UserId.getAttribute("userId");
			}
		%>
		<label class="user-label"> <% out.print("\t" + userId); %> </label> 
		<a href="Logout.jsp"> 
			<img id="logout-icon" src="images/logout.jpg" alt="logout">
		</a>
	</div>
</nav>