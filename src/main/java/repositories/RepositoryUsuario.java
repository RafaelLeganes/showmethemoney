package repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Usuario;
import utilities.Conexion;
import utilities.conversorSHA256;

public class RepositoryUsuario {

	public Usuario buscarUsuario(String nombre, String pass) {
		try {
			Connection cn = Conexion.devolverConexion();
			String sql = "SELECT * FROM Usuarios WHERE nombre=? and password=?";
			String passSHA256 = new conversorSHA256().convertirSHA256(pass);
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, nombre);
			pst.setString(2, passSHA256);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Usuario user = new Usuario();
				user.setNombre(rs.getString("nombre"));
				user.setPassword(rs.getString("password"));
				user.setCorreo(rs.getString("correo"));
				return user;
			}
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
