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
import org.revature.service.UserService;


@WebServlet("/RestrationServlet")
public class RestrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
    private UserService userService;
    
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/revature", "hbstudent", "hbstudent");
			this.userService = new UserService(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String command = request.getParameter("command");
		if (command == null || command.length() == 0) {
			command = "REGISTER_USER";
		}
		
		try {
			
			switch (command) {
			case "REGISTER_USER":
				userService.addUser(request, response);
				break;
			default:

			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
