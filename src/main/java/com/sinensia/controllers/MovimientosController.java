package com.sinensia.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinensia.models.Movimiento;
import com.sinensia.models.Usuario;
import com.sinensia.service.CategoriaService;
import com.sinensia.service.MovimientoService;


@WebServlet(urlPatterns ="/smtm/Movimientos")
public class MovimientosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	MovimientoService serviceMovimiento;
	CategoriaService serviceCategoria;

    public MovimientosController() {
        super();
        serviceMovimiento = new MovimientoService();
        serviceCategoria = new CategoriaService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = (Usuario) request.getSession().getAttribute("USUARIO");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String fecha =request.getParameter("fecha");
		fecha = serviceCategoria.devolverFecha(fecha,null);
		String idCategoria = request.getParameter("idCategoria");
		String categoriaNombre = request.getParameter("nombre");
		List<Movimiento> listaMovimientos = null;
		try {
			listaMovimientos = serviceMovimiento.get(user, idCategoria, fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/smtm/Movimientos.jsp");
		request.setAttribute("Lista", listaMovimientos);
		request.setAttribute("Categoria", categoriaNombre);
		request.setAttribute("idCategoria", idCategoria);
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = (Usuario) request.getSession().getAttribute("USUARIO");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String accion = request.getParameter("action");
		String importe = request.getParameter("importe");
		String fecha = request.getParameter("fechaimporte");
		String nombre = request.getParameter("nombreMovimiento");
		String idMovimiento = request.getParameter("idMovimiento");
		String idCategoria = request.getParameter("idCategoria");
		String categoriaNombre = request.getParameter("nombreCategoria");
		try {
			serviceMovimiento.redirigirAccion(accion, importe, fecha, nombre, idMovimiento);
			List<Movimiento> listaMovimientos = null;
			listaMovimientos = serviceMovimiento.get(user, idCategoria, fecha);
			RequestDispatcher rd = request.getRequestDispatcher("/smtm/Movimientos.jsp");
			request.setAttribute("Lista", listaMovimientos);
			request.setAttribute("Categoria", categoriaNombre);
			request.setAttribute("idCategoria", idCategoria);
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
