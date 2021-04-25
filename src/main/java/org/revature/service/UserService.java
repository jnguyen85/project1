package org.revature.service;

import java.sql.Connection;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.revature.dao.Impl.RegisterDaoImpl;
import org.revature.model.User;

public class UserService {
	private RegisterDaoImpl registerDaoImpl;

	public UserService(Connection connection) {
		this.registerDaoImpl = new RegisterDaoImpl(connection);
	}

	public void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		RequestDispatcher rd = null;
		String userEmail = request.getParameter("email").toLowerCase();
		String userPassword = request.getParameter("password");
		User user = new User(userEmail, userPassword);
		
		boolean firstTimeUser = validateFirstTimeUser(userEmail);
		boolean existingUser = validateExistingUser(userEmail);
		
		if (existingUser) {
			request.setAttribute("INFO", "You already registered. Please log in");
			rd = request.getRequestDispatcher("/registration-form.jsp");
		} else if (firstTimeUser) {
			this.registerDaoImpl.addUser(user);
			request.setAttribute("INFO", "You have successfully register. Please log in.");
			rd = request.getRequestDispatcher("/login.jsp");
		} else {
			request.setAttribute("INFO", "You don't have an account with Revature. Please check with administrator");
			rd = request.getRequestDispatcher("/registration-form.jsp");
		}
		
		rd.forward(request, response);
	}

	private boolean validateFirstTimeUser(String userEmail) throws Exception{
		return this.registerDaoImpl.validateFirstTimeUser(userEmail);
	}
	
	public boolean validateExistingUser(String userEmail) throws Exception {
		return this.registerDaoImpl.validateExistingUser(userEmail);
	}
	
	
}
