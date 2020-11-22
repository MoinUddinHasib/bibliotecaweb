<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Inserisci nuovo</title>

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
				<h5>Inserisci nuovo Utente</h5>
			</div>
			<div class='card-body'>

				<form method="post" action="InserisciUtente" name="campi">

					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Nome <span class="text-danger">*</span></label> <input
								type="text" name="nome" id="nome" class="form-control" required>
						</div>

						<div class="form-group col-md-6">
							<label>Cognome <span class="text-danger">*</span></label> <input
								type="text" name="cognome" id="cognome" class="form-control"
								required>
						</div>

						<div class="form-group col-md-6">
							<label>Username </label> <input type="text" name="username"
								id="username" class="form-control" required>

						</div>

						<div class="form-group col-md-6">
							<label>Password </label> <input type="password" name="password"
								id="password" class="form-control" required>

						</div>

						<div class="form-group col-md-3">
							<fieldset>
								<legend>Ruoli</legend>
								<br> <input type="checkbox" name="admin"
									value=${requestScope.admin } /> ADMIN <br /> <input
									type="checkbox" name="classic" value=${requestScope.classic } />
								CLASSIC <br /> <input type="checkbox" name="guest"
									value=${requestScope.guest } /> GUEST
							</fieldset>




						</div>
					</div>
					<a
						href="${pageContext.request.contextPath}/SearchUtente?nome=${sessionScope.nome_utente}&cognome=${sessionScope.cognome_utente}&
				username=${sessionScope.username}&ruolo=${sessionScope.autore.getId()}&stato=${sessionScope.stato}"
						class='btn btn-outline-secondary' style='width: 80px'> Back </a>
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