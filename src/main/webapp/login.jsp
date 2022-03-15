<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="com.sinensia.beans.BeanUsuario" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
bean.setSesion(session);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
<style type="text/css">
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row no-gutter">
			<!-- contenido de la izqda. con imagen -->
			<div class="col-md-4 bg-image d-flex text-center align-items-center">
				<img src="images/logo.png" width="200" height="200"
					class="img-fluid mx-auto">
			</div>
			<!-- el contendio de la otra mitad -->
			<div class="col-md-8 bg-light">
				<div class="login d-flex align-items-center py-5">
					<!-- Demo content-->
					<div class="container">
						<div class="row">
							<div class="caja-login col-lg-10 col-xl-6 mx-auto">
								<h3 class="display-5 my-4">¡Sonríe! Estás en Show Money</h3>
								<form method="post">
									<div class="form-group mb-4">
										<input id="inputNombre" type="text" name="usuario"
											placeholder="usuario" required
											class="form-control rounded-pill border-0 shadow-sm px-4">
									</div>
									<div class="form-group mb-4">
										<input id="inputPassword" type="password" name="password"
											placeholder="Password" required
											class="form-control rounded-pill border-0 shadow-sm px-4 text-primary">
									</div>
									<button type="submit"
										class="btn btn-block mt-5 mb-4 rounded-pill shadow-s">Login</button>
									<div class="d-flex justify-content-center links mb-3">
										No tienes cuenta?<a href="registro.jsp">Registrarse</a>

									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- End -->
				</div>
			</div>
			<!-- End -->
		</div>
	</div>
	<c:if test="${not empty param.usuario}">
		<c:set var="correcto"
			value='<%=bean.validarUsuario(request.getParameter("usuario"), request.getParameter("password"))%>' />
		<c:choose>
			<c:when test="${correcto == false}">
				<h2 id='error' style='color: red'>Datos incorrectos</h2>
			</c:when>
			<c:otherwise>
				<c:redirect url="smtm/Home" />
			</c:otherwise>
		</c:choose>
	</c:if>
</body>
</html>
