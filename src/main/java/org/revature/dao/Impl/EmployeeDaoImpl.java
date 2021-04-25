package org.revature.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.revature.dao.EmployeeDao;
import org.revature.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	// get a handle to a Connection object for CRUD operation
	private Connection connection;
	

	public EmployeeDaoImpl(Connection connection) {

		this.connection = connection;
	}

	@Override
	public List<Employee> getAllEmployees() throws Exception {
		List<Employee> employees = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = connection.createStatement();
			String sql = "SELECT * FROM employees";
			
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {
				int employeeId = myRs.getInt("employeeId");
				String employeeFirstName = myRs.getString("employeeFirstName");
				String employeeLastName = myRs.getString("employeeLastName");
				String employeeEmail = myRs.getString("employeeEmail");
				String employeeRole = myRs.getString("employeeRole");
				
				Employee employee = new Employee(
						employeeId, employeeFirstName,
						employeeLastName, employeeEmail,
						employeeRole);
				
				employees.add(employee);
			}// end while
			return employees;
			
		}finally {
			
			close(connection, myStmt, myRs);
			
		}// end try & catch
	}// end getAllEmployees

	@Override
	public void addEmployee(Employee employee) throws Exception{
		PreparedStatement myStmt = null;
		
		try {
			String sql = "INSERT INTO employees "
					+ "(employeeFirstName, employeeLastName, employeeEmail, employeeRole) "
					+  "VALUES (?, ?, ?, ?)";
			
			
			myStmt = connection.prepareStatement(sql);
			
			myStmt.setString(1, employee.getEmployeeFirstName());
			myStmt.setString(2, employee.getEmployeeLastName());
			myStmt.setString(3, employee.getEmployeeEmail());
			myStmt.setString(4, employee.getEmployeeRole());
			
			myStmt.execute();
			
		}finally {
			
			close(connection, myStmt, null);
			
		}// end try & catch

	}

	@Override
	public Employee getEmployee(String employeeId) throws Exception{
		
		Employee employee = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			int intEmployeeId = Integer.parseInt(employeeId);
			String sql = "SELECT * FROM employees WHERE employeeId=?";
			
			myStmt = connection.prepareStatement(sql);
			
			myStmt.setInt(1, intEmployeeId);
			
			myRs = myStmt.executeQuery();
			
			if (myRs.next()) {
				String firstName = myRs.getString("employeeFirstName");
				String lastName = myRs.getString("employeeLastName");
				String email = myRs.getString("employeeEmail");
				String role = myRs.getString("employeeRole");
				
				employee = new Employee(intEmployeeId, firstName, lastName, email, role);
				
				return employee;
			}
			else {
				throw new Exception("Could not find employee id: " + employeeId);
			}
		}
		finally {
			close(connection, myStmt, myRs);
		}
	}
	
	public Employee getEmployeeByEmail(String email) throws Exception{
		Employee employee = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			String sql = "SELECT * FROM employees WHERE employeeEmail=?";
			
			myStmt = connection.prepareStatement(sql);
			
			myStmt.setString(1, email);
			
			myRs = myStmt.executeQuery();
			
			if (myRs.next()) {
				int employeeId = myRs.getInt("employeeId");
				String firstName = myRs.getString("employeeFirstName");
				String lastName = myRs.getString("employeeLastName");
				email = myRs.getString("employeeEmail");
				String role = myRs.getString("employeeRole");
				
				employee = new Employee(employeeId, firstName, lastName, email, role);
				
				return employee;
			}
			else {
				throw new Exception("Could not find employee email: " + email);
			}
		}
		finally {
			close(connection, myStmt, myRs);
		}
	}

	@Override
	public void updateEmployee(Employee employee) throws Exception{
		
		PreparedStatement myStmt = null;
		
		try {
			String sql = "UPDATE employees "
					+ "SET employeeFirstName=?, employeeLastName=?, employeeEmail=?, employeeRole=? "
					+ "WHERE employeeId=?";
			
			myStmt = connection.prepareStatement(sql);
			
			myStmt.setString(1, employee.getEmployeeFirstName());
			myStmt.setString(2, employee.getEmployeeLastName());
			myStmt.setString(3, employee.getEmployeeEmail());
			myStmt.setString(4, employee.getEmployeeRole());
			myStmt.setInt(5, employee.getEmployeeId());
			
			myStmt.execute();
		}
		finally {
			close(connection, myStmt, null);
		}
	}

	@Override
	public void deleteEmployee(String employeeId) throws Exception{
		
		int iEmployeeId = Integer.parseInt(employeeId);
		
		PreparedStatement myStmt = null;
		
		try {
			String sql = "DELETE FROM employees WHERE employeeId=? ";
			
			myStmt = connection.prepareStatement(sql);
			
			myStmt.setInt(1, iEmployeeId);
			
			myStmt.execute();
		}
		finally {
			close(connection, myStmt, null);
		}
	}
	
	private void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	} // end close()

	

}
