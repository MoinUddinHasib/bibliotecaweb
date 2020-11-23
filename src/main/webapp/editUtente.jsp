<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Modifica Utente</title>

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


		<div class='card'>
			<div class='card-header'>
				<h5>Modifica Utente</h5>
			</div>
			<div class='card-body'>

				<form method="post" action="UpdateUtente" name="campi" novalidate>

					<input type="hidden" name="id" value="${ requestScope.id}">

					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Nome </label> <input
								type="text" name="nome" id="nome" class="form-control"
								value="${requestScope.nome}" required>
						</div>

						<div class="form-group col-md-6">
							<label>Cognome </label> <input
								type="text" name="cognome" id="cognome" class="form-control"
								value="${requestScope.cognome}" required>
						</div>

						<div class="form-group col-md-6">
							<label>Username </label> <input type="text" name="username"
								value="${requestScope.username}" id="username"
								class="form-control" required>

						</div>

						<div class="form-group col-md-3">
							<fieldset>
								<legend>Ruoli</legend>
								<br> <input type="checkbox" name="admin" id="admin"
									value=${requestScope.admin }
									<c:if test = "${requestScope.cond_admin == true}">
         								checked
      								</c:if> />
								ADMIN <br /> <input type="checkbox" name="classic" id="classic"
									value=${requestScope.classic }
									<c:if test = "${requestScope.cond_classic == true}">
         								checked
      								</c:if> />
								CLASSIC <br /> <input type="checkbox" name="guest" id="guest"
									value=${requestScope.guest }
									<c:if test = "${requestScope.cond_guest == true}">
         								checked
      								</c:if> />
								GUEST
							</fieldset>
						</div>

						<div class="form-row">
							<div class="form-group col-md-3">
								<label>Stato </label> <select name="stato">

									<option value="ATTIVO"
										<c:if test = "${requestScope.stato == 'ATTIVO'}">
         								selected
      								</c:if>>Attivo</option>
									<option value="INATTIVO"
										<c:if test = "${requestScope.stato == 'INATTIVO'}">
         								selected
      								</c:if>>Inattivo</option>

								</select>

							</div>

						</div>
					</div>
					<a href="${pageContext.request.contextPath}/SearchUtente?nome=${sessionScope.nome_utente}&cognome=${sessionScope.cognome_utente}&
				username=${sessionScope.username}&ruolo=${sessionScope.ruolo.getId()}&stato=${sessionScope.stato}" class='btn btn-outline-secondary' style='width:80px'>
		          Back
		        </a>
					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>
				

				</form>



				<!-- end card-body -->
			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="./footer.jsp" />

</body>
</html>