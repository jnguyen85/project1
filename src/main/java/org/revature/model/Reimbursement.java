package org.revature.model;

import java.io.InputStream;

public class Reimbursement {
	private int ticketId;
	private int employeeId;
	private double amount;
	private String fileName;
	private InputStream image;
	private String description;
	private boolean status;
	
	
	public Reimbursement(int ticketId, int employeeId, double amount, String fileName, InputStream image,
			String description, boolean status) {
		this.ticketId = ticketId;
		this.employeeId = employeeId;
		this.amount = amount;
		this.fileName = fileName;
		this.image = image;
		this.description = description;
		this.status = status;
	}
	
	public Reimbursement(int ticketId, int employeeId, double amount, String fileName, String description,
			boolean status) {
		this.ticketId = ticketId;
		this.employeeId = employeeId;
		this.amount = amount;
		this.fileName = fileName;
		this.description = description;
		this.status = status;
	}


	public Reimbursement(int ticketId, double amount, String fileName, InputStream image, String description,
			boolean status) {
		this.ticketId = ticketId;
		this.amount = amount;
		this.fileName = fileName;
		this.image = image;
		this.description = description;
		this.status = status;
	}

	

	


	public Reimbursement(int ticketId, int employeeId, double amount, String fileName, String description) {
		this.ticketId = ticketId;
		this.employeeId = employeeId;
		this.amount = amount;
		this.fileName = fileName;
		this.description = description;
	}


	public int getTicketId() {
		return ticketId;
	}


	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public InputStream getImage() {
		return image;
	}


	public void setImage(InputStream image) {
		this.image = image;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
	
	
	
	
	
}
