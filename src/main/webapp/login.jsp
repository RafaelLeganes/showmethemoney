<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="com.sinensia.beans.BeanUsuario" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
bean.setSesion(session);
%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>	
<style type="text/css">
	<%@ include file = "login.css"%>
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
									<div class="form-group mb-4">
										<select class="form-select" id="tipoRepo" name="tipoRepo" required>
											<option value="Procedure">Procedure</option>
											<option value="JDBC">JDBC</option>
										</select>
									</div>
										<c:if test="${not empty param.usuario}">
											<c:set var="correcto"
												value='<%=bean.validarUsuario(request.getParameter("usuario"), request.getParameter("password"), request.getParameter("tipoRepo"))%>' />
											<c:choose>
												<c:when test="${correcto == false}">
													<c:choose>
														<c:when test="${MensajeError != null}">
															<h4 id='error' style='color: red'>Error: ${MensajeError}</h4>
														</c:when>
														<c:otherwise>
															<h4 id='error' style='color: red'>Datos incorrectos: Nombre o contraseña Erroneos</h4>
														</c:otherwise>	
													</c:choose>
												</c:when>
												<c:otherwise>
													<c:redirect url="smtm/Home" />
												</c:otherwise>
											</c:choose>
										</c:if>
									<button type="submit"
										class="btn btn-block mt-5 mb-4 rounded-pill shadow-s">Login</button>
									<div class="d-flex justify-content-center links mb-3">
										<p class="mr-3">No tienes cuenta?</p><a href="registro.jsp">Registrarse</a>
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
</body>
</html>
