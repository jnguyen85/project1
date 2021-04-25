<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file = "/template/header.jsp" %>


	<header>
	 
	  <div class="p-5 text-center bg-light">
	    <h1 class="mb-3">Reimbursements</h1>
	  </div>
	  
	</header>
	
		
	<div class="container">
		<a class="btn btn-primary" href="<%=request.getContextPath()%>/ReimbursementControllerServlet?command=FORWARD_TO_ADD_REIMBURSEMENT_PAGE" role="button">Add New Reimbursement</a>
		<h1></h1>
		
		<table class="table table-hover table-fixed">
			<thead>
			  <tr>
			    <th>Ticket Id</th>
			    <th>Description</th>
			    <th>Amount</th>
			    <th>File Name</th>
			    <th>Status</th>
			    <th>image</th>
			    <th>Edit</th>
			    <th>Delete</th>
			  </tr>
			</thead>
			<tbody>
				<c:forEach var="reimbursement" items="${REIMBURSEMENTS_LIST}">
					<!-- set up a link for each reimbursement TO UPDATE-->
					<c:url var="updateLink" value="ReimbursementControllerServlet">
						<c:param name="command" value="LOAD_REIMBURSEMENT" />
						<c:param name="ticketId" value="${reimbursement.ticketId }" />
					</c:url>
					
					<!-- set up a link for each reimbursement TO DELETE-->
					<c:url var="deleteLink" value="ReimbursementControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="ticketId" value="${reimbursement.ticketId }" />
					</c:url>
					
					<tr class="table-info">
						<th scope="row">${reimbursement.ticketId}</th>
						<td>${reimbursement.description}</td>
						<td>${reimbursement.amount}</td>
						<td>${reimbursement.fileName}</td>
						<td>
							<c:if test="${reimbursement.status == false }">
								Processing
							</c:if>
						</td>
						<td>
							<img 
								src="ImageControllerServlet?ticketId=${reimbursement.ticketId}" 
								alt="${reimbursement.fileName}" 
								class="img-thumbnail"
								width="50"
								height="50">
						</td>
						<td>
							<a 
								href="${updateLink}" 
							>
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</a>
						</td>
						<td>
							<a 
								href="${deleteLink}" 
								onclick="if (!(confirm('Are yo sure you want to delete reimbursement ?'))) return false;"
							>
								<span class="glyphicon glyphicon-remove" aria-hidden="true" style="color: red;" ></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
<%@ include file = "/template/footer.jsp" %>