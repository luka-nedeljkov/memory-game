package me.memory_game.utils;

import java.sql.*;
import java.util.ArrayList;

public class SQLUtils {

	private static SQLUtils instance;

	private final Connection connection;

	private SQLUtils() {
		connection = connect();
	}

	public static SQLUtils getInstance() {
		if(instance == null) {
			instance = new SQLUtils();
		}
		return instance;
	}

	public void createTable() {
		try {
			Statement statement = connection.createStatement();
			statement.execute("create table players (" +
					"name text primary key," +
					"wins integer not null" +
					");");
		} catch (SQLException ignored) {}
	}

	public ArrayList<String> selectAllNames() {
		ArrayList<String> list = new ArrayList<>();
		try {
			ResultSet rs = connection.createStatement().executeQuery("select * from players;");
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public int select(String name) {
		ResultSet rs;
		try {
			rs = connection.createStatement().executeQuery("select wins from players where name='" + name + "';");
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void insert(String name) {
		try {
			connection.createStatement().execute("insert into players values ('\" + name + \"', 0);");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(String name, int wins) {
		try {
			connection.createStatement().execute("update players set wins = " + wins + " where name = '" + name + "';");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection connect() {
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:save.db");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return connection;
	}

}
