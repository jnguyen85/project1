package org.revature.service;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.revature.dao.Impl.ReimbursementDaoImpl;
import org.revature.model.Employee;
import org.revature.model.Reimbursement;


public class ReimbursementService {
	private ReimbursementDaoImpl reimbursementDaoImpl;

	public ReimbursementService(Connection connection) {
		this.reimbursementDaoImpl = new ReimbursementDaoImpl(connection);
	}

	public void addReimbursement(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("Inside >>> " + "addReimbursement");
		
		String filename = "No Name" ;
		
		ServletFileUpload upload =  new ServletFileUpload(new DiskFileItemFactory());
		
		List<FileItem> images = null;
		try {
			images = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Iterator<FileItem> iter = images.iterator();
		
		HashMap<String, String> fields = new HashMap<>();
		
		InputStream is = null;
			
		while(iter.hasNext()) {
			FileItem item = iter.next();
			if(item.isFormField()) {
				fields.put(item.getFieldName(), item.getString());
				String name = item.getFieldName();
				String value = item.getString();
				System.out.println("name: " + name);
				System.out.println("value: " + value);
			} else {
				filename = item.getName();
				try {filename = filename.substring(filename.lastIndexOf("\\") + 1);} catch(Exception e) {}
				System.out.println("filename: " + filename);
				if (filename == null || filename.equals("")) {
					break;
				} else { 
					
					 is = item.getInputStream();
				}
			}
		}
		
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("EMPLOYEE_INFO");
		
		System.out.println("Employee id = " + employee.getEmployeeId());
		
		double amount = Double.parseDouble(fields.get("amount"));
		String description = fields.get("description");
		
		Reimbursement reimbursement = new Reimbursement(employee.getEmployeeId(), amount, filename, is, description, false);
		
		try {
			reimbursementDaoImpl.addReimbursement(reimbursement);
			
			getAllReimbursementByUser(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void getAllReimbursementByUser(HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		System.out.println("Inside >>> " + "getAllReimbursementByUser");
		
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("EMPLOYEE_INFO");
		
		List<Reimbursement> reimbursements = this.reimbursementDaoImpl.getAllReimbursementByUser(employee.getEmployeeId());
		
		request.setAttribute("REIMBURSEMENTS_LIST", reimbursements);
		
		RequestDispatcher rd = request.getRequestDispatcher("/list-reimbursements.jsp");
		
		rd.forward(request, response);
		
	}

	public void forwardToAddReimbursementPage(HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		System.out.println("Inside >>> " + "forwardToAddReimbursementPage");
		
		RequestDispatcher rd = request.getRequestDispatcher("/add-reimbursement-form.jsp");
		rd.forward(request, response);
		
	}

	public void getReimbursement(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		String ticketId = request.getParameter("ticketId");
		
		Reimbursement reimbursement = reimbursementDaoImpl.getReimbursement(ticketId);
		
		
		request.setAttribute("THE_REIMBURSEMENT", reimbursement);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-reimbursement-form.jsp");
		
		dispatcher.forward(request, response);
		
	}

	public void updateReimbursement(HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		System.out.println("Inside >>> " + "updateReimbursement");
		
		String filename = "No Name" ;
		
		ServletFileUpload upload =  new ServletFileUpload(new DiskFileItemFactory());
		
		List<FileItem> images = null;
		try {
			images = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Iterator<FileItem> iter = images.iterator();
		
		HashMap<String, String> fields = new HashMap<>();
		
		InputStream is = null;
			
		while(iter.hasNext()) {
			FileItem item = iter.next();
			if(item.isFormField()) {
				fields.put(item.getFieldName(), item.getString());
				String name = item.getFieldName();
				String value = item.getString();
				System.out.println("name: " + name);
				System.out.println("value: " + value);
			} else {
				filename = item.getName();
				try {filename = filename.substring(filename.lastIndexOf("\\") + 1);} catch(Exception e) {}
				System.out.println("filename: " + filename);
				if (filename == null || filename.equals("")) {
					break;
				} else { 
					
					 is = item.getInputStream();
				}
			}
		}
		
		
		
		int ticketId = Integer.parseInt(fields.get("ticketId"));
		double amount = Double.parseDouble(fields.get("amount"));
		String description = fields.get("description");
		
		if (filename == null || filename.equals("")) {
			filename = fields.get("fileName");
		}
		
		Reimbursement reimbursement = new Reimbursement(ticketId, amount, filename, is, description, false);
		
		try {
			reimbursementDaoImpl.updateReimbursement(reimbursement);
			
			getAllReimbursementByUser(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void deleteReimbursement(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
