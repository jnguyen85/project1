package org.revature.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/ImageControllerServlet")
public class ImageControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private Connection connection;
    
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/revature", "hbstudent", "hbstudent");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PreparedStatement myStmt = null;
		ResultSet rs = null;
		int ticketId = Integer.parseInt(request.getParameter("ticketId"));
		String sql = "SELECT image from reimbursement WHERE ticketId=? ";
		
		try {
			myStmt = connection.prepareStatement(sql);
			myStmt.setInt(1, ticketId);
			
			rs = myStmt.executeQuery();
			
			if (rs.next()) {
				Blob blob = rs.getBlob("image");
				byte byteArray[]= blob.getBytes(1, (int) blob.length());
				response.setContentType("image/gif");
				OutputStream os = response.getOutputStream();
				os.write(byteArray);
				os.flush();
				os.close();
			} else {
				System.out.println("No image found with this id");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
