package com.revature.service;

import java.util.Scanner;

import com.revature.repository.UserDAO;
import com.revature.repository.UserDAOimpPJDBC;

public class UserService {
	private static Scanner input = new Scanner(System.in);
	private static UserDAO UDAO = new UserDAOimpPJDBC();

	public static void Login() {
		System.out.println("########################################");
		System.out.print("Enter User Name: ");
		String Uname = input.nextLine();
		System.out.println("");
		System.out.print("Enter Password: ");
		String Upass = input.nextLine();
		System.out.println("########################################");
		UDAO.getUser(Uname, Upass);

		
	}

	public static void UserDeposit() {
		System.out.println("$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$");
		System.out.print("Please enter amount for deposit: ");
		String money = input.nextLine();
		System.out.println("");
		System.out.println("$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$");

		UDAO.updateUser(Double.valueOf(money));
	}
}
