package org.revature.dao;

import java.util.List;

import org.revature.model.Role;

public interface RoleDao {
	public List<Role> getAllRoles() throws Exception;
}
