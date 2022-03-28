package com.sinensia.factories;

import com.sinensia.contracts.IMovimientos;
import com.sinensia.repositories.RepositoryMovimiento;
import com.sinensia.repositoriesprocedures.RepositoryMovimientoProcedure;


public class MovimientosFactory {

	public static IMovimientos getRepository(String repo) {
		
		if(repo.equals("Procedure")) {
			return new RepositoryMovimientoProcedure();
		} else {
			return new RepositoryMovimiento();			
		}
	}
}
