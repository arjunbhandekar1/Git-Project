package com.skillcube.insurance.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	static Connection conn = null;

	private DBConnection() {

		try {
			conn = DriverManager.getConnection(AppConstant.dbURL, AppConstant.username, AppConstant.password);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		if (conn == null) {
			DBConnection dbCon = new DBConnection();
			return conn;

		} else {
			return conn;

		}

	}

}
