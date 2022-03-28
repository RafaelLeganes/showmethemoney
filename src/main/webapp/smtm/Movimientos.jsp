
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Movimientos</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<link href="https://unpkg.com/ionicons@4.5.10-0/dist/css/ionicons.min.css" rel="stylesheet">
	<style type="text/css">
		<%@ include file = "home.css"%>
	</style>
</head>
<body>

<!-- NAVBAR -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="container-fluid me-3">
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>   
	    <a href="Home"><img alt="" src="../images/logo.png" class="img-fluid img me-3"></a>
		<h2><a class="text-dark" href="Home">Show Money</a></h2>
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
	            <li><a class="dropdown-item" href="Cuenta.jsp">Perfil</a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="../cerrar.jsp">Logout</a></li>
	          </ul>
	        </li>	        
	      </ul>
	    </div>
	  </div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="card shadow p-2 mt-4 bg-white rounded">
				<div class="card-header mt-2 bg-light">
					<h2 class="font-weight-bold text-dark text-center">Detalle ${Categoria}</h2>
				</div>
				<div class="card-body">
					<div id="categoria">
						<table class="table table-responsive table-borderless">
						 	<thead>
								<tr>
									<th scope="col">Fecha</th>
									<th scope="col">Nombre</th>
									<th scope="col">Importe</th>
									<th scope="col">Editar</th>
								</tr>
							</thead>
							<c:forEach items="${Lista}" var="movimiento" varStatus="estado">
								<tr>
									<td>${movimiento.fecha}</td>
									<td>${movimiento.nombre}</td>
									<td>${movimiento.importe}€</td>
									<td><button type="button" class="btn btn-sm btn-warning"
											data-bs-toggle="modal"
											data-bs-target="#Editar${movimiento.idMovimiento}"><i class="icon ion-md-create"></i></button>
										<div class="modal" id="Editar${movimiento.idMovimiento}">
											<form method="post">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<h4 class="modal-title">${movimiento.nombre}</h4>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal"></button>
														</div>
														<div class="modal-body">
															<div class="mb-3 mb-3">
																<label for="importe">Importe:</label> <input type="number"
																	step="0.01" class="form-control" required id="id"
																	name="importe" value="${movimiento.importe}">
															</div>
															<div class="mb-3">
																<label for="nombreMovimiento">Nombre:</label> <input type="text"
																	class="form-control" required
																	pattern="[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,64}"
																	id="nombreMovimiento" name="nombreMovimiento" value="${movimiento.nombre}">
															</div>
															<input type="hidden" value="${movimiento.idMovimiento}" name="idMovimiento">
															<input type="hidden" value="${idCategoria}" name="idMovimiento">
															<input type="hidden" value="${Categoria}" name="nombreCategoria">
															<div class="mb-3">
																<label for="fechaimporte">Fecha Ingreso:</label> <input
																	type="date" class="form-control" required id="fechaimporte"
																	value="${movimiento.fecha}" name="fechaimporte">
															</div>
														</div>
														<div class="modal-footer">
															<button type="submit" class="btn btn-primary" name="action"
																value="Modificar">Guardar</button>
															<button type="submit" class="btn btn-danger" name="action"
																value="Eliminar">Eliminar</button>
														</div>
													</div>
												</div>
											</form>
										</div></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>