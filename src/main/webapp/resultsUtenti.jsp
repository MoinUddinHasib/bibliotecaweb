<%@page import="it.bibliotecaweb.model.Utente"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Pagina dei risultati</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="./navbar.jsp" />

	<main role="main" class="container">

		<div
			class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}"
			role="alert">
			${successMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Esempio di operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none"
			role="alert">
			Aggiungere d-none nelle class per non far apparire
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Lista degli utenti</h5>
			</div>

			<div class='card-body'>
			<a href="${pageContext.request.contextPath}/SearchUtente" class='btn btn-outline-secondary' style='width:80px'>
		          Back
		        </a>
				<a class="btn btn-primary " href="InserisciUtente">Aggiungi
					utente</a>
					

				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Id</th>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Username</th>
								<th>Stato</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.listaUtentiparam}" var='u'>
								<tr>
									<td>${u.getId()}</td>
									<td>${u.getNome()}</td>
									<td>${u.getCognome()}</td>
									<td>${u.getUsername()}</td>
									<td>${u.getStato()}</td>
									<td><a class="btn  btn-sm btn-outline-secondary"
										href="VisualizzaUtente?id=${u.getId()}">Visualizza</a>

										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
										href="UpdateUtente?id=${u.getId()}">Edit</a> <a
										class="btn btn-outline-danger btn-sm"
										href="${pageContext.request.contextPath}/confermaUtente.jsp?id=${u.getId()}">Delete</a>

									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<!-- end card-body -->
			</div>
		</div>






		<!-- end container -->
	</main>
	<jsp:include page="./footer.jsp" />

</body>
</html>