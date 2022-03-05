package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static Connection CONEXION = null;

	
	private Conexion() throws Exception{
		String url = "jdbc:mysql://localhost/showmethemoney";
		String driver = "com.mysql.cj.jdbc.Driver";
		String usuario = "root";
		String password = "root";
		
		try {
			Class.forName(driver);
			CONEXION = DriverManager.getConnection(url,usuario,password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection devolverConexion() throws Exception {
		if(CONEXION == null) {
			new Conexion();
		}
		return CONEXION;
	}
	
}
