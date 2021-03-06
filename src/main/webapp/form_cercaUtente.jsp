<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Cerca Utente</title>

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
				<h5>Cerca Utente</h5>
			</div>
			<div class='card-body'>

				<form method="post" action="SearchUtente" name="campi">

					<div class="form-row">

						<div class="form-group col-md-6">
							<label>Nome </label> <input type="text" name="nome" id="nomec"
								class="form-control">

						</div>

						<div class="form-group col-md-6">
							<label>Cognome </label> <input type="text" name="cognome"
								id="cognomec" class="form-control">

						</div>

						<div class="form-group col-md-6">
							<label>Username </label> <input type="text" name="username"
								id="usernamec" class="form-control">

						</div>

						<div class="form-row">

							<div class="form-group col-md-3">
								<label>Ruolo </label> <select name="ruolo">
									<c:forEach items="${requestScope.ruoli}" var="ruo">
										<option value="${ruo.id }">${ruo.codice }:
											${ruo.descrizione }</option>
									</c:forEach>
									<option value="" selected>Qualsiasi Ruolo</option>
								</select>

							</div>

						</div>
						<div class="form-row">
							<div class="form-group col-md-3">
								<label>Stato </label> <select name="stato">
									<option value="ATTIVO" >Attivo</option>
									<option value="INATTIVO" >Inattivo</option>
									<option value="" selected>Qualsiasi</option>
								</select>

							</div>
						</div>


					</div>

					<a href="${pageContext.request.contextPath}/ServletLoggin" class='btn btn-outline-secondary' style='width:80px'>
		          Back
		        </a>
					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Cerca</button>

				
				</form>



				<!-- end card-body -->
			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="./footer.jsp" />

</body>
</html>