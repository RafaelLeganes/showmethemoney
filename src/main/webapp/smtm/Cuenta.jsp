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
	<link href="https://unpkg.com/ionicons@4.5.10-0/dist/css/ionicons.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<style type="text/css">
		<%@ include file = "home.css"%>
	</style>

</head>
<body>
				<!-- NAVBAR -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light shadow">
	  <div class="container-fluid me-3">
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <a href="Home"><img alt="" src="../images/logo.png" class="img-fluid img me-3"></a>
		<h2 ><a class="text-dark" href="Home">Show Money</a></h2>	
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav ms-auto me-5">
	        <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	          	<img alt="" src="../images/user.png" class="img-fluid img me-3">
				<c:out value="${sessionScope.USUARIO.nombre}" />		
	          </a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="Home">Home</a></li>
	            <li><a class="dropdown-item" href="Categorias">Categorias</a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="../cerrar.jsp">Logout</a></li>
	          </ul>
	        </li>	        
	      </ul>
	    </div>
	  </div>
	</nav>
	
		<div class="w-100 my-2">
			
			<div class="registro d-flex">
				<!-- INFORMACIÓN USUARIO-->
				<div class="container">
					<div class="row">
						<div class="col-lg-10 mx-auto">
							<form method="post">
								<section>
									<div class="container border-bottom">
										<div class="row">
											<div class=" me-2">
												<h5 class="text-color">Información</h5>
												<h6 class="text-dark">Aquí puedes cambiar tu correo o tu nombre</h6>
											</div>
								
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
												
												<div class="form-group mb-2">
													<input id="inputPasswordold" type="password" name="oldpassword"
														placeholder="Indroduce Contraseña" required
														class="form-control rounded-pill border-0 shadow-sm px-4 text-primary" value="">
												</div>
												<button type="submit" class="btn btn-outline-secondary btn-modificar btn-sm my-3 rounded-pill"
												value="Modificar" name="action">Modificar</button>
										
										</div>
									</div>
										
								</section>		
			 
				<!--MODIFICACIÓN CONTRASEÑA  -->
				
								<section>
									<div class="container border-bottom">
										<div class="row">
											<div class=" me-2 mt-1" >
												<h5 class="text-color">Password</h5>
												<h6 class="text-dark">Cambia tu contraseña</h6>
											</div>
													
											<button type="button" class="btn btn-outline-success btn-modificar btn-sm rounded-pill my-1" data-bs-toggle="collapse" data-bs-target="#newpassword" aria-expanded="false"
											 aria-controls="newpassword">Modificar contraseña</button>
											
											<div id="newpassword" class="my-2 collapse">
												<input id="inputPasswordnew" type="password" name="newpassword"
													placeholder="Indroduce Nueva Contraseña" 
													class="form-control rounded-pill border-0 shadow-sm px-4 text-primary" value="">
											</div>
								
										</div>
									</div>
										
								</section>	
								
							<!-- ELIMINACIÓN CUENTA -->	
			 					<section>
									<div class="container border-bottom">
										<div class="row">
											<div class=" me-2 mt-1">
												<h5 class="text-color">Eliminar cuenta</h5>
												<h6 class="text-dark">No nos abandones</h6>
											</div>
									

									<input type="hidden" value="${sessionScope.USUARIO.idUsuario}"
												name="idUsuario">
									
									<button type="submit" class="btn btn-outline-danger btn-modificar btn-sm my-3 rounded-pill " 
									value="Eliminar" name="action">Eliminar</button>
									
								
										</div>
									</div>
										
								</section>			
									
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
	</body>
</html>