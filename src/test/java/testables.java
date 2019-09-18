import static org.junit.Assert.*;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.controller.menu;
import com.revature.model.User;
import com.revature.repository.UserDAO;
import com.revature.repository.UserDAOimpPJDBC;

public class testables {
	UserDAO UDAOtest = new UserDAOimpPJDBC();
	menu m = new menu();

	@Test(expected = Exception.class)
	public void simulateadeposit() {
		boolean bool = true;
		String userinput1 = "Tesst1";
		String userinput2 = "123";
		User u = UDAOtest.getUser(userinput1, userinput2);
		
		try {
			UDAOtest.updateUser(1);
		} catch (Exception e) {
			bool = false;
		}
		
		assertTrue(bool == true);
	}
	
	@Test(expected = Exception.class)
	public void simulateabadwithdraw2() {
		String userinput1 = "Test1";
		String userinput2 = "123";
		String num = "sfsga";
		User u = UDAOtest.getUser(userinput1, userinput2);
		
		boolean bool = true;
		try {
			UDAOtest.updateUser(Double.valueOf(num));
		} catch (Exception e) {
			bool = false;
		}
		
		assertTrue(bool == true);
	}
	
	@Test(expected = Exception.class)
	public void simulateBADdeposit() {
		String userinput1 = "Test1";
		String userinput2 = "123";
		String userinput3 = "abc";
		User u = UDAOtest.getUser(userinput1, userinput2);
		
		boolean bool = true;
		try {
			UDAOtest.updateUser(Double.valueOf(userinput3));
		} catch (Exception e) {
		 bool = false;
		}
		
		assertFalse(bool == true);	
	}
	
	@Test(expected = Exception.class)
	public void simulateviewbalance() {
		String userinput1 = "Test1";
		String userinput2 = "123";
		User u = UDAOtest.getUser(userinput1, userinput2);
		
		boolean bool = true;
		try {
			m.balance();
		} catch (Exception e) {
		 bool = false;
		}
		
		assertTrue(bool == true);	
	}
	
	@Test(expected = Exception.class)
	public void simulatebadviewbalance() {
		String userinput1 = "Test1";
		String userinput2 = "13";
		User u = UDAOtest.getUser(userinput1, userinput2);
		
		boolean bool = true;
		try {
			m.balance();
		} catch (Exception e) {
		 bool = false;
		}
		
		assertTrue(bool == false);	
	}

}
