package beans;

import javax.servlet.http.HttpSession;

import models.Usuario;
import repositories.RepositoryUsuario;
import utilities.conversorSHA256;

public class BeanUsuario {
	
	HttpSession sesion;
	RepositoryUsuario repo;
	
	public void setSesion(HttpSession sesion) {
		this.sesion = sesion;
	}
	
	public BeanUsuario() {
		this.repo = new RepositoryUsuario();
	}
	
	public boolean validarUsuario(String nombre, String pass) throws Exception{
		String passSHA256 = new conversorSHA256().convertirSHA256(pass);
		Usuario user = repo.get(nombre, passSHA256);
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
