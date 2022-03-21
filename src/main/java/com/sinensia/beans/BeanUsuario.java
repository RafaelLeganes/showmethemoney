package com.sinensia.beans;

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
	
	public boolean validarUsuario(String nombre, String pass) {
		Usuario user;
		try {
			user = service.validarUsuario(nombre, pass);
			if(user !=null) {
				sesion.setAttribute("USUARIO", user);
				return true;
			}
		} catch (Exception e) {
			sesion.setAttribute("MensajeError", "Error al intentar conectar, pruebe de nuevo más tarde");	
			e.printStackTrace();
		}
		return false;
	}
	
	public void cerrarSesion() {
		sesion.setAttribute("USUARIO", null);
		sesion.invalidate();
	}
	
	
	public boolean registrarUsuario(String nombre, String correo, String pass)  {
		try {		
			Usuario usuario = service.registrarUsuario(nombre, correo, pass);
			if(usuario.getIdUsuario() != 0) {
				sesion.setAttribute("USUARIO", usuario);
				
				if(sesion.getAttribute("MensajeError") != null)
					sesion.removeAttribute("MensajeError");			
				return true;
			}
		} catch (Exception ex) {
			if(ex.getMessage().contains("UNIQUE"))

				sesion.setAttribute("MensajeError", "Este correo ya existe");				
		}	
		return false;
	}	
	
	
	public boolean modificarUsuario(String nombre, String correo, String passantigua, String idUsuario, String passnueva)  {
		try {
			Usuario user = (Usuario) sesion.getAttribute("USUARIO");		
			boolean correcto = validarUsuario(user.getNombre(), passantigua);
			if(correcto){
				Usuario usuario = service.modificarUsuario(nombre, correo, passantigua, idUsuario, passnueva);
				if(usuario.getIdUsuario() != 0) {
					sesion.setAttribute("USUARIO", usuario);			
					if(sesion.getAttribute("MensajeError") != null)
						sesion.removeAttribute("MensajeError");			
					return true;
				}
			} else {
				sesion.setAttribute("MensajeError", "Usuario o contraseña incorrecta");	
			}
		} catch (Exception ex) {
			if(ex.getMessage().contains("UNIQUE"))
				sesion.setAttribute("MensajeError", "Este correo ya existe");				
		}	
		return false;
	}
	
	public boolean eliminarUsuario() {
		Usuario usuario = (Usuario) sesion.getAttribute("USUARIO");
		System.out.println(usuario.getIdUsuario());
		try {		
			int eliminado = service.eliminarUsuario(usuario);
			if(eliminado >0) {
				if(sesion.getAttribute("MensajeError") != null)
					sesion.removeAttribute("MensajeError");	
				return true;
			}
		} catch (Exception ex) {
			sesion.setAttribute("MensajeError", "No se pudo eliminar el Usuario, intentelo más tarde");	
		}	
		return false;
	}	
}
