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
	
	public boolean validarUsuario(String nombre, String pass, String tipoRepo) {
		Usuario user;
		try {
			service = new UsuarioService(tipoRepo);
			user = service.validarUsuario(nombre, pass);
			if(user !=null) {
				sesion.setAttribute("USUARIO", user);
				sesion.setAttribute("Repositorio", tipoRepo);
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
		sesion.setAttribute("Repositorio", null);
		sesion.invalidate();
	}
	
	
	public boolean registrarUsuario(String nombre, String correo, String pass, String tipoRepo)  {
		service = new UsuarioService(tipoRepo);
		try {		
			Usuario usuario = service.registrarUsuario(nombre, correo, pass);
			if(usuario.getIdUsuario() != 0) {
				sesion.setAttribute("USUARIO", usuario);
				sesion.setAttribute("Repositorio", tipoRepo);
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
			String tipoRepo = (String) sesion.getAttribute("Repositorio");
			service = new UsuarioService(tipoRepo);
			boolean correcto = validarUsuario(user.getNombre(), passantigua, tipoRepo);
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
		String tipoRepo = (String) sesion.getAttribute("Repositorio");
		service = new UsuarioService(tipoRepo);
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
