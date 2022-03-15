package com.sinensia.models;

import java.util.List;

public class Categoria {

	private int idUsuario;
	private int idCategoria;
	private String nombre;
	private float importeTotal;
	private char tipo;
	private List<Movimiento> movimientos;
	
	public Categoria(int idUsuario, int idCategoria, String nombre, float importeTotal, char tipo, List<Movimiento> movimientos) {
		super();
		this.idUsuario = idUsuario;
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.movimientos = movimientos;
		this.tipo = tipo;
		this.importeTotal = importeTotal;
	}
	
	public Categoria() {}

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

	public float getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
}
