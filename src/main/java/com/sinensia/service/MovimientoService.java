package com.sinensia.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.sinensia.contracts.IMovimientos;
import com.sinensia.factories.MovimientosFactory;
import com.sinensia.models.Categoria;
import com.sinensia.models.Movimiento;
import com.sinensia.models.Usuario;

public class MovimientoService {

	IMovimientos repo;
	
    public MovimientoService(String tipo) {
        super();
        repo = MovimientosFactory.getRepository(tipo);
    }
    
	public int add(Usuario user, String importe, String nombre, String categoria,  String fecha) throws SQLException{	
		int idMovimiento = 0;
		Movimiento movimiento = new Movimiento();
		movimiento.setImporte(Float.valueOf(importe));
		movimiento.setNombre(nombre);
		movimiento.setIdUsuario(user.getIdUsuario());
		movimiento.setIdCategoria(Integer.valueOf(categoria));
		LocalDate localFecha = LocalDate.parse(fecha);
		movimiento.setFecha(localFecha);
		idMovimiento = repo.add(movimiento);
		return idMovimiento;
	}
	
	public List<Movimiento> get(Usuario user, String categoria,  String fecha) throws SQLException{
		Categoria category = new Categoria();
		category.setIdCategoria(Integer.valueOf(categoria));
		List<Movimiento> listaMovimientos = repo.get(user, category, fecha);
		return listaMovimientos;
	}
	
	public int modify(String importe, String fecha, String nombre, String idMovimiento) throws SQLException{
		int modificado=0;
		Movimiento movimiento = new Movimiento();
		LocalDate localFecha = LocalDate.parse(fecha);
		movimiento.setImporte(Float.valueOf(importe));
		movimiento.setFecha(localFecha);
		movimiento.setNombre(nombre);
		movimiento.setIdMovimiento(Integer.valueOf(idMovimiento));
		modificado = repo.modify(movimiento);
		return modificado;	
	}
	
	public int remove(String id) throws SQLException {
		int borrado=0;
		borrado = repo.remove(Integer.valueOf(id));
		return borrado;
	}
	
	public void redirigirAccion(String accion, String importe, String fecha, String nombre, String idMovimiento) throws SQLException {
		if("Modificar".equals(accion)) {
			modify(importe, fecha, nombre, idMovimiento);
		} else {
			remove(idMovimiento);
		}
	}
}
