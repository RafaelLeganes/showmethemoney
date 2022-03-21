package com.sinensia.contracts;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;




public interface UserDao<T> {

	public T get(String nombre, String pass) throws SQLException;

	public T add(T modelo, Properties appProps) throws SQLException, IOException;
	
	public int modify(T modelo) throws SQLException;

	public int remove(T modelo) throws SQLException;

}
