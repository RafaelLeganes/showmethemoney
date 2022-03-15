package com.sinensia.models;

import java.time.LocalDate;

public class Movimiento {
	
	private int idMovimiento;
	private float importe;
	private int idUsuario;
	private int idCategoria;
	private String nombre;
	private LocalDate fecha;
	
	public Movimiento() {
		super();
		
	}

	public Movimiento(int idMovimiento, float importe, int idUsuario, int idCategoria, String nombre, LocalDate fecha) {
		super();
		this.idMovimiento = idMovimiento;
		this.importe = importe;
		this.idUsuario = idUsuario;
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.fecha = fecha;
	}

	public int getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
}
