package com.sinensia.contracts;

import java.sql.SQLException;
import java.util.List;

import com.sinensia.models.Categoria;
import com.sinensia.models.Usuario;

public interface MovimientoDao<T> {
	
	public List<T> get(Usuario usuario, Categoria categoria, String fecha) throws SQLException;
	
	public int add(T modelo) throws SQLException;
	
	public int modify(T movimiento) throws SQLException;
	
	public int remove(int id) throws SQLException;
}
