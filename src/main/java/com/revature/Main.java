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
		// ConnectionUtil.getConnection();

		UserDAO UDAO = new UserDAOimpPJDBC();

//		for (User u : UDAO.getUsers()) {
//			System.out.println(u);
//		}
		
//		User i = UDAO.getUser("Test1", "123");
//		System.out.println(i);
//		UDAO.showID();
//		UDAO.updateUser(-6000);
//	    i = UDAO.getUser("Test1", "123");
//		System.out.println(i);
//		Random r = new Random();
//		int x = r.nextInt(999999);
//		User u = new User(0L,"Roy", "Master0329", x, 20000);
//		UDAO.createUser(u);
//		User i = UDAO.getUser("Roy", "Master0329");
//		System.out.println(i);
//		x=0;

	}
}
