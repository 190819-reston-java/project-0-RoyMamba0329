package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.revature.exception.UserNotFoundException;
import com.revature.model.Logs;
import com.revature.model.User;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.StreamCloser;

public class UserDAOimpPJDBC implements UserDAO {

	private static long Uid = 0L;

	@Override
	public List<User> getUsers() {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		List<User> users = new ArrayList<User>();

		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM dbbank;");

			while (rs.next()) {
				users.add(createUserFromRS(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(rs);
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}

		return users;
	}

	@Override
	public User getUser(long id) {
		User u = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM dbbank WHERE id = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, id);
				if (stmt.execute()) {
					try (ResultSet rs = stmt.getResultSet()) {
						if (rs.next()) {
							u = createUserFromRS(rs);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}
	
	@Override
	public double getBalance() {
		User u = null;
		double amt = 0;
		

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM dbbank WHERE id = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, Uid);
				if (stmt.execute()) {
					try (ResultSet rs = stmt.getResultSet()) {
						if (rs.next()) {
							u = createUserFromRS(rs);
							amt = u.getAmount();
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return amt;
	}

	@Override
	public User getUser(String name, String password) {
		User u = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			stmt = conn.prepareStatement("SELECT * FROM dbbank WHERE firstName = ? AND password = ?;");

			stmt.setString(1, name);
			stmt.setString(2, password);

			if (stmt.execute()) {
				rs = stmt.getResultSet();

				if (rs.next()) {
					u = createUserFromRS(rs);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			StreamCloser.close(rs);
			StreamCloser.close(stmt);
		}

		Uid = u.getId();
		return u;
	}

	@Override
	public boolean createUser(User u) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String query = "INSERT INTO dbbank VALUES (DEFAULT, ?, ?, ? , ?);";
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, u.getFirstName());
			stmt.setString(2, u.getPassword());
			stmt.setInt(3, u.getAccNum());
			stmt.setDouble(4, u.getAmount());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			StreamCloser.close(rs);
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
		return true;
	}

	@Override
	public boolean updateUser(double amt) {
		Connection conn = null;
		PreparedStatement stmt = null;
		User u = getUser(Uid);
		
		final String query = "UPDATE dbbank SET AMOUNT = ? WHERE id = ?;";
		
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setDouble(1, u.getAmount() + amt);
			stmt.setLong(2, Uid);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
		return true;
	}

	private User createUserFromRS(ResultSet rs) throws SQLException {
		return new User(rs.getLong("id"),
				rs.getString("firstName"),
				rs.getString("password"),
				rs.getInt("AccountNum"),
				rs.getDouble("Amount"));
	}

	public void showID() {
		System.out.println(Uid);
	}
	
	private Logs createLogsFromRS(ResultSet rs) throws SQLException {
		return new Logs(rs.getLong("id"),
				rs.getInt("Accnum"),
				rs.getString("logs"));
	}

	@Override
	public List<Logs> getlogs() {
		User u = getUser(Uid);
		
		List<Logs> logs = new ArrayList<Logs>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM bts WHERE Accnum = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setInt(1, u.getAccNum());
				if (stmt.execute()) {
					try (ResultSet rs = stmt.getResultSet()) {
						while (rs.next()) {
							logs.add(createLogsFromRS(rs));
						}
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return logs;
	}

	@Override
	public boolean createlog(String log) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		User u = getUser(Uid);
		
		String query = "INSERT INTO bts VALUES (DEFAULT, ?,?);";
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, u.getAccNum());
			stmt.setString(2, log);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(rs);
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
		
		return true;
	}

}
