package com.sinensia.contracts;

import java.sql.SQLException;
import java.util.List;

import com.sinensia.models.Categoria;
import com.sinensia.models.Usuario;

public interface IDaoGetConParametros <T>{

	public List<T> get(Usuario usuario, Categoria categoria, String fecha) throws SQLException;
}
