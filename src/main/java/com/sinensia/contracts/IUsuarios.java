package com.sinensia.contracts;

import java.sql.SQLException;
import java.util.Properties;

import com.sinensia.models.Usuario;

public interface IUsuarios {
	
	public Usuario get(String nombre, String pass) throws SQLException;
	
	public Usuario add(Usuario usuario, Properties appProps) throws SQLException;
	
	public int modify(Usuario usuario) throws SQLException;
	
	public int remove(Usuario user) throws SQLException;
	
	

}
