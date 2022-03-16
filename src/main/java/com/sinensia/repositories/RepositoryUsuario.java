package com.sinensia.repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;


import com.sinensia.contracts.UserDao;
import com.sinensia.models.Usuario;


public class RepositoryUsuario extends RepositoryBaseDatos implements UserDao<Usuario>{
	
	private Connection connect;
	
	@Override
	public Usuario get(String nombre, String pass) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connect = super.getconnection();
			String sql = "SELECT * FROM Usuarios WHERE nombre=? and password=?";
			pst = connect.prepareStatement(sql);
			pst.setString(1, nombre);
			pst.setString(2, pass);
			rs = pst.executeQuery();
			Usuario user = new Usuario();
			if(rs.next()) {
				user.setIdUsuario(rs.getInt("idUsuario"));
				user.setNombre(rs.getString("nombre"));
				user.setPassword(rs.getString("password"));
				user.setCorreo(rs.getString("correo"));
				rs.close();
				pst.close();
				return user;
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
		return null;
	}



	@Override
	public Usuario add(Usuario usuario, Properties appProps) throws SQLException {
		RepositoryCategoria repo = new RepositoryCategoria();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int idUsuario = 0;
		try {
			connect = super.getconnection();
			connect.setAutoCommit(false);
			String sql = "INSERT INTO Usuarios(nombre, correo, password)" + "VALUE (?, ?, ?)";
			pst = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, usuario.getNombre());
			pst.setString(2, usuario.getCorreo());
			pst.setString(3, usuario.getPassword());
			pst.executeUpdate();
			
			rs = pst.getGeneratedKeys();
			rs.next();
			idUsuario = rs.getInt(1);
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
			if (pst != null)
				pst.close();
			if (rs != null)
				rs.close();
			if (connect != null)
				connect.close();
		}
		usuario.setIdUsuario(idUsuario);
		return usuario;
	}
}
