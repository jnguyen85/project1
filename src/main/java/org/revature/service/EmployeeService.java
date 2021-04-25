package org.revature.service;

import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.revature.dao.Impl.EmployeeDaoImpl;
import org.revature.dao.Impl.RoleDaoImpl;
import org.revature.model.Employee;
import org.revature.model.Role;

public class EmployeeService {
	private EmployeeDaoImpl employeeDaoImpl;
	private RoleDaoImpl roleDaoImpl;

	public EmployeeService(Connection connection) {
		this.employeeDaoImpl = new EmployeeDaoImpl(connection);
		this.roleDaoImpl = new RoleDaoImpl(connection);
	}
	
	public List<Employee> getAllEmployees(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		List<Employee> employees = this.employeeDaoImpl.getAllEmployees();
		RequestDispatcher rs = request.getRequestDispatcher("list-employees.jsp");
		request.setAttribute("EMPLOYEES_LIST", employees);
		rs.forward(request, response);
		
		return employees;
	}

	public void addEmployeePage(HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		List<Role> roles = this.roleDaoImpl.getAllRoles();
		
		request.setAttribute("ROLES_LIST", roles);
		
		RequestDispatcher ds = request.getRequestDispatcher("/add-employee-form.jsp");
		
		ds.forward(request, response);
		
	}

	public void addEmployee(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		
		Employee employee = new Employee(firstName, lastName, email, role);
		
		employeeDaoImpl.addEmployee(employee);
		
		getAllEmployees(request, response);
		
	}

	public void getEmployee(HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		String employeeId = request.getParameter("employeeId");
		
		Employee employee = employeeDaoImpl.getEmployee(employeeId);
		
		List<Role> roles = roleDaoImpl.getAllRoles();
		
		request.setAttribute("THE_EMPLOYEE", employee);
		
		request.setAttribute("ROLES_LIST", roles);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-employee-form.jsp");
		
		dispatcher.forward(request, response);
	}

	public void updateEmployee(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		
		Employee employee = new Employee(employeeId, firstName, lastName, email, role);
		
		employeeDaoImpl.updateEmployee(employee);
		
		getAllEmployees(request, response);
	}

	public void deleteEmployee(HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		String employeeId = request.getParameter("employeeId");
		
		employeeDaoImpl.deleteEmployee(employeeId);
		
		getAllEmployees(request, response);
		
	}
	
	
	
}
