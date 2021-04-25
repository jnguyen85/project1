<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file = "/template/header.jsp" %>


	<header>
	 
	  <div class="p-5 text-center bg-light">
	    <h1 class="mb-3">Employees</h1>
	  </div>
	  
	</header>
	
	<div class="container">
		<a class="btn btn-primary" href="<%=request.getContextPath()%>/EmployeeControllerServlet?command=ADD_EMPLOYEE_PAGE" role="button">Add New Employee</a>
		<h1></h1>
		
		<table class="table table-hover table-fixed">
			<thead>
			  <tr>
			    <th>First Name</th>
			    <th>Last Name</th>
			    <th>Email</th>
			    <th>Role</th>
			    <th>Edit</th>
			    <th>Delete</th>
			  </tr>
			</thead>
			<tbody>
				<c:forEach var="employee" items="${EMPLOYEES_LIST}">
					<!-- set up a link for each student TO UPDATE-->
					<c:url var="updatelink" value="EmployeeControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="employeeId" value="${employee.employeeId }" />
					</c:url>
					
					<!-- set up a link for each student TO DELETE-->
					<c:url var="deleteLink" value="EmployeeControllerServlet">
						<c:param name="command" value="DELETE_EMPLOYEE" />
						<c:param name="employeeId" value="${employee.employeeId }" />
					</c:url>
					
					<tr class="table-info">
						<th scope="row">${employee.employeeFirstName}</th>
						<td>${employee.employeeLastName}</td>
						<td>${employee.employeeEmail}</td>
						<td>${employee.employeeRole}</td>
						
						<td>
							<a 
								href="${updatelink}" 
							>
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</a>
						</td>
						<td>
							<a 
								href="${deleteLink}" 
								onclick="if (!(confirm('Are yo sure you want to delete this employee?'))) return false;"
							>
								<span class="glyphicon glyphicon-remove" aria-hidden="true" style="color: red;" ></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a class="btn btn-primary" href="<%=request.getContextPath()%>/EmployeeControllerServlet?command=ADD_EMPLOYEE_PAGE" role="button">Add New Employee</a>
		
<%@ include file = "/template/footer.jsp" %>