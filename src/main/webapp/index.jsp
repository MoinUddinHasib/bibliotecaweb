<!doctype html>
<html lang="it">
<head>

<jsp:include page="./header.jsp" />

<!-- Custom styles for this template -->
<link href="./assets/css/global.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 3.5rem;
}
</style>
<title>Sign-in</title>
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
			<h5>Log-in</h5>
		</div>
		<div class='card-body'>

			<form method="post" action="ServletLoggin" name="campi">

				<div class="form-row">

					<div class="form-group col-md-6">
						<label>Username </label> <input
							type="text" value="${requestScope.username}" name="username"
							id="username" class="form-control"
							placeholder="Inserire username" required>
					</div>

				</div>

				<div class="form-row">
					<div class="form-group col-md-3">
						<label>Password </label> <input
							type="password" value="${requestScope.password}"
							class="form-control" name="password" id="password"
							placeholder="Inserire password" required>
					</div>
				</div>

				<button type="submit" name="submit" value="submit" id="submit"
					class="btn btn-primary">Invia</button>

			</form>

		</div>

	</main>
	<jsp:include page="./footer.jsp" />
</body>
</html>