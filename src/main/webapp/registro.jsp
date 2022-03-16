<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
<<<<<<< HEAD
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Registros</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
	<style type="text/css">
		<%@ include file = "../../css/registro.css"%>
	</style>
	
</head>
<body>
	<div class="container-fluid">
	    <div class="row no-gutter"> 	
	        <!-- contenido de la izqda. con imagen -->
	        <div class="col-md-5 d-flex text-center align-items-center">
	        	<img src="images/registro.png" width="600" height="600" class="img-fluid mx-auto">
	        </div>
	        <!-- el contendio de la otra mitad -->
	        <div class="col-md-7">
	            <div class="login d-flex align-items-center py-5">
	                <!-- Demo content-->
	                <div class="container">
	                    <div class="row">
	                        <div class="caja-login col-lg-10 col-xl-6 mx-auto">
	                            <h4 class="display-5 my-4">¡Sonríe! Estás en Show Money</h4>
	                            <form method="post">
	                                <div class="form-group mb-4">
	                                    <input id="inputNombre" type="text" name="nombre" placeholder="Nombre" required class="form-control rounded-pill border-0 shadow-sm px-4">
	                                </div>
	                                <div class="form-group mb-4">
	                                    <input id="inputCorreo" type="email" name="email" placeholder="Correo" required class="form-control rounded-pill border-0 shadow-sm px-4">
	                                </div>
	                                <div class="form-group mb-4">
	                                    <input id="inputPassword" type="password" name="password" placeholder="Password" required class="form-control rounded-pill border-0 shadow-sm px-4 text-primary">
	                                </div>
	                                <div class="form-check">
										<input class="form-check-input" type="checkbox" id="checkAcepto">
										<label class="form-check-label" for="checkAcepto">Condiones y términos</label>
									</div>
	                                <button type="submit" class="btn btn-block mt-5 mb-4 rounded-pill shadow-s">Registrarse</button>
									<div class="d-flex justify-content-center links mb-3">
										¿Ya tienes cuenta? <a href="login.jsp">Login</a>
									</div>

	                            </form>
	                        </div>
	                    </div>
	                </div><!-- End -->
	
	            </div>
	        </div><!-- End -->
	
	    </div>
	</div>

=======
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<jsp:useBean id="bean" class="com.sinensia.beans.BeanUsuario" />
<%
bean.setSesion(session);
%>
<!DOCTYPE html>
<html>
<head>
<title>Registros</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>


</head>
<body>
	<div class="container-fluid">
		<div class="row no-gutter">
			<!-- contenido de la izqda. con imagen -->
			<div class="col-md-5 d-flex text-center align-items-center">
				<img src="images/registro.png" width="600" height="600"
					class="img-fluid mx-auto">
			</div>
			<!-- el contendio de la otra mitad -->
			<div class="col-md-7">
				<div class="login d-flex align-items-center py-5">
					<!-- Demo content-->
					<div class="container">
						<div class="row">
							<div class="caja-login col-lg-10 col-xl-6 mx-auto">
								<h4 class="display-5 my-4">¡Sonríe! Estás en Show Money</h4>
								<form method="post">
									<div class="form-group mb-4">
										<input id="inputNombre" type="text" name="nombre"
											placeholder="Nombre" required
											class="form-control rounded-pill border-0 shadow-sm px-4">
									</div>
									<div class="form-group mb-4">
										<input id="inputCorreo" type="email" name="email"
											placeholder="Correo" required
											class="form-control rounded-pill border-0 shadow-sm px-4">
									</div>
									<div class="form-group mb-4">
										<input id="inputPassword" type="password" name="password"
											placeholder="Password" required
											class="form-control rounded-pill border-0 shadow-sm px-4 text-primary">
									</div>
									<div class="form-check">
										<input class="form-check-input" type="checkbox"
											id="checkAcepto"><label class="form-check-label"
											for="checkAcepto">Condiones y términos</label>
									</div>
									<button type="submit"
										class="btn btn-block mt-5 mb-4 rounded-pill shadow-s">Registrarse</button>
									<div class="d-flex justify-content-center links mb-3">
										¿Ya tienes cuenta? <a href="login.jsp">Login</a>
									</div>
								</form>
								<c:if test="${not empty param.nombre && not empty param.email && not empty param.password}">
									<c:set var="correcto"
										value='<%=bean.registrarUsuario(request.getParameter("nombre"), request.getParameter("email") ,request.getParameter("password"))%>' />
									<c:choose>
										<c:when test="${sessionScope.MensajeError != null}">
											<h2 id='error' style='color: red'>El correo ya existe</h2>
										</c:when>
										<c:otherwise>
											<c:redirect url="smtm/Home" />
										</c:otherwise>
									</c:choose>
								</c:if>
							</div>
						</div>
					</div>
					<!-- End -->

				</div>
			</div>
			<!-- End -->

		</div>
	</div>
>>>>>>> feature/registro
</body>
</html>