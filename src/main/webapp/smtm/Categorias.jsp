
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>JSP Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	            <li><a class="dropdown-item" href="Cuenta.jsp">Perfil</a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="../cerrar.jsp">Logout</a></li>
	          </ul>
	        </li>	        
	      </ul>
	    </div>
	  </div>
	</nav>

	<div id="categoria">
		<div id="insertarCategoria">	
			<div class="modal" id="modalInsertar">
				<form method="post">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title text-dark">Insertar Categoría</h4>
								<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
							</div>
							<div class="modal-body">
								<div class="mb-3">
									<label class="text-dark" for="nombreCategoria">Nombre:</label> <input type="text"
										class="form-control" required
										pattern="[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,64}"
										id="nombreCategoria" name="nombreCategoria"
										value="${categoria.nombre}">
								</div>
								<div class="mb-3 ">
									<label class="text-dark" for="tipo">Tipo:</label> <select class="form-select"
										name="tipo" required>
										<option value="">Elige tipo</option>
										<option value="G">Gasto</option>
										<option value="I">Ingreso</option>
									</select>
								</div>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary " name="action"
									value="Insertar">Crear</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		
		<!-- 	TABLA DE CATEGORIAS    -->
		<div class="container">
			<div class="row">
				<div class="card shadow p-2 mt-4 bg-white rounded">
					<div class="card-header mt-2 bg-light">
						<h3 class="font-weight-bold text-dark text-center ">CATEGORIAS</h3>	
					</div>
					<div class="card-body">
						<table class="table table-responsive table-borderless text-center">
							<thead>
								<tr>
									<th scope="col">Nombre</th>
      								<th scope="col">Tipo</th>
								</tr>
							</thead>
							<c:forEach items="${Lista}" var="categoria" varStatus="estado">
								<tr>
									<td>${categoria.nombre}</td>
									<c:choose>
										<c:when test="${categoria.tipo eq 'G'.charAt(0)}">
											<td>Gasto</td>
										</c:when>
										<c:otherwise>
											<td>Ingreso</td>
										</c:otherwise>
									</c:choose>
									<td>
										<button type="button" class="btn btn-sm btn-warning"
											data-bs-toggle="modal"
											data-bs-target="#Editar${categoria.idCategoria}"><i class="icon ion-md-create"></i>
										</button>
										<div class="modal" id="Editar${categoria.idCategoria}">
											<form method="post">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<h4 class="modal-title">${categoria.nombre}</h4>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal"></button>
														</div>
														<div class="modal-body">
															<div class="mb-3">
																<label for="nombreCategoria">Nombre:</label> 
																<input
																	type="text" class="form-control" required
																	pattern="[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,64}"
																	id="nombreCategoria" name="nombreCategoria"
																	value="${categoria.nombre}">
															</div>
															<input type="hidden" value="${categoria.idCategoria}"
																name="idCategoria">
															<div class="mb-3 ">
																<label for="tipo">Tipo:</label> <select class="form-select"
																	name="tipo" required>
																	<c:choose>
																		<c:when test="${categoria.tipo eq 'G'.charAt(0)}">
																			<option value="G" selected>Gasto</option>
																			<option value="I">Ingreso</option>
																		</c:when>
																		<c:otherwise>
																			<option value="I" selected>Ingreso</option>
																			<option value="G">Gasto</option>
																		</c:otherwise>
																	</c:choose>
																</select>
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
										</div>
									</td>
								</tr>
							</c:forEach>
							
						</table>
						<div>
							<button type="button" class="btn btn-circle btn-xl btn-warning" data-bs-toggle="modal"
							data-bs-target="#modalInsertar"><i class="icon ion-md-add lead"></i></button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>