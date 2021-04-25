package org.revature.service;

import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.revature.dao.Impl.EmployeeDaoImpl;
import org.revature.dao.Impl.LoginImpl;
import org.revature.model.Employee;
import org.revature.model.Login;

public class LoginService {
	private LoginImpl loginImpl;
	private EmployeeDaoImpl employeeDaoImpl;

	public LoginService(Connection connection) {
		this.loginImpl = new LoginImpl(connection);
		this.employeeDaoImpl = new EmployeeDaoImpl(connection);
	}

	public void validateLogin(HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		RequestDispatcher rs = null;
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Login loginInfo = new Login(email, password);
		
		boolean validateLogin = loginImpl.validateLogin(loginInfo);
		
		if (validateLogin) {
			Employee employee = this.employeeDaoImpl.getEmployeeByEmail(email);
			
			session.setAttribute("EMPLOYEE_INFO", employee);
			session.setAttribute("IS_DISPLAY", true);
			
			rs = request.getRequestDispatcher("/home.jsp");
			
		} else {
			session.setAttribute("IS_DISPLAY", false);
			request.setAttribute("INFO", "Invalid username or password");
			rs = request.getRequestDispatcher("/login.jsp");
		}
		
		rs.forward(request, response);
	}
	
	
}
