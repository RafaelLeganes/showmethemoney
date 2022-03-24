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
  	<link href="https://unpkg.com/ionicons@4.5.10-0/dist/css/ionicons.min.css" rel="stylesheet">
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>	
  	<style type="text/css">
		<%@ include file = "home.css"%>
	</style>
</head>
<body>
	<div class="d-flex">
		<div id="contenedor-perfil" class="color-sidebar">		
			<!-- Sidebar -->
			<div class="logo border-bottom bg-primary">
				<img alt="" src="../images/usuario.png" width="75" height="75" class="img-fluid">
				<p class="mt-3">
					<c:out value="${sessionScope.USUARIO.nombre}" />
				</p>	
			</div>
			<div class="menu">
				<a href="Categorias" class="d-block text-dark p-3"><i class="icon ion-md-apps iconos-sidebar lead
				"></i>Categorias</a> 
				<a href="Cuenta.jsp" class="d-block text-dark p-3"><i class="icon ion-md-person lead iconos-sidebar "></i>Perfil</a> 
				<a href="../cerrar.jsp" class="d-block text-dark p-3"><i class="icon ion-md-power iconos-sidebar lead"></i>Logout</a>
			</div>
		</div>
		<!-- Cuentas -->
			<div class="w-100">
					<section>
						<div class="container">
							<div class="row">
								<div class="col-lg-12 text-center my-2">
									<a id="mesAnterior" href="Home?fecha=${fecha}&accion=anterior"><i class="icon ion-md-arrow-round-back text-dark flecha-izqda"></i></a><span id="mes" class="text-dark mes">${mes}</span><a id="mesSiguiente" href="Home?fecha=${fecha}&accion=siguiente"><i class="icon ion-md-arrow-round-forward text-dark flecha-drcha"></i></a>	
								</div>							
							</div>
						</div>
						<div class="container">
							<div class="card shadow p-3 my-1 bg-white rounded border-0">
								<div class="card-body ">
									<div class="row">
										<div class="col-lg-4 d-flex estado my-3">
											<div class="mx-auto"> 
												<h4 class="text-dark">Ingresos</h4>
												<span id="ingresos" class="text-success"></span>
											</div>
										</div>
										<div class="col-lg-4 d-flex estado my-3">
											<div class="mx-auto">
												<h4 class="text-dark">Gastos</h4>
												<span id="gastos" class="text-danger"></span>
											</div>
										</div>
										<div class="col-lg-4 d-flex my-3">
											<div class="mx-auto">
												<h4 class="font-weight-bold text-dark">Total</h4>
												<span id="total" class="text-dark"></span>
											</div>
										</div>	
									</div>
								</div>
							</div>	
						</div>
					</section>
			
				
				<section class="py-5">
					<div class="container"> 
						<div class="row">
							<div class="col-lg-12">
								<div class="card shadow p-2 my-2 bg-white rounded border-0">
									<div class="card-body">
										<div id="categorias">
											<div id="listaCategorias" >
												<table class="table table-responsive table-borderless">
													<thead>
														<tr>
															<th scope="col">Categorías</th>
      														<th class="texto" scope="col">Importes</th>
														</tr>
													</thead>
													
													<c:forEach items="${Lista}" var="categoria" varStatus="estado">
														<tr>
															<c:choose>
																<c:when test="${categoria.tipo eq 'G'.charAt(0)}">
																	<td><a class="text-dark" id="rojo" href="Movimientos?idCategoria=${categoria.idCategoria}&nombre=${categoria.nombre}&fecha=${fecha}">${categoria.nombre}</a></td>
																</c:when>
																<c:otherwise>
																	<td><a class="text-dark" id="verde" href="Movimientos?idCategoria=${categoria.idCategoria}&nombre=${categoria.nombre}&fecha=${fecha}">${categoria.nombre}</a></td>
																</c:otherwise>
															</c:choose>
															<td class="text-dark texto">${categoria.importeTotal}€"</td>
														</tr>
													</c:forEach>	
												</table>
												<c:set var="gastos" value="${0}" />
												<c:set var="ingresos" value="${0}" />
												<c:set var="total" value="${0}" />
												<c:forEach items="${ListaCalculos}" var="categoriacal" varStatus="estado">
													<c:choose>
														<c:when test="${categoriacal.tipo eq 'G'.charAt(0)}">
															<c:set var="gastos" value="${gastos+categoriacal.importeTotal}" />
														</c:when>
														<c:otherwise>
															<c:set var="ingresos" value="${ingresos+categoriacal.importeTotal}" />
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:set var="total" value="${ingresos-gastos}" />
												<c:if test="${PaginaActual != 1}">
													<td><a href="Home?page=${PaginaActual-1}&fecha=${fecha}">Anterior</a></td>
												</c:if>
												<table border="1" cellpadding="5" cellspacing="5">
													<tr>
														<c:forEach begin="1" end="${noDePaginas}" var="i">
															<c:choose>
																<c:when test="${PaginaActual eq i}">
																	<td>${i}</td>
																</c:when>
																<c:otherwise>
																	<td><a href="Home?page=${i}&fecha=${fecha}">${i}</a></td>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</tr>
												</table>
												<c:if test="${PaginaActual lt noDePaginas}">
													<td><a href="Home?page=${PaginaActual+1}&fecha=${fecha}">Siguiente</a></td>
												</c:if>
												
												
												<script type="text/javascript">
													var ingresos = (<c:out value="${ingresos}"/>).toFixed(2);
													var gastos = (<c:out value="${gastos}"/>).toFixed(2);
													var total = (<c:out value="${total}"/>).toFixed(2);
													document.getElementById("ingresos").innerHTML = ingresos + "€";
													document.getElementById("gastos").innerHTML = gastos + "€";
													document.getElementById("total").innerHTML = total + "€";
												</script>
										</div>
									</div>

								</div>
							</div>
						</div>
						<div id="acciones">
							<div class="container">
								<div class="text-center mt-2 ">
									<button type="button" class="btn btn-circle btn-xl btn-outline-success" data-bs-toggle="modal" data-bs-target="#myModal"><i class="icon ion-md-add lead"></i></button>
									<button type="button" class="btn btn-circle btn-xl btn-outline-danger" data-bs-toggle="modal" data-bs-target="#myModal2"><i class="icon ion-md-remove lead"></i></button>
								</div>
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
					</div>
				</section>
			</div>	
		</div>
						
	
</body>
</html>