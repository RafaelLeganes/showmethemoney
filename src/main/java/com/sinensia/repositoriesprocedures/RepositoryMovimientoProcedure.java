package com.sinensia.repositoriesprocedures;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sinensia.contracts.IMovimientos;
import com.sinensia.models.Categoria;
import com.sinensia.models.Movimiento;
import com.sinensia.models.Usuario;
import com.sinensia.repositories.RepositoryBaseDatos;


public class RepositoryMovimientoProcedure extends RepositoryBaseDatos  implements IMovimientos{
	
	private Connection connect;

	@Override
	public List<Movimiento> get(Usuario user, Categoria categoria, String fecha) throws SQLException{
		List<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			connect = super.getconnection();
			//String sql = "SELECT * FROM Movimientos WHERE idUsuario=? and idCategoria=? and fecha BETWEEN ? AND LAST_DAY(?) order by fecha asc";
			String sql = "{call get_movimientos(?,?,?)}";
			cs = connect.prepareCall(sql);
			cs.setInt(1, user.getIdUsuario());
			cs.setInt(2, categoria.getIdCategoria());
			cs.setString(3, fecha);
			rs = cs.executeQuery();
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
			if(cs != null) {
				cs.close();
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
		CallableStatement cs = null;
		try {
			connect = super.getconnection();
			//String sql = "INSERT INTO Movimientos(importe, fecha, idCategoria, idUsuario, nombre) VALUE (?,?,?,?,?)";
			String sql = "{call add_movimiento(?,?,?,?,?,?)}";
			cs = connect.prepareCall(sql);
			cs.setFloat(1, movimiento.getImporte());
			cs.setDate(2, Date.valueOf(movimiento.getFecha()));
			cs.setInt(3, movimiento.getIdCategoria());
			cs.setInt(4, movimiento.getIdUsuario());
			cs.setString(5, movimiento.getNombre());
			cs.registerOutParameter(6, java.sql.Types.INTEGER);
			cs.executeUpdate();
			
			idMovimiento = cs.getInt(6);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(cs != null) {
				cs.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
		return idMovimiento;
	}
	
	@Override
	public int remove(int id) throws SQLException {
		CallableStatement cs = null;
		int borrado=0;
		try {
			connect = super.getconnection();
			//String sql = "DELETE FROM Movimientos WHERE idMovimientos=?";
			String sql = "{call remove_movimiento(?)}";
			cs = connect.prepareCall(sql);
			cs.setInt(1, id);
			cs.executeUpdate();
			borrado =  cs.getUpdateCount();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(cs != null) {
				cs.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
		return borrado;
	}

	@Override
	public int modify(Movimiento movimiento) throws SQLException {
		CallableStatement cs = null;
		int modificado=0;
		try {
			connect = super.getconnection();
			//String sql = "UPDATE Movimientos SET importe=?, fecha=?, nombre=? WHERE idMovimientos=?";
			String sql = "{call modify_movimiento(?,?,?,?)}";
			cs = connect.prepareCall(sql);
			cs.setFloat(1, movimiento.getImporte());
			cs.setDate(2, Date.valueOf(movimiento.getFecha()));
			cs.setString(3, movimiento.getNombre());
			cs.setInt(4, movimiento.getIdMovimiento());
			cs.executeUpdate();
			modificado = cs.getUpdateCount();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(cs != null) {
				cs.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
		return modificado;
	}
	
	@Override
	public int removeCategoriaMovimientos(int idCategoria, int idUsuario, Connection connect) throws SQLException {
		CallableStatement cs = null;
		int borrado=0;
		try {
			//String sql = "DELETE FROM Movimientos WHERE idCategoria=? AND idUsuario=?";
			String sql = "{call remove_categoria_movimientos(?,?)}";
			cs = connect.prepareCall(sql);
			cs.setInt(1, idCategoria);
			cs.setInt(2, idUsuario);
			cs.executeUpdate();
			borrado = cs.getUpdateCount();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(cs != null) {
				cs.close();
			}
		}
		return borrado;
	}
	
	@Override
	public int removeUsuarioMovimientos(int idUsuario, Connection connect) throws SQLException {
		CallableStatement cs = null;
		int borrado=0;
		try {
			//String sql = "DELETE FROM Movimientos WHERE idUsuario=?";
			String sql = "{call remove_usuario_movimientos(?)}";
			cs = connect.prepareCall(sql);
			cs.setInt(1, idUsuario);
			cs.executeUpdate();
			borrado = cs.getUpdateCount();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(cs != null) {
				cs.close();
			}
		}
		return borrado;
	}
}
