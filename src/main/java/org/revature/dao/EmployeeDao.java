package org.revature.dao;

import java.util.List;

import org.revature.model.Employee;

public interface EmployeeDao {
	public List<Employee> getAllEmployees() throws Exception;
	public void addEmployee(Employee employee) throws Exception;
	public Employee getEmployee(String employeeId) throws Exception;
	public void updateEmployee(Employee employee) throws Exception;
	public void deleteEmployee(String employeeId) throws Exception;
}
