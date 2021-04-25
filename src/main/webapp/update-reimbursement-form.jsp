<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file = "/template/header.jsp" %>
	<div class="container">
		<form action="UpdateReimControllerServlet" method="POST" enctype="multipart/form-data" >
			<input type="hidden" name="command" value="UPDATE_REIMBURSEMENT" />
			<input type="hidden" name="ticketId" value="${THE_REIMBURSEMENT.ticketId}" />
			<div class="form-group">
				<h3>Ticket ID: ${THE_REIMBURSEMENT.ticketId}</h3>
			 </div>
			
			<div class="form-group">
				<label for="amount">Total cost for reimbursement</label>
			 	<input type="text" class="form-control" id="amount" name="amount" value="${THE_REIMBURSEMENT.amount }">
			 </div>
			 
			 <div class="form-group">
			 	<input type="hidden" name="fileName" value=" ${THE_REIMBURSEMENT.fileName}" />
				<span>Current file</span>: ${THE_REIMBURSEMENT.fileName} <br>
				<img 
					src="ImageControllerServlet?ticketId=${THE_REIMBURSEMENT.ticketId}" 
					alt="${reimbursement.fileName}" 
					class="img-thumbnail"
					width="250"
					height="150">
			 	
			 </div>
			 
			 <div class="form-group">
				<label for="description">Description of Reimbursement</label>
			   	<input type="text" class="form-control" id="description" name="description" value="${THE_REIMBURSEMENT.description}">
			</div>
			 
			<div class="form-group">
				<label for="image">Click here to update current image</label>
			   	<input type="file" class="form-control" id="image" name="image" >
			</div>
			
			<div class="form-group">
		 		<button type="submit" class="btn btn-primary">Update Reimbursement</button>
		 	</div>
		 	
		 	<div class="form-group">
		 		<a href="ReimbursementControllerServlet?command=LIST_REIMBURSEMENT">Back to Reimbursements</a>
		 	</div>
		</form>

<%@ include file = "/template/footer.jsp" %>