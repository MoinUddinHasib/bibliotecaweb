<%@page import="it.bibliotecaweb.model.Libro"%>
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
				<h5>Lista dei libri</h5>
			</div>

			<div class='card-body'>
				<a class="btn btn-primary " href="InserisciLibro">Aggiungi libro</a>

				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Id</th>
								<th>Titolo</th>
								<th>Genere</th>
								<th>Trama</th>
								<th>Autore</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.listaLibriparam}" var='a'>
								<tr>
									<td>${a.getId()}</td>
									<td>${a.getTitolo()}</td>
									<td>${a.getGenere()}</td>
									<td>${a.getTrama()}</td>
									<td>${a.getAutore().getNome()} ${a.getAutore().getCognome()}</td>
									<td><a class="btn  btn-sm btn-outline-secondary"
										href="VisualizzaLibro?id=${a.getId()}">Visualizza</a>

										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
										href="PrepareUpdateArticolo?id=${a.getId()}">Edit</a> <a
										class="btn btn-outline-danger btn-sm"
										href="CancellaLibro?id=${a.getId()}">Delete</a>

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