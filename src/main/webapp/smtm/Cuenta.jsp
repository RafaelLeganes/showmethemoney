<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<jsp:useBean id="bean" class="com.sinensia.beans.BeanUsuario" />
<%
bean.setSesion(session);
%>
<!DOCTYPE html>
<html>
<head>
<title>Perfil</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<style type="text/css">
	<%@ include file = "home.css"%>
</style>

</head>
<body>
	<div class="d-flex">
		<div id="contenedor-perfil" class="bg-primary">		
			<!-- Sidebar -->
			<div class="logo border-bottom">
				<img alt="" src="../images/usuario.png" width="100" height="100" class="img-fluid">
				<p class="mt-2">
					<c:out value="${sessionScope.USUARIO.nombre}" />
				</p>	
			</div>
			<div class="menu">
				<a href="Home" class="d-block text-light p-3">Home</a>
				<a href="Categorias" class="d-block text-light p-3">Categorias</a> 
				<a href="../cerrar.jsp" class="d-block text-light p-3">Logout</a>
			</div>
		</div>
		<!-- el contendio de la otra mitad -->
		<div class="col-md-7">
			<div class="registro d-flex align-items-center py-5">
				<!-- Demo content-->
				<div class="container">
					<div class="row">
						<div class="col-lg-10 col-xl-6 mx-auto">
							<h4 class="display-5 my-4">¡Sonríe! Estás en Show Money</h4>
							<form method="post">
								<div class="form-group mb-4">
									<input id="inputNombre" type="text" name="nombre"
										placeholder="${sessionScope.USUARIO.nombre}" required
										class="form-control rounded-pill border-0 shadow-sm px-4" value="${sessionScope.USUARIO.nombre}">									
								</div>
								<div class="form-group mb-4">
										<input id="inputCorreo" type="email" name="email"
											placeholder="${sessionScope.USUARIO.correo}" required
											class="form-control rounded-pill border-0 shadow-sm px-4" value="${sessionScope.USUARIO.correo}">
									</div>
									<div class="form-group mb-4">
										<input id="inputPasswordold" type="password" name="oldpassword"
											placeholder="Indroduce Contraseña" required
											class="form-control rounded-pill border-0 shadow-sm px-4 text-primary" value="">
									</div>
									<button type="button" class="btn btn-primary" data-bs-toggle="collapse" data-bs-target="#newpassword" aria-expanded="false"
									 aria-controls="newpassword">Modificar contraseña</button>
									
									<div id="newpassword" class="mb-4 collapse">
										<input id="inputPasswordnew" type="password" name="newpassword"
											placeholder="Indroduce Nueva Contraseña" 
											class="form-control rounded-pill border-0 shadow-sm px-4 text-primary" value="">
									</div>
									<input type="hidden" value="${sessionScope.USUARIO.idUsuario}"
												name="idUsuario">
									<button type="submit" class="btn btn-block mt-5 mb-4 rounded-pill shadow-s" 
									value="Modificar" name="action">Modificar</button>
									<button type="submit" class="btn btn-danger mt-5 mb-4 rounded-pill shadow-s " 
									value="Eliminar" name="action">Eliminar Cuenta</button>
								</form>
								<c:choose>
									<c:when test="${param.action eq 'Modificar'}">
										<c:set var="correcto"
											value='<%=bean.modificarUsuario(request.getParameter("nombre"),  
													request.getParameter("email"), request.getParameter("oldpassword"), 
													request.getParameter("idUsuario"), request.getParameter("newpassword"))%>' />
										<c:choose>
											<c:when test="${correcto == false}">
												<h2 id='error' style='color: red'>Se ha producido un Error: ${MensajeError}</h2>
											</c:when>
											<c:otherwise>
													<h2 id='correcto' style='green: red'>Usuario Modificado con éxito</h2>
													<c:redirect url="Cuenta.jsp" />
													<script type="text/javascript">
														var h2Error = document.getElementById("error");
														h2Error.innerHTML = "";
													</script>												
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:if test="${param.action eq 'Eliminar'}">
											<c:set var="correcto" value='<%=bean.eliminarUsuario()%>'/>
											<c:choose>
												<c:when test="${correcto == false}">
													<h2 id='error' style='color: red'>Se ha producido un Error: ${MensajeError}</h2>
												</c:when>
												<c:otherwise>
													<c:redirect url="../cerrar.jsp" />
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<!-- End -->
				</div>
			</div>
			<!-- End -->
	</div>
</body>
</html>