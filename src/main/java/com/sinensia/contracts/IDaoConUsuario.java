package com.sinensia.contracts;

import java.sql.SQLException;


public interface IDaoConUsuario <T,U>{
	
	public int add(T modelo, U usuario) throws SQLException;

	public int modify(T modelo, U usuario) throws SQLException;

	public int remove(int id, U usuario) throws SQLException;
	
}
