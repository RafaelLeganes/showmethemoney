package com.sinensia.beans;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.sinensia.models.Usuario;
import com.sinensia.service.UsuarioService;


public class BeanUsuario {
	
	HttpSession sesion;
	UsuarioService service;
	
	public void setSesion(HttpSession sesion) {
		this.sesion = sesion;
	}
	
	public BeanUsuario() {
		this.service = new UsuarioService();
	}
	
	public boolean validarUsuario(String nombre, String pass) throws Exception{
		Usuario user = service.validarUsuario(nombre, pass);
		if(user !=null) {
			sesion.setAttribute("USUARIO", user);
			return true;
		}
		return false;
	}
	
	public void cerrarSesion() {
		sesion.setAttribute("USUARIO", null);
		sesion.invalidate();
	}
	
	
	public boolean registrarUsuario(String nombre, String correo, String pass) throws Exception {
		try {		
			Usuario usuario = service.registrarUsuario(nombre, correo, pass);
			if(usuario.getIdUsuario() != 0) {
				sesion.setAttribute("USUARIO", usuario);
				
				if(sesion.getAttribute("MensajeError") != null)
					sesion.removeAttribute("MensajeError");			
				return true;
			}
		} catch (SQLException ex) {
			if(ex.getMessage().contains("UNIQUE"))

				sesion.setAttribute("MensajeError", "Este correo ya esxite");				
		}	
		return false;
	}	
}
