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

import org.revature.service.EmployeeService;


@WebServlet("/EmployeeControllerServlet")
public class EmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
    private EmployeeService employeeService;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/revature", "hbstudent", "hbstudent");
			this.employeeService = new EmployeeService(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String command = request.getParameter("command");
		if (command == null || command.length() == 0) {
			command = "EMPLOYEES_LIST";
		}
			
		try {
			
			switch (command) {
				case "EMPLOYEES_LIST":
					employeeService.getAllEmployees(request, response);
					break;
				case "ADD_EMPLOYEE_PAGE":
					employeeService.addEmployeePage(request, response);
					break;
				case "ADD_EMPLOYEE":
					employeeService.addEmployee(request, response);
					break;
				case "LOAD":
					employeeService.getEmployee(request, response);
					break;
				case "UPDATE_EMPLOYEE":
					employeeService.updateEmployee(request, response);
					break;
				case "DELETE_EMPLOYEE":
					employeeService.deleteEmployee(request, response);
					break;
			default:
				employeeService.getAllEmployees(request, response);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
