package com.sinensia.contracts;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sinensia.models.Categoria;
import com.sinensia.models.Movimiento;
import com.sinensia.models.Usuario;

public interface IMovimientos {
	
	public List<Movimiento> get(Usuario user, Categoria categoria, String fecha) throws SQLException ;
	
	public int add(Movimiento movimiento) throws SQLException;
	
	public int remove(int id) throws SQLException ;
	
	public int modify(Movimiento movimiento) throws SQLException;
	
	public int removeCategoriaMovimientos(int idCategoria, int idUsuario, Connection connect) throws SQLException;
	
	public int removeUsuarioMovimientos(int idUsuario, Connection connect) throws SQLException;

}
