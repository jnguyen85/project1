<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file = "/template/header.jsp" %>

	<div class="container">
		<form action="EmployeeControllerServlet" method="GET">
			<input type="hidden" name="command" value="ADD_EMPLOYEE" />
			
			<div class="form-group">
				<label for="firstName">First Name</label>
			 	<input type="text" class="form-control" id="firstName" name="firstName" aria-describedby="emailHelp" placeholder="Enter First Name">
			 </div>
			 
			<div class="form-group">
				<label for="lastName">Last Name</label>
			   	<input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter Last Name">
			</div>
			
			<div class="form-group">
				<label for="email">Email</label>
			   	<input type="text" class="form-control" id="email" name="email" placeholder="Enter Email">
			   	<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
			</div>

			<div class="form-group">
				<label for="role">Select a role for employee</label>
			   	<select name="role">
			   		<option value="employee" selected>employee</option>
			        <c:forEach items="${ROLES_LIST}" var="role">
			            <option value="${role.roleName}">${role.roleName}</option>
			        </c:forEach>
			    </select>
			</div>
			
			<div class="form-group">
		 		<button type="submit" class="btn btn-primary">Save Employee</button>
		 	</div>
		 	
		 	<div class="form-group">
		 		<a href="<%=request.getContextPath()%>/EmployeeControllerServlet">Back to Employees</a>
		 	</div>
		</form>

<%@ include file = "/template/footer.jsp" %>