package repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sinensia.contracts.UserDao;

import models.Usuario;


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
	public int add(Usuario modelo) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
}
