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


@WebServlet(urlPatterns ="/smtm/Categorias")
public class CategoriasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	CategoriaService serviceCategoria;
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipoRepo = (String) request.getSession().getAttribute("Repositorio");
		serviceCategoria = new CategoriaService(tipoRepo);
		Usuario user = (Usuario) request.getSession().getAttribute("USUARIO");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		List<Categoria> listaCategorias = null;
		String fecha = request.getParameter("fecha");
		fecha = serviceCategoria.devolverFecha(fecha,null);
		try {
			listaCategorias = serviceCategoria.getCategorias(user, fecha);
			RequestDispatcher rd = request.getRequestDispatcher("/smtm/Categorias.jsp");
			request.setAttribute("Lista", listaCategorias);
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Error en BBDD: "+e.getMessage());
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipoRepo = (String) request.getSession().getAttribute("Repositorio");
		serviceCategoria = new CategoriaService(tipoRepo);
		Usuario user = (Usuario) request.getSession().getAttribute("USUARIO");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String accion = request.getParameter("action") ;
		String nombre = request.getParameter("nombreCategoria") ;
		String idCategoria = request.getParameter("idCategoria");
		String tipo = request.getParameter("tipo");
		try {
			serviceCategoria.redirigirAccion(user, accion, nombre, tipo, idCategoria);
			List<Categoria> listaCategorias = null;
			String fecha = request.getParameter("fecha");
			fecha = serviceCategoria.devolverFecha(fecha,null);
			listaCategorias = serviceCategoria.getCategorias(user, fecha);
			RequestDispatcher rd = request.getRequestDispatcher("/smtm/Categorias.jsp");
			request.setAttribute("Lista", listaCategorias);
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Error en BBDD: "+e.getMessage());
		}
	}

}
