package org.revature.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.revature.dao.ReimbursementDao;
import org.revature.model.Reimbursement;

public class ReimbursementDaoImpl implements ReimbursementDao {
	private Connection connection;
	
	

	public ReimbursementDaoImpl(Connection connection) {
		this.connection = connection;
	}



	@Override
	public void addReimbursement(Reimbursement reimbursement) throws Exception {
		PreparedStatement myStmt = null;
		System.out.println(reimbursement.getEmployeeId());
		
		try {
			
			String sql = "INSERT INTO reimbursement (employeeId, amount, fileName, image, description, status) VALUES(?, ?, ?, ?, ?, ?)";
			
			myStmt = connection.prepareStatement(sql);
			
			myStmt.setInt(1, reimbursement.getEmployeeId());
			myStmt.setDouble(2, reimbursement.getAmount());
			myStmt.setString(3, reimbursement.getFileName());
			myStmt.setBlob(4, reimbursement.getImage());
			myStmt.setString(5, reimbursement.getDescription());
			myStmt.setBoolean(6, reimbursement.isStatus());
			
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



	public List<Reimbursement> getAllReimbursementByUser(int employeeId) throws Exception{
		
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT * FROM reimbursement WHERE employeeId=?";
			
			myStmt = connection.prepareStatement(sql);
			myStmt.setInt(1, employeeId);
			
			rs = myStmt.executeQuery();
			
			while(rs.next()) {
				int ticketId = rs.getInt("ticketId");
				double amount = rs.getDouble("amount");
				String filename = rs.getString("fileName");
				String desc = rs.getString("description");
				boolean status = rs.getBoolean("status");
				
				
				Reimbursement r = new Reimbursement(ticketId, employeeId, amount, filename, desc, status);
				
				reimbursements.add(r);
			}
			
			return reimbursements;
		}
		finally {
			close(connection, myStmt, null);
		}
	}



	public Reimbursement getReimbursement(String ticketId) throws Exception{
		
		Reimbursement reimbursement = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			int tkId = Integer.parseInt(ticketId);
			String sql = "SELECT employeeId, amount, fileName, description FROM reimbursement WHERE ticketId=? AND status=?";
			
			myStmt = connection.prepareStatement(sql);
			
			myStmt.setInt(1, tkId);
			myStmt.setBoolean(2, false);
			
			myRs = myStmt.executeQuery();
			
			if (myRs.next()) {
				int employeeId = myRs.getInt("employeeId");
				double amount = myRs.getDouble("amount");
				String fileName = myRs.getString("fileName");
				String description = myRs.getString("description");
				
				reimbursement = new Reimbursement(tkId, employeeId, amount, fileName, description);
				
				return reimbursement;
			}
			else {
				throw new Exception("Could not find ticket id: " + ticketId);
			}
		}
		finally {
			close(connection, myStmt, myRs);
		}
	}



	public void updateReimbursement(Reimbursement reimbursement) throws Exception{
		PreparedStatement myStmt = null;
		
		try {
			
			String sql = null;
			
			if (reimbursement.getImage() == null) {
				sql = "UPDATE reimbursement "
					+ "SET amount=?, fileName=?, description=? "
					+ "WHERE ticketId=?";
				myStmt = connection.prepareStatement(sql);
				
				myStmt.setDouble(1, reimbursement.getAmount());
				myStmt.setString(2, reimbursement.getFileName());
				myStmt.setString(3, reimbursement.getDescription());
				myStmt.setInt(4, reimbursement.getTicketId());
			} else {
				sql = "UPDATE reimbursement "
						+ "SET amount=?, fileName=?, image=?, description=? "
						+ "WHERE ticketId=?";
				myStmt = connection.prepareStatement(sql);
				
				myStmt.setDouble(1, reimbursement.getAmount());
				myStmt.setString(2, reimbursement.getFileName());
				myStmt.setBlob(3, reimbursement.getImage());
				myStmt.setString(4, reimbursement.getDescription());
				myStmt.setInt(5, reimbursement.getTicketId());
			}
			
			myStmt.execute();
		}
		finally {
			close(connection, myStmt, null);
		}
		
	}
}
