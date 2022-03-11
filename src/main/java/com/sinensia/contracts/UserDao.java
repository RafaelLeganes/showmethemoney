package com.sinensia.contracts;

import java.sql.SQLException;


public interface UserDao<T> {

	public T get(String nombre, String pass) throws SQLException;
	
	public T add(T modelo) throws SQLException;

}
