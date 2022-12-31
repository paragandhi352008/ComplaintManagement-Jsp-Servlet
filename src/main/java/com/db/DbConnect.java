package com.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {

	private static Connection con;

	public static Connection getCon() {
		try {
			// To connect with database
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccms", "root", "Parag@12345");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
