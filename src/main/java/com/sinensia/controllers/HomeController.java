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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipoRepo = (String) request.getSession().getAttribute("Repositorio");
		serviceCategoria = new CategoriaService(tipoRepo);
		Usuario user = (Usuario) request.getSession().getAttribute("USUARIO");
		response.setContentType("text/html;charset=UTF-8");
		request.getParameter("fecha");
		String accion=request.getParameter("accion");
		List<Categoria> listaCategoriasPintar = null;
		List<Categoria> listaCategoriasCalculos = null;
		String fecha = request.getParameter("fecha");
		fecha = serviceCategoria.devolverFecha(fecha, accion);
		int page = 1;
		int registrosPorPagina = 6;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int noDePaginas = 0;
		try {
			listaCategoriasPintar = serviceCategoria.getCategoriasPaginadas(user, fecha, (page-1)*registrosPorPagina, registrosPorPagina);
			listaCategoriasCalculos = serviceCategoria.getCategorias(user, fecha);
			int noDeRegistros = serviceCategoria.getNumeroCategorias(user);
			noDePaginas = (int) Math.ceil(noDeRegistros * 1.0 / registrosPorPagina);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Error en BBDD: "+e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/smtm/Home.jsp");
		request.setAttribute("Lista", listaCategoriasPintar);
		request.setAttribute("ListaCalculos", listaCategoriasCalculos);
		request.setAttribute("noDePaginas", noDePaginas);
		request.setAttribute("PaginaActual", page);
		request.setAttribute("fecha", fecha);
		request.setAttribute("mes", serviceCategoria.devolverMes(fecha));
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipoRepo = (String) request.getSession().getAttribute("Repositorio");
		serviceMovimiento = new MovimientoService(tipoRepo);
		serviceCategoria = new CategoriaService(tipoRepo);
		Usuario user = (Usuario) request.getSession().getAttribute("USUARIO");
		response.setContentType("text/html;charset=UTF-8");
		String importe =  request.getParameter("importe");
		String nombre = request.getParameter("nombre");
		String categoria = request.getParameter("categoria");
		String fecha = request.getParameter("fechaimporte");
		List<Categoria> listaCategorias = null;
		List<Categoria> listaCategoriasCalculos = null;
		int page = 1;
		int registrosPorPagina = 6;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int noDePaginas = 0;
		try {
			serviceMovimiento.add(user, importe, nombre, categoria, fecha);
			listaCategorias = serviceCategoria.getCategoriasPaginadas(user, fecha, (page-1)*registrosPorPagina, registrosPorPagina);
			listaCategoriasCalculos = serviceCategoria.getCategorias(user, fecha);
			int noDeRegistros = serviceCategoria.getNumeroCategorias(user);
			noDePaginas = (int) Math.ceil(noDeRegistros * 1.0 / registrosPorPagina);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Error en BBDD: "+e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/smtm/Home.jsp");
		request.setAttribute("Lista", listaCategorias);
		request.setAttribute("ListaCalculos", listaCategoriasCalculos);
		request.setAttribute("noDePaginas", noDePaginas);
		request.setAttribute("PaginaActual", page);
		request.setAttribute("fecha", fecha);
		request.setAttribute("mes", serviceCategoria.devolverMes(fecha));
		rd.forward(request, response);
	}

}
