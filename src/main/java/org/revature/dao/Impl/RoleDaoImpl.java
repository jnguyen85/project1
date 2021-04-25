package org.revature.dao.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.revature.dao.RoleDao;
import org.revature.model.Role;

public class RoleDaoImpl implements RoleDao {
	
	private Connection connection;
	
	public RoleDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Role> getAllRoles() throws Exception {
	
		List<Role> roles = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = connection.createStatement();
			String sql = "SELECT * FROM roles";
			
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {
				int roleId = myRs.getInt("roleId");
				String roleName = myRs.getString("roleName");
				
				Role role = new Role(roleId, roleName);
				
				roles.add(role);
				
			}// end while
			
		}finally {
			
			close(connection, myStmt, myRs);
			
		}// end try & catch
		
		return roles;
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
