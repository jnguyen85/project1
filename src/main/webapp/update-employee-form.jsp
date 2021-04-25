<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file = "/template/header.jsp" %>	

	<header>
	  <div class="p-5 text-center bg-light">
	    <h1 class="mb-3">Update Employee</h1>
	    <h4 class="mb-3"></h4>
	    <h4 class="mb-3"></h4>
	  </div>
	</header>
	<div class="container">
		<form action="EmployeeControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE_EMPLOYEE" />
			<input type="hidden" name="employeeId" value="${THE_EMPLOYEE.employeeId}" />
			
			<div class="form-group">
				<label for="firstName">First Name</label>
			 	<input 
			 		type="text" 
			 		class="form-control" id="firstName" 
			 		name="firstName" 
			 		value="${THE_EMPLOYEE.employeeFirstName}">
			 </div>
			 
			<div class="form-group">
				<label for="lastName">Last Name</label>
			   	<input 
			   		type="text" 
			   		class="form-control" id="lastName" 
			   		name="lastName"
			   		value="${THE_EMPLOYEE.employeeLastName}">
			</div>
			
			<div class="form-group">
				<label for="email">Email</label>
			   	<input 
			   		type="text" 
			   		class="form-control" id="email" 
			   		name="email"
			   		value="${THE_EMPLOYEE.employeeEmail}">
			   	<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
			</div>
			
			<div class="form-group">
				<label for="role">Select a role for employee</label>
			   	<select name="role">
			   		<option value="${THE_EMPLOYEE.employeeRole}" selected>${THE_EMPLOYEE.employeeRole}</option>
			        <c:forEach items="${ROLES_LIST}" var="role">
			            <option value="${role.roleName}">${role.roleName}</option>
			        </c:forEach>
			    </select>
			</div>
			
			<div class="form-group">
		 		<button type="submit" class="btn btn-primary">Update Employee</button>
		 	</div>
		 	
		 	<p>
		 		<a href="EmployeeControllerServlet">Back to Employees</a>
		 	</p>
		</form>
		
<%@ include file = "/template/footer.jsp" %>