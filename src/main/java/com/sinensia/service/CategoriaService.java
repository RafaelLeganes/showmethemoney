package com.sinensia.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import com.sinensia.models.Categoria;
import com.sinensia.models.Movimiento;
import com.sinensia.models.Usuario;
import com.sinensia.repositories.RepositoryCategoria;

public class CategoriaService {
	
	RepositoryCategoria repo;
	
    public CategoriaService() {
        super();
        repo = new RepositoryCategoria();
    }

	public List<Categoria> getCategorias(Usuario user, String fecha) throws SQLException{	
		List<Categoria> listaCategorias = null;
		String dia = devolverFecha(fecha,null);
		listaCategorias = repo.getCategorias(user, dia);
		listaCategorias = getImporte(listaCategorias);
		return listaCategorias;
	}
	
	public List<Categoria> getImporte(List<Categoria> listaCategorias){
		for(Categoria categoria : listaCategorias) {
			float total =0;
			for(Movimiento movimiento : categoria.getMovimientos()) {
				total += movimiento.getImporte();
			}
			categoria.setImporteTotal(total);
		}
		return listaCategorias;
	}
	
	public String devolverFecha(String fecha, String accion) {
		String dia = null;
		LocalDate day;
		if(fecha == null) {
			day = LocalDate.now();
			day = day.withDayOfMonth(1);
			dia = day.toString();
		} else {
			if(accion !=null) {
				if (accion.equals("anterior")) {
					day = LocalDate.parse(fecha);
					day = day.minusMonths(1);
					day = day.withDayOfMonth(1);
					dia = day.toString();
				} else {
					day = LocalDate.parse(fecha);
					day = day.plusMonths(1);
					day = day.withDayOfMonth(1);
					dia = day.toString();
				}
			} else {
				day = LocalDate.parse(fecha);
				day = day.withDayOfMonth(1);
				dia = day.toString();
			}
		}
		return dia;
	}
	
	public String devolverMes(String fecha) {
		LocalDate mounth =  LocalDate.parse(fecha);
		Month mes = mounth.getMonth();
		String nombre = mes.getDisplayName(TextStyle.FULL, new Locale ("es", "ES"));
		return nombre.toUpperCase();
	}
	
	public int add(Usuario user, String nombre, String tipo) throws SQLException {
		Categoria categoria = new Categoria();
		int catId = 0;
		categoria.setIdUsuario(user.getIdUsuario());
		categoria.setNombre(nombre);
		categoria.setTipo(tipo.charAt(0));
		catId =repo.add(categoria, user);
		return catId;
	}
	
	public int modify(Usuario user, String nombre, String tipo, String id) throws SQLException {
		Categoria categoria = new Categoria();
		int catId = 0;
		categoria.setIdUsuario(user.getIdUsuario());
		categoria.setNombre(nombre);
		categoria.setTipo(tipo.charAt(0));
		categoria.setIdCategoria(Integer.valueOf(id));
		catId =repo.modify(categoria, user);
		return catId;
	}
	
	public int remove(String id, Usuario user) throws NumberFormatException, SQLException {
		int catId = 0;
		catId =repo.remove(Integer.valueOf(id), user);
		return catId;
	}
	
	public void redirigirAccion(Usuario user, String accion, String nombre, String tipo, String id) throws SQLException {
		if("Modificar".equals(accion)) {
			modify(user, nombre, tipo, id);
		} else if("Insertar".equals(accion)){
			add(user, nombre, tipo);
		} else {
			remove(id, user);
		}
	}
}
