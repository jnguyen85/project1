package org.revature.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.revature.dao.LoginDao;
import org.revature.model.Login;

public class LoginImpl implements LoginDao{

	private Connection connection;
	
	public LoginImpl(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public boolean validateLogin(Login loginInfo) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			String sql = "SELECT userEmail, userPassword FROM users WHERE userEmail=? AND userPassword=? ";
			
			myStmt = connection.prepareStatement(sql);
			
			myStmt.setString(1, loginInfo.getEmail());
			myStmt.setString(2, loginInfo.getPassword());
			
			myRs = myStmt.executeQuery();
			
			if (myRs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, myStmt, myRs);
		}
		return false;
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
