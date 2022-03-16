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

import com.sinensia.models.Categoria;
import com.sinensia.models.Usuario;
import com.sinensia.service.CategoriaService;
import com.sinensia.service.MovimientoService;


@WebServlet(urlPatterns ="/smtm/Home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CategoriaService serviceCategoria;
	MovimientoService serviceMovimiento;
	
    public HomeController() {
        super();
        serviceCategoria = new CategoriaService();
        serviceMovimiento = new MovimientoService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = (Usuario) request.getSession().getAttribute("USUARIO");
		response.setContentType("text/html;charset=UTF-8");
		request.getParameter("fecha");
		String accion=request.getParameter("accion");
		List<Categoria> listaCategorias = null;
		String fecha = request.getParameter("fecha");
		fecha = serviceCategoria.devolverFecha(fecha, accion);
		try {
			listaCategorias = serviceCategoria.getCategorias(user, fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/smtm/Home.jsp");
		request.setAttribute("Lista", listaCategorias);
		request.setAttribute("fecha", fecha);
		request.setAttribute("mes", serviceCategoria.devolverMes(fecha));
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = (Usuario) request.getSession().getAttribute("USUARIO");
		response.setContentType("text/html;charset=UTF-8");
		String importe =  request.getParameter("importe");
		String nombre = request.getParameter("nombre");
		String categoria = request.getParameter("categoria");
		String fecha = request.getParameter("fechaimporte");
		List<Categoria> listaCategorias = null;
		try {
			serviceMovimiento.add(user, importe, nombre, categoria, fecha);
			listaCategorias = serviceCategoria.getCategorias(user, fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/smtm/Home.jsp");
		request.setAttribute("Lista", listaCategorias);
		request.setAttribute("fecha", fecha);
		request.setAttribute("mes", serviceCategoria.devolverMes(fecha));
		rd.forward(request, response);
	}

}
