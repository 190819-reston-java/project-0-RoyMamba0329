package com.revature.model;

public class Logs {
	private long id;
	private int Accnum;
	private String logs;
	
	

	public Logs(long id, int accnum, String logs) {
		super();
		this.id = id;
		Accnum = accnum;
		this.logs = logs;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAccnum() {
		return Accnum;
	}

	public void setAccnum(int accnum) {
		Accnum = accnum;
	}

	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
	}
}
