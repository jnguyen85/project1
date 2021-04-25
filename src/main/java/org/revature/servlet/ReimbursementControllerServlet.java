package org.revature.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.revature.service.ReimbursementService;

@WebServlet("/ReimbursementControllerServlet")
public class ReimbursementControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
    private ReimbursementService reimbursementService;
    
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/revature", "hbstudent", "hbstudent");
			this.reimbursementService = new ReimbursementService(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String command = request.getParameter("command");
		if (command == null || command.length() == 0) {
			command = "ADD_REIMBURSEMENT";
		}
			
		try {
			
			switch (command) {
				case "ADD_REIMBURSEMENT":
					reimbursementService.addReimbursement(request, response);
					break;
				case "LIST_REIMBURSEMENT":
					reimbursementService.getAllReimbursementByUser(request, response);
					break;
				case "FORWARD_TO_ADD_REIMBURSEMENT_PAGE":
					reimbursementService.forwardToAddReimbursementPage(request, response);
					break;
				case "LOAD_REIMBURSEMENT":
					reimbursementService.getReimbursement(request, response);
					break;
				case "UPDATE_REIMBURSEMENT":
					reimbursementService.updateReimbursement(request, response);
					break;
				case "DELETE_REIMBURSEMENT":
					reimbursementService.deleteReimbursement(request, response);
					break;
				default:
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
