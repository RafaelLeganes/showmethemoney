
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.sinensia.models.Usuario" %>
<jsp:useBean id="bean" class="beans.BeanMovimiento"/>
<%
    bean.setSesion(session);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="divIzquierdoSup">
	        <p> aqui va una imagen</p>
	        <% 
	        Usuario user =  (Usuario)session.getAttribute("USUARIO");
	        %>
	        <p><%=user.getNombre()%></p>
        </div>
        <div id="divDerechoSup">
        	<div id="divDerechoSupizq">
        		<div id="gastos"><span>Gastos</span><button id="bontonGastos" onclick="abrirPopupGastos">-</button></div>
        		<div id="ingresos"><span>Ingresos</span><button id="bontonIngresos" onclick="abrirPopupIngresos">+</button></div>
        	</div>
        	<div id="divDerechoSupdere">
        		<form action="">
        		<button type="submit">Total</button>
        		</form>
        	</div>
        </div>
        <div id="categorias">
        	<% %>
        </div>
    </body>
</html>