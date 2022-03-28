package com.sinensia.factories;

import com.sinensia.contracts.ICategorias;
import com.sinensia.repositories.RepositoryCategoria;
import com.sinensia.repositoriesprocedures.RepositoryCategoriaProcedure;

public class CategoriasFactory {
	
	public static ICategorias getRepository(String repo) {
		
		if(repo.equals("Procedure")) {
			return new RepositoryCategoriaProcedure();
		} else {
			return new RepositoryCategoria();			
		}
	}
}
