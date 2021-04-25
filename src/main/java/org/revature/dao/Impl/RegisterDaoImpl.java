package org.revature.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.revature.dao.RegisterDao;
import org.revature.model.User;

public class RegisterDaoImpl implements RegisterDao {
	private Connection connection;
	
	public RegisterDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void addUser(User user) throws Exception {
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			String sql = "INSERT INTO users (userEmail, userPassword) VALUES(?, ?)";
			
			myStmt = connection.prepareStatement(sql);
			
			myStmt.setString(1, user.getUserEmail());
			myStmt.setString(2, user.getUserPassword());
			
			myStmt.execute();
		}
		finally {
			close(connection, myStmt, myRs);
		}
	}

	public boolean validateFirstTimeUser(String userEmail) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			
			String sql = "SELECT employeeEmail FROM employees WHERE employeeEmail=?";
			
			myStmt = connection.prepareStatement(sql);
			
			myStmt.setString(1, userEmail);

			myRs = myStmt.executeQuery();
			
			if(myRs.next()) {
				return true;
			} else {
				return false;
			}
		}
		finally {
			close(connection, myStmt, myRs);
		}
	}
	
	public boolean validateExistingUser(String userEmail) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			
			String sql = "SELECT userEmail FROM users WHERE userEmail=?";
			
			myStmt = connection.prepareStatement(sql);
			
			myStmt.setString(1, userEmail);

			myRs = myStmt.executeQuery();
			
			if(myRs.next()) {
				return true;
			} else {
				return false;
			}
		}
		finally {
			close(connection, myStmt, myRs);
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
