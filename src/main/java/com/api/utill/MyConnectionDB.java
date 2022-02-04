package com.api.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnectionDB {
	private Connection singletonConn;

	public Connection getConnection() {
		if (singletonConn == null) {
			try {

				String host = "jdbc:mysql://localhost:3306/crud_prueba";
				String uName = "root";
				String uPass = "VLRZoe1324!";

				singletonConn = DriverManager.getConnection(host, uName, uPass);
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		return singletonConn;
	}

}
