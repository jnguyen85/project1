<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file = "/template/header.jsp" %>
	<div class="container">
		<form action="ReimbursementControllerServlet" method="POST" enctype="multipart/form-data" >
			<input type="hidden" name="command" value="ADD_REIMBURSEMENT" />
			
			<div class="form-group">
				<label for="amount">Total cost for reimbursement</label>
			 	<input type="text" class="form-control" id="amount" name="amount" placeholder="Enter amount">
			 </div>
			 
			<div class="form-group">
				<label for="image">Image</label>
			   	<input type="file" class="form-control" id="image" name="image">
			</div>
			
			<div class="form-group">
				<label for="description">Description of Reimbursement</label>
			   	<input type="text" class="form-control" id="description" name="description" placeholder="Enter Description">
			</div>
			
			
			<div class="form-group">
		 		<button type="submit" class="btn btn-primary">Save Reimbursement</button>
		 	</div>
		 	
		 	<div class="form-group">
		 		<a href="ReimbursementControllerServlet">Back to Reimbursements</a>
		 	</div>
		</form>

<%@ include file = "/template/footer.jsp" %>