
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="beans.BeanUsuario"/>
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
        <h2>Bienvenido</h2>
        <form method="post">
            <label>NOMBRE </label>
            <input type="text" name="usuario" required/><br/>
            <label>PASSWORD </label>
            <input type="text" name="password" required/><br/>
            <button type="submit">Login</button>
        </form>
        <p>¿Aún no estás registrado?</p>
        <a href="/registro.jsp">Registrarse</a>
        <%
            String user = request.getParameter("usuario");
            String pass = request.getParameter("password");
            if(user != null){
            	try{
	                if(bean.validarUsuario(user, pass) != true){
	                	out.println("<h2 id='error' style='color:red'>Datos incorrectos</h2>");
	                } else {    	
	                	//LE LLEVAMOS A LA ZONA DE SEGURIDAD
	                    response.sendRedirect("smtm/home.jsp");
                	}
            	}catch (Exception e){
                	e.printStackTrace();
                	out.println("<h2 style='color:red'>Se ha producido un error intentelo de nuevo más tarde</h2>");
                }
            }
        %>
    </body>
</html>
