package com.revature.model;

public class User {
	private long id;
	private String firstName;
	private String password;
	private int AccNum;
	private double  Amount;
	
	public User(long id, String firstName, String password, int accNum, double amount) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.password = password;
		AccNum = accNum;
		Amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccNum() {
		return AccNum;
	}

	public void setAccNum(int accNum) {
		AccNum = accNum;
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", password=" + password + ", AccNum=" + AccNum
				+ ", Amount=" + Amount + "]";
	}
	
	
	
}
