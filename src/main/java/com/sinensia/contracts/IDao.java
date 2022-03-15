package com.sinensia.contracts;

import java.sql.SQLException;



public interface IDao<T> {
	
	public int add(T modelo) throws SQLException;

	public int modify(T movimiento) throws SQLException;

	public int remove(int id) throws SQLException;

}
