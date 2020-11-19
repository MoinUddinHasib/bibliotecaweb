<!doctype html>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Modifica Autore</title>

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
			<h5>Modifica autore</h5>
		</div>
		<div class='card-body'>

			<form method="post" action="UpdateAutore" name="campi">
				
				<input type="hidden" value="${requestScope.id }" name="id">

				<div class="form-row">

					<div class="form-group col-md-6">
						<label>Nome </label><input
							type="text" value="${requestScope.nome}" name="nome" id="nome"
							class="form-control" required>
					</div>

					<div class="form-group col-md-6">
						<label>Cognome </label> <input
							type="text" value="${requestScope.cognome}" name="cognome"
							id="cognome" class="form-control" required>
					</div>

					<div class="form-group col-md-6">
						<label>Data di nascita </label>
						<input type="date" value="${requestScope.data_di_nascita}"
							name="data_di_nascita" id="data_di_nascita" class="form-control"
							required>
					</div>
				</div>


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