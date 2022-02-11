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
				String uPass = "*******";
				
		        Class.forName("com.mysql.cj.jdbc.Driver");
				singletonConn = DriverManager.getConnection(host, uName, uPass);
				
			} catch (SQLException e) {
				System.out.println(e);
			} catch (Exception e){
		        System.out.println("Error: "+e.getMessage());
		    }
		}
		return singletonConn;
	}

}
