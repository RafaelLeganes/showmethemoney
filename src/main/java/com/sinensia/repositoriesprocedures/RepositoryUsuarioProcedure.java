package com.sinensia.repositoriesprocedures;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import com.sinensia.contracts.IUsuarios;
import com.sinensia.models.Usuario;
import com.sinensia.repositories.RepositoryBaseDatos;


public class RepositoryUsuarioProcedure extends RepositoryBaseDatos implements IUsuarios{
	
	private Connection connect;
	
	@Override
	public Usuario get(String nombre, String pass) throws SQLException {
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			connect = super.getconnection();
			//String sql = "SELECT * FROM Usuarios WHERE nombre=? and password=?";
			String sql = "{call get_usuario(?,?)}";
			cs = connect.prepareCall(sql);
			cs.setString(1, nombre);
			cs.setString(2, pass);
			rs = cs.executeQuery();
			Usuario user = new Usuario();
			if(rs.next()) {
				user.setIdUsuario(rs.getInt("idUsuario"));
				user.setNombre(rs.getString("nombre"));
				user.setPassword(rs.getString("password"));
				user.setCorreo(rs.getString("correo"));
				return user;
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
		return null;
	}


	@Override
	public Usuario add(Usuario usuario, Properties appProps) throws SQLException {
		RepositoryCategoriaProcedure repo = new RepositoryCategoriaProcedure();
		CallableStatement cs = null;
		ResultSet rs = null;
		int idUsuario = 0;
		try {
			connect = super.getconnection();
			connect.setAutoCommit(false);
			//String sql = "INSERT INTO Usuarios(nombre, correo, password)" + "VALUE (?, ?, ?)";
			String sql = "{call add_usuario(?,?,?,?)}";
			cs = connect.prepareCall(sql);
			cs.setString(1, usuario.getNombre());
			cs.setString(2, usuario.getCorreo());
			cs.setString(3, usuario.getPassword());
			cs.registerOutParameter(4, java.sql.Types.INTEGER);
			cs.executeUpdate();
			
			idUsuario = cs.getInt(4);
			Set<String> listapropiedades = appProps.stringPropertyNames();
			for (Iterator<String> it = listapropiedades.iterator(); it.hasNext();) {
				String nombre = (String) it.next();
				String tipo = appProps.getProperty(nombre);
				repo.addCategoriasInicio(nombre, idUsuario, tipo, connect);
			}
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
			if (rs != null)
				rs.close();
			if (connect != null)
				connect.close();
		}
		usuario.setIdUsuario(idUsuario);
		return usuario;
	}


	@Override
	public int modify(Usuario usuario) throws SQLException {
		CallableStatement cs = null;
		int modificado=0;
		try {
			connect = super.getconnection();
			modificado = usuario.getIdUsuario();
			//String sql = "UPDATE Usuarios SET nombre=?, correo=?, password=? WHERE idUsuario=?";
			String sql = "{call modify_usuario(?,?,?,?)}";
			cs = connect.prepareCall(sql);
			cs.setString(1, usuario.getNombre());
			cs.setString(2, usuario.getCorreo());
			cs.setString(3, usuario.getPassword());
			cs.setInt(4, usuario.getIdUsuario());
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(cs != null) {
				cs.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
		return modificado;
	}

	@Override
	public int remove(Usuario user) throws SQLException {
		CallableStatement cs = null;
		int borrado=0;
		try {
			connect = super.getconnection();
			connect.setAutoCommit(false);
			RepositoryCategoriaProcedure repo = new RepositoryCategoriaProcedure();
			repo.removeAllUser(user, connect);
			//String sql = "DELETE FROM Usuarios WHERE idUsuario=?";
			String sql = "{call remove_usuario(?)}";
			cs = connect.prepareCall(sql);
			cs.setInt(1, user.getIdUsuario());			
			borrado =cs.executeUpdate();;
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
			if (connect != null)
				connect.close();
		}
		return borrado;
	}

	
	
}
