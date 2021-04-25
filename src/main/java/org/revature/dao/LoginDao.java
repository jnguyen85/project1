package org.revature.dao;

import org.revature.model.Login;

public interface LoginDao {
	public boolean validateLogin(Login loginInfo) throws Exception;
}
