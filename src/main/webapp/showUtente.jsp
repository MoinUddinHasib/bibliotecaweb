<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Visualizza elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">

		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio
		    </div>
		    
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">ID:</dt>
				  <dd class="col-sm-9">${requestScope.id}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Nome:</dt>
				  <dd class="col-sm-9">${requestScope.nome}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Cognome:</dt>
				  <dd class="col-sm-9">${requestScope.cognome}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Username:</dt>
				  <dd class="col-sm-9">${requestScope.username}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Ruoli:</dt>
				  <ul>
				  <c:forEach items="${requestScope.ruoli}" var="r">
					<li> <dd >${r.codice }: ${r.descrizione } </dd></li>
				  </c:forEach>
				  </ul>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Stato:</dt>
				  <dd class="col-sm-9">${requestScope.stato}</dd>
		    	</dl>
		    	
		    	<a href="${pageContext.request.contextPath}/SearchUtente?nome=${sessionScope.nome_utente}&cognome=${sessionScope.cognome_utente}&
				username=${sessionScope.username}&ruolo=${sessionScope.ruolo.getId()}&stato=${sessionScope.stato}" class='btn btn-outline-secondary' style='width:80px'>
		          Back
		        </a>
		    	
		    </div>
		    
		</div>	
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>