package com.sinensia.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RepositoryBaseDatos {
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getconnection() throws SQLException {
		Connection connection = null;
		try {
			//connection = DriverManager.getConnection("jdbc:mysql://eu-cdbr-west-01.cleardb.com/heroku_66f4946ce42c988", "bdcda050e4ec71","65ba3f34");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/showmethemoney?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root","root");
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return connection;
	}
}
