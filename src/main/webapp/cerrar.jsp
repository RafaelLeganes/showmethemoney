<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="com.sinensia.beans.BeanUsuario"/>
<% 
	bean.setSesion(session);
	bean.cerrarSesion();
	response.sendRedirect("login.jsp");
%>
