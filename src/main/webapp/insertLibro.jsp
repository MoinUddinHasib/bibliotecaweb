<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Inserisci nuovo</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

<!--     <script type="text/javascript" language="javascript">
    function validaForm() {
    	if(document.campi.codice.value=="" || document.campi.descrizione.value=="" || document.campi.prezzo.value=="" || isNaN(document.campi.prezzo.value)){
    		alert("Campi non validi");
    		return false;
    	}
    	return true;
    }
    </script> -->
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
				<h5>Inserisci libro</h5>
			</div>
			<div class='card-body'>

				<form method="post" action="InserisciLibro" name="campi"
					onSubmit="return validaForm();">

					<div class="form-row">

						<div class="form-group col-md-6">
							<label>Titolo </label> <input type="text" name="titolo"
								id="titolo" class="form-control"
								>

						</div>

						<div class="form-group col-md-6">
							<label>Genere </label> <input type="text" name="genere"
								id="genere" class="form-control"
								>

						</div>

						<div class="form-group col-md-6">
							<label>Trama </label> <input type="text" name="trama"
								id="trama" class="form-control"
								>

						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Autore </label> <select name="autore">
								<c:forEach items="${requestScope.autori}" var="aut">
									<option value="${aut.id }">${aut.nome }, ${aut.cognome }</option>
								</c:forEach>
							</select>

						</div>

					</div>

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