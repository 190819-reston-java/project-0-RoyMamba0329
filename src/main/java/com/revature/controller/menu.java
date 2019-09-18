package com.revature.controller;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;

import com.revature.exception.UserNotFoundException;
import com.revature.exception.WronginputException;
import com.revature.model.Logs;
import com.revature.model.User;
import com.revature.repository.UserDAO;
import com.revature.repository.UserDAOimpPJDBC;

public class menu {
	private static Scanner input = new Scanner(System.in);
	private static UserDAO UDAO = new UserDAOimpPJDBC();
	private static Logger log = Logger.getLogger(menu.class);
	private static Date date = Calendar.getInstance().getTime();
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	private static String strDate = dateFormat.format(date);

	public static void startMenu() {
		log.debug("Menu Iniciated.");
		System.out.println("****************************************");
		System.out.println("              Please Choose:            ");
		System.out.println("1) Log in.");
		System.out.println("2) Register.");
		System.out.println("****************************************");
		String startmenuinput = input.nextLine();
		log.debug("Received user input: " + startmenuinput);

		switch (startmenuinput) {
		case "1": {
			Login();

		}
			break;

		case "2": {
			register();
		}
			break;

		default: {
			System.out.println("Invalid input please try again later!!!!!!!");
			log.fatal("Invalid user input, Menu restarting.");
			startMenu();
		}
			break;
		}

	}

	public static void logInMenu() {
		log.info("User Successful log in.");
		System.out.println("+++++++++++++++++++++++++++++++++++++++");
		System.out.println("          ¡Revature Bank!            ");
		System.out.println("+++++++++++++++++++++++++++++++++++++++");
		System.out.println("Please choose a service");
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("3. View Balance");
		System.out.println("4. View all transactions");
		System.out.println("+++++++++++++++++++++++++++++++++++++++");

		String logininput = input.nextLine();
		log.debug("User input recieved: " + logininput);

		switch (logininput) {
		case "1": {
			UserDeposit();
		}
			break;
		case "2": {
			UserWithdraw();
		}
			break;
		case "3": {
			balance();
		}
			break;
		case "4": {
			showAllTransactions();
		}
			break;
		default: {
			System.out.println("Invalid input please try again.");
			log.error("Invalid user input restarting the logged in menu.");
			logInMenu();
		}
			break;
		}

	}

	private static void showAllTransactions() {
		List<Logs> logs = UDAO.getlogs();

		System.out.println("-----------------------------");
		System.out.println("    All User transaction     ");
		System.out.println("-----------------------------");
		for (Logs ls : logs) {
			System.out.println("User Account number: " + ls.getAccnum());
			System.out.println(ls.getLogs());
		}
		System.out.println("-----------------------------");

		String nothing = input.nextLine();
		logInMenu();

	}

	private static void Login() {
		log.info("Register menu iniciated");
		System.out.println("########################################");
		System.out.println("    Enter your creentials for login     ");
		System.out.println("########################################");
		boolean pass = false;
		do {
			pass = false;
			System.out.print("Enter User Name: ");
			String Uname = input.nextLine();
			log.debug("User input recieved: " + Uname);
			System.out.println("");
			System.out.print("Enter Password: ");
			String Upass = input.nextLine();
			log.debug("User input recieved: " + Upass);

			try {
				if (UDAO.getUser(Uname, Upass) == null) {
					throw new UserNotFoundException();
				}

			} catch (UserNotFoundException e) {
				System.out.println(e.getMessage());
				log.error("Error invalid credentials!!");
				pass = true;
			}
		} while (pass == true);

		if (pass == false) {
			System.out.println("SUCCESS!!!!!!");
			log.info("Successful log in.");
			String enter = input.nextLine();
			logInMenu();
		}

	}

	public static void UserDeposit() {

		log.info("Deposit is being created");
		System.out.println("$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$");
		System.out.println("                     Account Deposit                         ");
		System.out.println("$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$+$");
		boolean pass = false;
		String money;
		do {
			pass = false;
			System.out.print("Please enter amount for deposit: ");
			try {
				money = input.nextLine();
				try {
					double m = Double.valueOf(money);
					if (UDAO.updateUser(m) == false) {
						throw new WronginputException();
					}
				} catch (Exception e) {
					throw new WronginputException();
				}
				log.debug("User input for deposit: " + money);
				UDAO.createlog(strDate + "User Deposited: " + money);

			} catch (WronginputException e) {
				System.out.println(e.getMessage());
				log.fatal("Invalid user deposit input");
				pass = true;
			}
		} while (pass == true);

		if (pass == false) {
			System.out.println("SUCCESS!!!!!!");
			log.info("Successful user deposit");
			String enter = input.nextLine();
			logInMenu();
		}
	}

	public static void UserWithdraw() {
		log.info("Withdraw is being created");
		System.out.println("$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$");
		System.out.println("                     Account Withdraw                        ");
		System.out.println("$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$");
		boolean pass = false;
		do {
			pass = false;
			System.out.print("Please enter amount for withdraw: ");
			try {
				String money = input.nextLine();
				try {
				Double m = Double.valueOf(money);
				if (UDAO.updateUser(-m) == false) {
					throw new WronginputException();
				}
				}catch (Exception e) {
					throw new WronginputException();
				}
				
				log.debug("User input for withdraw: " + money);
				UDAO.createlog(strDate + "User Withdrew: " + money);

			} catch (WronginputException e) {
				System.out.println(e.getMessage());
				log.fatal("Invalid user withdraw input");
				pass = true;
			}
		} while (pass == true);

		if (pass == false) {
			System.out.println("SUCCESS!!!!!!");
			log.info("Successful user withdraw");
			String enter = input.nextLine();
			logInMenu();
		}

	}

	private static void register() {
		User u;
		Random r = new Random();
		int x = r.nextInt(999999);
		log.info("Registration started.");
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println("        RESGISTRATION         ");
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.print("Please enter your Name: ");
		String name = input.nextLine();
		log.debug("User input for name: " + name);
		System.out.print("Please enter password: ");
		String pw = input.nextLine();
		log.debug("User input for password: " + pw);
		boolean pass = false;
		do {
			pass = false;
			System.out.print("Please enter inicial amount: ");
			try {
				String money = input.nextLine();
				log.debug("User input for money: " + money);
				UDAO.createUser(u = new User(01, name, pw, x, Double.valueOf(money)));
			} catch (Exception e) {
				System.out.println("Wrong input please try again!!!");
				log.fatal("User input invalid");
				pass = true;
			}
		} while (pass == true);

		if (pass == false) {
			System.out.println("SUCCESS!!!!!!");
			log.info("User successfuly registered");
			String enter = input.nextLine();
			Login();
		}

	}

	public static void balance() {
		double amt = UDAO.getBalance();
		NumberFormat nf = NumberFormat.getCurrencyInstance();

		System.out.println("$$$$$$$$$$$$$$$$$$$$");
		System.out.println("Your current balance is: " + nf.format(amt));
		System.out.println("$$$$$$$$$$$$$$$$$$$$");
		String enter = input.nextLine();

		logInMenu();
	}

}
