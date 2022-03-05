package beans;

import javax.servlet.http.HttpSession;

import models.Usuario;
import repositories.RepositoryUsuario;

public class BeanUsuario {
	
	HttpSession sesion;
	RepositoryUsuario repo;
	
	public void setSesion(HttpSession sesion) {
		this.sesion = sesion;
	}
	
	public BeanUsuario() {
		this.repo = new RepositoryUsuario();
	}
	
	public boolean validarUsuario(String nombre, String pass) {
		Usuario user = repo.buscarUsuario(nombre, pass);
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
	
}
