<!doctype html>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Conferma</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="./navbar.jsp" />

	<main role="main" class="container">

		<div class='card'>
			<div class='card-header'>
				<h5>Conferma eliminazione</h5>
			</div>
			<div class='card-body'>

				<a
						href="${pageContext.request.contextPath}/SearchUtente?nome=${sessionScope.nome_utente}&cognome=${sessionScope.cognome_utente}&
				username=${sessionScope.username}&ruolo=${sessionScope.autore.getId()}&stato=${sessionScope.stato}"
						class='btn btn-outline-secondary' style='width: 80px'> Back </a> 
					
					<a
					class="btn btn-outline-danger btn-sm"
					href="${pageContext.request.contextPath}/CancellaUtente?id=<%=request.getParameter("id")%>">Delete</a>



			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="./footer.jsp" />

</body>
</html>