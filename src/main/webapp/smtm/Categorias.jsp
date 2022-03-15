
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>JSP Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div id="divIzquierdo">
		<p>aqui va una imagen</p>
		<p>
			<c:out value="${sessionScope.USUARIO.nombre}" />
		</p>
	</div>
	<div id="divDerecho">
		<h2>Categorias</h2>
	</div>
	<div id="menu">
		<a href="Home">Home</a> <a href="#">Categorias</a> <a href="#">Cuenta</a>
		<a href="../cerrar.jsp">Logout</a>
	</div>
	<div id="categoria">
		<div id="insertarCategoria">
			<button type="button" class="btn btn-primary" data-bs-toggle="modal"
				data-bs-target="#modalInsertar">Nueva Categoría</button>
			<div class="modal" id="modalInsertar">
				<form method="post">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Insertar Categoría</h4>
								<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
							</div>
							<div class="modal-body">
								<div class="mb-3">
									<label for="nombreCategoria">Nombre:</label> <input type="text"
										class="form-control" required
										pattern="[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,64}"
										id="nombreCategoria" name="nombreCategoria"
										value="${categoria.nombre}">
								</div>
								<div class="mb-3 ">
									<label for="tipo">Tipo:</label> <select class="form-select"
										name="tipo" required>
										<option value="">Elige tipo</option>
										<option value="G">Gasto</option>
										<option value="I">Ingreso</option>
									</select>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger"
									data-bs-dismiss="modal">Cerrar</button>
								<button type="submit" class="btn btn-primary" name="action"
									value="Insertar">Crear</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<table>
			<tr>
				<td>Nombre</td>
				<td>tipo</td>
			</tr>
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
					<td><button type="button" class="btn btn-primary"
							data-bs-toggle="modal"
							data-bs-target="#Editar${categoria.idCategoria}">+</button>
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
												<label for="nombreCategoria">Nombre:</label> <input
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
											<button type="button" class="btn btn-danger"
												data-bs-dismiss="modal">Cerrar</button>
											<button type="submit" class="btn btn-primary" name="action"
												value="Modificar">Modificar</button>
											<button type="submit" class="btn btn-primary" name="action"
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
</body>
</html>