
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
		<h2>Detalle ${Categoria}</h2>
	</div>
	<div id="menu">
		<a href="Home">Home</a>
		<a href="#">Categorias</a>
		<a href="#">Cuenta</a> 
		<a href="../cerrar.jsp">Logout</a>
	</div>
	<div id="categoria">
		<table>
			<tr>
				<td>Fecha</td>
				<td>Nombre</td>
				<td>Importe</td>
				<td>Editar</td>
			</tr>
			<c:forEach items="${Lista}" var="movimiento" varStatus="estado">
				<tr>
					<td>${movimiento.fecha}</td>
					<td>${movimiento.nombre}</td>
					<td>${movimiento.importe}€</td>
					<td><button type="button" class="btn btn-primary"
							data-bs-toggle="modal"
							data-bs-target="#Editar${movimiento.idMovimiento}">+</button>
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