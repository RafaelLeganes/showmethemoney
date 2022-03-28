package com.sinensia.repositoriesprocedures;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinensia.models.Categoria;
import com.sinensia.models.Usuario;
import com.sinensia.contracts.ICategorias;
import com.sinensia.repositories.RepositoryBaseDatos;

public class RepositoryCategoriaProcedure extends RepositoryBaseDatos implements ICategorias{
	
	
	private Connection connect;
	

	@Override
	public List<Categoria> getCategorias(Usuario user, String fecha) throws SQLException{
		RepositoryMovimientoProcedure repo = new RepositoryMovimientoProcedure();
		List<Categoria> listaCategorias = new ArrayList<Categoria>();
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			connect = super.getconnection();
			//String sql = "SELECT * FROM Categoriaspersonalizadas WHERE idUsuario=?";
			String sql = "{call get_categorias(?)}";
			cs = connect.prepareCall(sql);
			cs.setInt(1, user.getIdUsuario());
			rs = cs.executeQuery();
			while(rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setIdUsuario(rs.getInt("idUsuario"));
				categoria.setNombre(rs.getString("nombre"));
				categoria.setTipo(rs.getString("tipo").charAt(0));
				categoria.setMovimientos(repo.get(user, categoria, fecha));
				listaCategorias.add(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(cs != null) {
				cs.close();
			}
			if(rs != null) {
				rs.close();
			}
			if(connect!=null) {
				connect.close();
			}
		}		
		return listaCategorias;
	}
	
	@Override
	public List<Categoria> getCategoriasPaginadas(Usuario user, String fecha, int inicio, int registrosPorPagina) throws SQLException{
		RepositoryMovimientoProcedure repo = new RepositoryMovimientoProcedure();
		List<Categoria> listaCategorias = new ArrayList<Categoria>();
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			connect = super.getconnection();
			//String sql = "SELECT * FROM categoriaspersonalizadas WHERE idUsuario=? LIMIT ?, ?";
			String sql = "{call get_categorias_paginadas(?,?,?)}";
			cs = connect.prepareCall(sql);
			cs.setInt(1, user.getIdUsuario());
			cs.setInt(2, inicio);
			cs.setInt(3, registrosPorPagina);
			rs = cs.executeQuery();
			while(rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setIdUsuario(rs.getInt("idUsuario"));
				categoria.setNombre(rs.getString("nombre"));
				categoria.setTipo(rs.getString("tipo").charAt(0));
				categoria.setMovimientos(repo.get(user, categoria, fecha));
				listaCategorias.add(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(cs != null) {
				cs.close();
			}
			if(rs != null) {
				rs.close();
			}
			if(connect!=null) {
				connect.close();
			}
		}		
		return listaCategorias;
	}
	
	@Override
	public Categoria getCategoriabyId(Usuario user, Categoria categoria, String fecha) throws SQLException {
		RepositoryMovimientoProcedure repo = new RepositoryMovimientoProcedure();
		Categoria category = new Categoria();
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			connect = super.getconnection();
			//String sql = "SELECT * FROM Categoriaspersonalizadas WHERE idUsuario=? and idCategoria=?";
			String sql = "{call get_categoriabyid(?,?)}";
			cs = connect.prepareCall(sql);
			cs.setInt(1, user.getIdUsuario());
			cs.setInt(2, categoria.getIdCategoria());
			rs = cs.executeQuery();
			if(rs.next()) {
				category.setIdCategoria(rs.getInt("idCategoria"));
				category.setIdUsuario(rs.getInt("idUsuario"));
				category.setNombre(rs.getString("nombre"));
				category.setTipo(rs.getString("tipo").charAt(0));
				category.setMovimientos(repo.get(user, categoria, fecha));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(cs != null) {
				cs.close();
			}
			if(rs != null) {
				rs.close();
			}
			if(connect!=null) {
				connect.close();
			}
		}		
		return category;
		
	}
	
	@Override
	public int addCategoriasInicio(String nombre, int idUsuario, String tipo, Connection connect) throws SQLException {
		CallableStatement cs = null;
		ResultSet rsKey = null;
		int idCategoria=0;
		try {
			//String sql = "INSERT INTO Categoriaspersonalizadas(idUsuario, nombre, tipo) VALUE (?,?,?)";
			String sql = "{call add_categorias_inicio(?,?,?,?)}";
			cs = connect.prepareCall(sql);
			cs.setInt(1, idUsuario);
			cs.setString(2, nombre);
			cs.setString(3, tipo);
			cs.registerOutParameter(4, java.sql.Types.INTEGER);
			cs.executeUpdate();

			idCategoria = cs.getInt(4);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(cs != null) {
				cs.close();
			}
			if(rsKey != null) {
				rsKey.close();
			}
		}
		return idCategoria;
	}


	@Override
	public int add(Categoria categoria, Usuario usuario) throws SQLException {
		CallableStatement cs = null;
		ResultSet rsKey = null;
		int idCategoria=0;
		try {
			connect = super.getconnection();
			//String sql = "INSERT INTO Categoriaspersonalizadas(idUsuario, nombre, tipo) VALUE (?,?,?)";
			String sql = "{call add_categoria(?,?,?,?)}";
			cs = connect.prepareCall(sql);
			cs.setInt(1, usuario.getIdUsuario());
			cs.setString(2, categoria.getNombre());
			cs.setString(3, String.valueOf(categoria.getTipo()));
			cs.registerOutParameter(4, java.sql.Types.INTEGER);
			cs.executeUpdate();

			idCategoria = cs.getInt(4);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(cs != null) {
				cs.close();
			}
			if(rsKey != null) {
				rsKey.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
		return idCategoria;
	}

	@Override
	public int modify(Categoria categoria, Usuario usuario) throws SQLException {
		CallableStatement cs = null;
		ResultSet rsKey = null;
		int modificado=0;
		try {
			connect = super.getconnection();
			//String sql = "UPDATE Categoriaspersonalizadas SET nombre=?, tipo=? WHERE idCategoria=? AND idUsuario=?;";
			String sql = "{call modify_categoria(?,?,?,?)}";
			cs = connect.prepareCall(sql);
			cs.setString(1, categoria.getNombre());
			cs.setString(2, String.valueOf(categoria.getTipo()));
			cs.setInt(3, categoria.getIdCategoria());
			cs.setInt(4, categoria.getIdUsuario());
			cs.executeUpdate();
			modificado = cs.getUpdateCount();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(cs != null) {
				cs.close();
			}
			if(rsKey != null) {
				rsKey.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
		return modificado;
	}

	@Override
	public int remove(int id, Usuario usuario) throws SQLException {
		CallableStatement cs = null;
		ResultSet rsKey = null;
		int borrado=0;
		try {
			connect = super.getconnection();
			connect.setAutoCommit(false);
			//String sql = "DELETE FROM Categoriaspersonalizadas WHERE idCategoria=? and idUsuario=?";
			String sql = "{call remove_categoria(?,?)}";
			cs = connect.prepareCall(sql);
			RepositoryMovimientoProcedure repo = new RepositoryMovimientoProcedure();
			repo.removeCategoriaMovimientos(id, usuario.getIdUsuario(), connect);
			cs.setInt(1, id);
			cs.setInt(2, usuario.getIdUsuario());
			cs.executeUpdate();
			borrado =  cs.getUpdateCount();
			connect.commit();
		} catch (SQLException  e) {
			e.printStackTrace();
			if(connect != null) {
				try{
					connect.rollback();
				} catch(SQLException e1) {
					e1.printStackTrace();
					throw e1;
				}
			}
			throw e;
		} finally {
			if (cs != null)
				cs.close();
			if (rsKey != null)
				rsKey.close();
			if (connect != null)
				connect.close();
		}
		return borrado;
	}
	
	@Override
	public int removeAllUser(Usuario usuario, Connection conexion) throws SQLException {
		CallableStatement cs = null;
		int borrado=0;
		try {
			RepositoryMovimientoProcedure repo = new RepositoryMovimientoProcedure();
			repo.removeUsuarioMovimientos(usuario.getIdUsuario(), conexion);
			//String sql = "DELETE FROM Categoriaspersonalizadas WHERE idUsuario=?";
			String sql = "{call remove_all_user(?)}";
			cs = conexion.prepareCall(sql);
			cs.setInt(1, usuario.getIdUsuario());
			cs.executeUpdate();
			borrado = cs.getUpdateCount();
		} catch (SQLException  e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (cs != null)
				cs.close();
		}
		return borrado;
	}
	
	@Override
	public int getNumeroCategorias(Usuario user) throws SQLException {
		CallableStatement cs = null;
		ResultSet rs = null;
		int cantidad=0;
		try {
			connect = super.getconnection();
			//String sql = "SELECT count(*) FROM Categoriaspersonalizadas WHERE idUsuario=?";
			String sql = "{call get_numero_categorias(?)}";
			cs = connect.prepareCall(sql);
			cs.setInt(1, user.getIdUsuario());
			rs = cs.executeQuery();
			if(rs.next()) {
				cantidad = rs.getInt(1);
			}
		} catch (SQLException  e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (cs != null)
				cs.close();
			if (rs != null)
				rs.close();
			if (connect != null)
				connect.close();
		}
		return cantidad;
	}
}
