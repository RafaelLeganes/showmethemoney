package beans;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.sinensia.models.Usuario;
import com.sinensia.repositories.RepositoryUsuario;

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
	
	
	public boolean registrarUsuario(String nombre, String correo, String pass) throws Exception {
		
		String passSHA256 = new conversorSHA256().convertirSHA256(pass);
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setCorreo(correo);
		usuario.setPassword(passSHA256);
		
		
		try {		
			usuario = repo.add(usuario);
			
		} catch (SQLException ex) {
			if(ex.getMessage().contains("UNIQUE KEY"))
				sesion.setAttribute("MensajeError", "Este correo ya esxite");	
			
		}
		
		if(usuario.getIdUsuario() != 0) {
			sesion.setAttribute("USUARIO", usuario);
			
			if(sesion.getAttribute("MensajeError") != null)
				sesion.removeAttribute("MensajeError");			
			return true;
		}
		
		return false;
	}	
}
