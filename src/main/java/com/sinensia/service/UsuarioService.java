package com.sinensia.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;


import com.sinensia.models.Usuario;
import com.sinensia.repositories.RepositoryUsuario;
import com.sinensia.utilities.FileResourceUtils;
import com.sinensia.utilities.conversorSHA256;

public class UsuarioService {
	
	RepositoryUsuario repo;
	
	public UsuarioService() {
		this.repo = new RepositoryUsuario();
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
				throw new SQLException("UNIQUE KEY: Este correo ya esxite");	
			
		}		
		return usuario;
	}
}
