package com.revature.repository;

import java.util.List;

import com.revature.model.Logs;
import com.revature.model.User;

public interface UserDAO {
	
	
	List<User> getUsers();
	
	User getUser(long id);
	User getUser(String name, String password);
	
	boolean createUser(User u);
	boolean createlog(String log);
	boolean updateUser(double amt);
	void showID();
	double getBalance();

	List<Logs> getlogs();
	
	
}
