<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.sinensia.models.Usuario"%>
<%@page import="com.sinensia.models.Categoria"%>
<!DOCTYPE html>
<html>
<head>
<title>JSP Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div id="divIzquierdo">
		<p>aqui va una imagen</p>
		<p>
			<c:out value="${sessionScope.USUARIO.nombre}" />
		</p>
	</div>
	<div id="divDerecho">
		<div id="EnvioFecha">
			<a id="mesAnterior" href="Home?fecha=${fecha}&accion=anterior">Aquí una imagen de flechita </a><span id="mes">${mes}</span><a id="mesSiguiente" href="Home?fecha=${fecha}&accion=siguiente"> Aquí otra imagen de flechita</a>
		</div>
		<div id="importes">
			<div id="divIngresos">
				<p>Ingresos</p>
				<p id="ingresos"></p>
			</div>
			<div id="divGastos">
				<p>Gastos</p>
				<p id="gastos"></p>
			</div>
			<div id="divTotal">
				<p>Total</p>
				<p id="total"></p>
			</div>
		</div>
	</div>
	<div id="menu">
		<a href="Categorias">Categorias</a> <a href="#">Cuenta</a> <a href="../cerrar.jsp">Logout</a>
	</div>
	<div id="categorias">
		<div id="listaCategorias">
			<table>
				<tr>
					<td>Categoria</td>
					<td>Importe</td>
				</tr>
				<c:set var="gastos" value="${0}" />
				<c:set var="ingresos" value="${0}" />
				<c:set var="total" value="${0}" />
				<c:forEach items="${Lista}" var="categoria" varStatus="estado">
					<tr>
						<td><a href="Movimientos?idCategoria=${categoria.idCategoria}&nombre=${categoria.nombre}&fecha=${fecha}">${categoria.nombre}</a></td>
						<td>${categoria.importeTotal}€"</td>
						<c:choose>
							<c:when test="${categoria.tipo eq 'G'.charAt(0)}">
								<c:set var="gastos" value="${gastos+categoria.importeTotal}" />
							</c:when>
							<c:otherwise>
								<c:set var="ingresos" value="${ingresos+categoria.importeTotal}" />
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
				<c:set var="total" value="${ingresos-gastos}" />
			</table>
			<script type="text/javascript">
				var ingresos = (<c:out value="${ingresos}"/>).toFixed(2);
				var gastos = (<c:out value="${gastos}"/>).toFixed(2);
				var total = (<c:out value="${total}"/>).toFixed(2);
				document.getElementById("ingresos").innerHTML = ingresos + "€";
				document.getElementById("gastos").innerHTML = gastos + "€";
				document.getElementById("total").innerHTML = total + "€";
			</script>
		</div>
		<div id="acciones">
			<div class="container">
				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">+</button>
				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal2">-</button>
				<div class="modal" id="myModal">
					<form method="post">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Ingreso</h4>
									<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
								</div>
								<div class="modal-body">
									<div class="mb-3 mb-3">
										<label for="importe">Importe:</label> <input type="number" step="0.01"
											class="form-control" required id="id"
											placeholder="Introduce Importe" name="importe">
									</div>
									<div class="mb-3">
										<label for="nombre">Nombre Ingreso:</label> <input type="text"
											class="form-control" required pattern="[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,64}" id="nombre"
											placeholder="Introduce Nombre" name="nombre">
									</div>
									<div class="mb-3 ">
										<label for="categoria">Categoría:</label> 
										<select class="form-select" name="categoria" required>
											<option value="">Elige Categoría</option>
											<c:forEach items="${Lista}" var="categoria"
												varStatus="estado">
												<c:if test="${categoria.tipo eq 'I'.charAt(0)}">
													<option value="${categoria.idCategoria}">${categoria.nombre}</option>
												</c:if>
											</c:forEach>
										</select>
									</div>
									<div class="mb-3">
										<label for="fechaimporte">Fecha Ingreso:</label> <input
											type="date" class="form-control" required
											id="fechaimporte" placeholder="dd/mm/aaaa"
											name="fechaimporte">
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
								    <button type="submit" class="btn btn-primary">Insertar</button>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal" id="myModal2">
					<form method="post">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Gasto</h4>
									<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
								</div>
								<div class="modal-body">
									<div class="mb-3 mb-3">
										<label for="importe">Importe:</label> <input type="number" step="0.01"
											class="form-control" required id="id"
											placeholder="Introduce Importe" name="importe">
									</div>
									<div class="mb-3">
										<label for="nombre">Nombre Gasto:</label> <input type="text"
											class="form-control" required pattern="[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,64}" id="nombre"
											placeholder="Introduce Nombre" name="nombre">
									</div>
									<div class="mb-3 ">
										<label for="categoria">Categoría:</label> 
										<select class="form-select" name="categoria" required>
											<option value="">Elige Categoría</option>
											<c:forEach items="${Lista}" var="categoria"
												varStatus="estado">
												<c:if test="${categoria.tipo eq 'G'.charAt(0)}">
													<option value="${categoria.idCategoria}">${categoria.nombre}</option>
												</c:if>
											</c:forEach>
										</select>
									</div>
									<div class="mb-3">
										<label for="fechaimporte">Fecha Gasto:</label> <input
											type="date" class="form-control" required
											id="fechaimporte" placeholder="dd/mm/aaaa"
											name="fechaimporte">
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
								    <button type="submit" class="btn btn-primary">Insertar</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>