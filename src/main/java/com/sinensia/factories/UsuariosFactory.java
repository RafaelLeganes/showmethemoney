package com.sinensia.factories;

import com.sinensia.contracts.IUsuarios;
import com.sinensia.repositories.RepositoryUsuario;
import com.sinensia.repositoriesprocedures.RepositoryUsuarioProcedure;

public class UsuariosFactory {

	public static IUsuarios getRepository(String repo) {
		
		if(repo.equals("Procedure")) {
			return new RepositoryUsuarioProcedure();
		} else {
			return new RepositoryUsuario();			
		}
	}
}
