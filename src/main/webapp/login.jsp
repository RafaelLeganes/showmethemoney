
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h1>Credenciales de usuario</h1>
        <form method="post">
            <label>Usuario: </label>
            <input type="text" name="usuario" required/><br/>
            <label>Password: </label>
            <input type="text" name="password" required/><br/>
            <button type="submit">Validar Usuario</button>
        </form>
        <%
            String user = request.getParameter("usuario");
            String pass = request.getParameter("password");
            if(user != null){
                if(bean.validarUsuario(user, pass) == true){
                    //LE LLEVAMOS A LA ZONA DE SEGURIDAD
                    response.sendRedirect("smtm/home.jsp");
                } else {
                    out.println("<h1 style='color:red'>Datos incorrectos</h1>");
                }
            }
        %>
    </body>
</html>
