package com.sinensia.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import com.sinensia.contracts.IUsuarios;
import com.sinensia.factories.UsuariosFactory;
import com.sinensia.models.Usuario;
import com.sinensia.utilities.FileResourceUtils;
import com.sinensia.utilities.conversorSHA256;

public class UsuarioService {
	
	IUsuarios repo;
	
	public UsuarioService(String tipo) {
		this.repo = UsuariosFactory.getRepository(tipo);
	}
	
	public Usuario validarUsuario(String nombre, String pass) throws Exception{
		String passSHA256 = new conversorSHA256().convertirSHA256(pass);
		Usuario user = repo.get(nombre, passSHA256);
		return user;
	}

	public Usuario registrarUsuario(String nombre, String correo, String pass) throws Exception {
		String passSHA256 = new conversorSHA256().convertirSHA256(pass);
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setCorreo(correo);
		usuario.setPassword(passSHA256);
		FileResourceUtils app = new FileResourceUtils();
		InputStream is = app.getFileFromResourceAsStream("app.properties");
		Properties appProps = new Properties();
		appProps.load(is);
		try {		
			usuario = repo.add(usuario, appProps);		
		} catch (SQLException ex) {
			if(ex.getMessage().contains("UNIQUE"))
				throw new SQLException("UNIQUE KEY: Este correo ya existe");	
			
		}		
		return usuario;
	}
	
	public Usuario modificarUsuario(String nombre, String correo, String passantigua, String idUsuario, String passnueva) throws Exception {
		String passAntiguaSHA256 = new conversorSHA256().convertirSHA256(passantigua);
		Usuario usuario = new Usuario();
		if(passnueva.length()>0) {
			String passNuevaSHA256 = new conversorSHA256().convertirSHA256(passnueva);
			usuario.setPassword(passNuevaSHA256);
		} else {
			usuario.setPassword(passAntiguaSHA256);
		}
		usuario.setNombre(nombre);
		usuario.setCorreo(correo);
		usuario.setIdUsuario(Integer.valueOf(idUsuario));
		int modificado = 0;
		try {		
			modificado = repo.modify(usuario);
			if(modificado>0) {
				return usuario;
			}
		} catch (SQLException ex) {
			if(ex.getMessage().contains("UNIQUE"))
				throw new SQLException("UNIQUE KEY: Este correo ya existe");	
			
		}		
		return usuario;
	}
	
	public int eliminarUsuario(Usuario user) throws Exception {
		int borrado=0;
		try {
			borrado = repo.remove(user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("No se pudo eliminar el Usuario, intentelo más tarde");
		}
		return borrado;
	}
	
}
