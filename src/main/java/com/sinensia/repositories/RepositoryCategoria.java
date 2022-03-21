package com.sinensia.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sinensia.contracts.IDaoConUsuario;
import com.sinensia.models.Categoria;
import com.sinensia.models.Usuario;

public class RepositoryCategoria extends RepositoryBaseDatos implements IDaoConUsuario<Categoria,Usuario>{
	
	private Connection connect;

	public List<Categoria> getCategorias(Usuario user, String fecha) throws SQLException{
		RepositoryMovimiento repo = new RepositoryMovimiento();
		List<Categoria> listaCategorias = new ArrayList<Categoria>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connect = super.getconnection();
			String sql = "SELECT * FROM Categoriaspersonalizadas WHERE idUsuario=?";
			pst = connect.prepareStatement(sql);
			pst.setInt(1, user.getIdUsuario());
			rs = pst.executeQuery();
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
			if(pst != null) {
				pst.close();
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
	
	public Categoria getCategoriabyId(Usuario user, Categoria categoria, String fecha) throws SQLException {
		RepositoryMovimiento repo = new RepositoryMovimiento();
		Categoria category = new Categoria();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connect = super.getconnection();
			String sql = "SELECT * FROM Categoriaspersonalizadas WHERE idUsuario=? and idCategoria=?";
			pst = connect.prepareStatement(sql);
			pst.setInt(1, user.getIdUsuario());
			pst.setInt(2, categoria.getIdCategoria());
			rs = pst.executeQuery();
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
			if(pst != null) {
				pst.close();
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
	
	public int addCategoriasInicio(String nombre, int idUsuario, String tipo, Connection connect) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rsKey = null;
		int idCategoria=0;
		try {
			preparedStatement = connect.prepareStatement("INSERT INTO Categoriaspersonalizadas(idUsuario, nombre, tipo) "
					+ "VALUE (?,?,?)",Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, idUsuario);
			preparedStatement.setString(2, nombre);
			preparedStatement.setString(3, tipo);
			preparedStatement.executeUpdate();
			rsKey = preparedStatement.getGeneratedKeys();
			
			rsKey.next();
			idCategoria = rsKey.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(rsKey != null) {
				rsKey.close();
			}
		}
		return idCategoria;
	}


	@Override
	public int add(Categoria categoria, Usuario usuario) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rsKey = null;
		int idCategoria=0;
		try {
			connect = super.getconnection();
			preparedStatement = connect.prepareStatement("INSERT INTO Categoriaspersonalizadas(idUsuario, nombre, tipo) "
					+ "VALUE (?,?,?)",Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, usuario.getIdUsuario());
			preparedStatement.setString(2, categoria.getNombre());
			preparedStatement.setString(3, String.valueOf(categoria.getTipo()));
			preparedStatement.executeUpdate();
			rsKey = preparedStatement.getGeneratedKeys();
			
			rsKey.next();
			idCategoria = rsKey.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(preparedStatement != null) {
				preparedStatement.close();
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
		PreparedStatement preparedStatement = null; 
		ResultSet rsKey = null;
		int modificado=0;
		try {
			connect = super.getconnection();
			preparedStatement = connect.prepareStatement("UPDATE Categoriaspersonalizadas SET nombre=?, tipo=? WHERE idCategoria=? AND idUsuario=?");
			preparedStatement.setString(1, categoria.getNombre());
			preparedStatement.setString(2, String.valueOf(categoria.getTipo()));
			preparedStatement.setInt(3, categoria.getIdCategoria());
			preparedStatement.setInt(4, categoria.getIdUsuario());
			modificado =preparedStatement.executeUpdate();
			System.out.println("hola");
			System.out.println(modificado);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(preparedStatement != null) {
				preparedStatement.close();
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
		PreparedStatement preparedStatement = null; 
		ResultSet rsKey = null;
		int borrado=0;
		try {
			connect = super.getconnection();
			connect.setAutoCommit(false);
			RepositoryMovimiento repo = new RepositoryMovimiento();
			repo.removeCategoriaMovimientos(id, usuario.getIdUsuario(), connect);
			preparedStatement = connect.prepareStatement("DELETE FROM Categoriaspersonalizadas WHERE idCategoria=? and idUsuario=?");
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, usuario.getIdUsuario());
			borrado =preparedStatement.executeUpdate();
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
			if (preparedStatement != null)
				preparedStatement.close();
			if (rsKey != null)
				rsKey.close();
			if (connect != null)
				connect.close();
		}
		return borrado;
	}
	
	public int removeAllUser(Usuario usuario, Connection conexion) throws SQLException {
		PreparedStatement preparedStatement = null; 
		ResultSet rsKey = null;
		int borrado=0;
		try {
			RepositoryMovimiento repo = new RepositoryMovimiento();
			repo.removeUsuarioMovimientos(usuario.getIdUsuario(), conexion);
			preparedStatement = conexion.prepareStatement("DELETE FROM Categoriaspersonalizadas WHERE idUsuario=?");
			preparedStatement.setInt(1, usuario.getIdUsuario());
			borrado =preparedStatement.executeUpdate();
		} catch (SQLException  e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
			if (rsKey != null)
				rsKey.close();
		}
		return borrado;
	}
}
