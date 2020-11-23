<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Modifica Libro</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="./navbar.jsp" />

	<main role="main" class="container">

		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card-header'>
			<h5>Modifica libro</h5>
		</div>
		<div class='card-body'>

			<form method="post" action="UpdateLibro" name="campi" novalidate>

				<input name="id" value="${requestScope.id }" hidden="true">
				<input name="autore_fk" value="${requestScope.autore_fk }" hidden="true">
				<div class="form-row">

					<div class="form-group col-md-6">
						<label>Titolo </label> <input
							type="text" value="${requestScope.titolo}" name="titolo"
							id="titolo" class="form-control" required>
					</div>

					<div class="form-group col-md-6">
						<label>Genere </label> <input
							type="text" value="${requestScope.genere}" name="genere"
							id="genere" class="form-control" required>
					</div>
					<div class="form-group col-md-6">
						<label>Trama </label> <input
							type="text" value="${requestScope.trama}" class="form-control"
							name="trama" id="trama" required>
					</div>
				</div>


					<div class="dropdown bootstrap-select">
						<label>Autore </label> <select name="autore">
							<c:forEach items="${requestScope.autori}" var="a">
								<option value="${a.id }"
									<c:if test = "${requestScope.autore_fk == a.id}">
         								selected
      								</c:if>
      						>${a.nome };	${a.cognome }</option>
							</c:forEach>
						</select>
				</div>
				<a href="${pageContext.request.contextPath}/SearchLibro?titolo=${sessionScope.titolo}&genere=${sessionScope.genere}&
				trama=${sessionScope.trama}&autore=${sessionScope.autore.getId()}" class='btn btn-outline-secondary' style='width:80px'>
		          Back
		        </a>
				<button type="submit" name="submit" value="submit" id="submit"
					class="btn btn-primary">Conferma</button>
				
			</form>

			<div class='card-footer'></div>
		</div>



		<!-- end main container -->
	</main>
	<jsp:include page="./footer.jsp" />

</body>
</html>