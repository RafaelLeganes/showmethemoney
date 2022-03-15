package com.sinensia.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.sinensia.contracts.MovimientoDao;
import com.sinensia.models.Categoria;
import com.sinensia.models.Movimiento;
import com.sinensia.models.Usuario;

public class RepositoryMovimiento extends RepositoryBaseDatos implements MovimientoDao<Movimiento>{
	
	private Connection connect;

	@Override
	public List<Movimiento> get(Usuario user, Categoria categoria, String fecha) throws SQLException {
		List<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
		PreparedStatement pst = null;
		ResultSet rs = null;	
		try {
			connect = super.getconnection();
			String sql = "SELECT * FROM Movimientos WHERE idUsuario=? and idCategoria=? and fecha BETWEEN ? AND LAST_DAY(?)";
			pst = connect.prepareStatement(sql);
			pst.setInt(1, user.getIdUsuario());
			pst.setInt(2, categoria.getIdCategoria());
			pst.setString(3, fecha);
			pst.setString(4, fecha);
			rs = pst.executeQuery();
			while(rs.next()) {
				Movimiento movimiento = new Movimiento();
				movimiento.setIdMovimiento(rs.getInt("idMovimientos"));
				movimiento.setImporte(rs.getFloat("importe"));
				movimiento.setFecha(rs.getDate("fecha").toLocalDate());
				movimiento.setIdCategoria(rs.getInt("idCategoria"));
				movimiento.setIdUsuario(rs.getInt("idUsuario"));
				movimiento.setNombre(rs.getString("nombre"));
				listaMovimientos.add(movimiento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(pst != null) {
				pst.close();
			}
			if(rs != null) {
				rs.close();
			}
			if(connect!=null) {
				connect.close();
			}
		}		
		return listaMovimientos;
	}

	@Override
	public int add(Movimiento movimiento) throws SQLException {
		int idMovimiento = 0;
		PreparedStatement preparedStatement = null; 
		ResultSet rsKey = null;
		try {
			connect = super.getconnection();
			preparedStatement = connect.prepareStatement("INSERT INTO Movimientos(importe, fecha, idCategoria, idUsuario, nombre) "
					+ "VALUE (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setFloat(1, movimiento.getImporte());
			preparedStatement.setDate(2, Date.valueOf(movimiento.getFecha()));
			preparedStatement.setInt(3, movimiento.getIdCategoria());
			preparedStatement.setInt(4, movimiento.getIdUsuario());
			preparedStatement.setString(5, movimiento.getNombre());
			preparedStatement.executeUpdate();
			rsKey = preparedStatement.getGeneratedKeys();
			
			rsKey.next();
			idMovimiento = rsKey.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(rsKey != null) {
				rsKey.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
		return idMovimiento;
	}
	
	@Override
	public int remove(int id) throws SQLException {	
		PreparedStatement preparedStatement = null; 
		ResultSet rsKey = null;
		int borrado=0;
		try {
			connect = super.getconnection();
			preparedStatement = connect.prepareStatement("DELETE FROM Movimientos WHERE idMovimientos=?");
			preparedStatement.setInt(1, id);			
			borrado =preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(rsKey != null) {
				rsKey.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
		return borrado;
	}

	@Override
	public int modify(Movimiento movimiento) throws SQLException {
		PreparedStatement preparedStatement = null; 
		ResultSet rsKey = null;
		int modificado=0;
		try {
			connect = super.getconnection();
			preparedStatement = connect.prepareStatement("UPDATE Movimientos SET importe=?, fecha=?, nombre=? WHERE idMovimientos=?");
			preparedStatement.setFloat(1, movimiento.getImporte());
			preparedStatement.setDate(2, Date.valueOf(movimiento.getFecha()));
			preparedStatement.setString(3, movimiento.getNombre());
			preparedStatement.setInt(4, movimiento.getIdMovimiento());	
			modificado =preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(rsKey != null) {
				rsKey.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
		return modificado;
	}
}
