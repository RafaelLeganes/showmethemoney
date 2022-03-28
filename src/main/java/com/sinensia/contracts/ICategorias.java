package com.sinensia.contracts;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sinensia.models.Categoria;
import com.sinensia.models.Usuario;

public interface ICategorias {

	public List<Categoria> getCategorias(Usuario user, String fecha) throws SQLException;
	
	public List<Categoria> getCategoriasPaginadas(Usuario user, String fecha, int inicio, int registrosPorPagina) throws SQLException;
	
	public Categoria getCategoriabyId(Usuario user, Categoria categoria, String fecha) throws SQLException;
	
	public int addCategoriasInicio(String nombre, int idUsuario, String tipo, Connection connect) throws SQLException;
	
	public int add(Categoria categoria, Usuario usuario) throws SQLException;
	
	public int modify(Categoria categoria, Usuario usuario) throws SQLException;
	
	public int remove(int id, Usuario usuario) throws SQLException;
	
	public int removeAllUser(Usuario usuario, Connection conexion) throws SQLException;

	int getNumeroCategorias(Usuario user) throws SQLException;
	
}
