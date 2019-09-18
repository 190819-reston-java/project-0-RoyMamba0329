package com.revature;

import java.util.Random;

import com.revature.controller.menu;
import com.revature.model.User;
import com.revature.repository.UserDAO;
import com.revature.repository.UserDAOimpPJDBC;
import com.revature.utils.ConnectionUtil;

/**
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {

	public static void main(String[] args) {
		menu.startMenu();
	}
}
