<%@page import="it.bibliotecaweb.model.Autore"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
	
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
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
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
		  Esempio di operazione fallita!
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
		  Aggiungere d-none nelle class per non far apparire
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista degli autori</h5> 
		    </div>

		    <div class='card-body'>
		    <a href="${pageContext.request.contextPath}/SearchAutore" class='btn btn-outline-secondary' style='width:80px'>
		          Back
		        </a>
		    	<a class="btn btn-primary " href="InserisciAutore">Aggiungi autore</a>
		    	
		    						
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Nome</th>
		                        <th>Cognome</th>
		                        <th>Data di nascita</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                	
		                	<c:forEach items="${requestScope.listaAutoriparam}" var= 'a'>							
		                    <tr >
		                        <td>${a.getId()}</td>
		                        <td>${a.getNome()}</td>
		                        <td>${a.getCognome()}</td>
		                        <td>${a.getData_di_nascita()}</td>
		                        <td>
									<a class="btn  btn-sm btn-outline-secondary" href="VisualizzaAutore?id=${a.getId()}">Visualizza</a>
									<c:if test = "${(sessionScope.admin_cond) || (sessionScope.classic_cond)}">
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="UpdateAutore?id=${a.getId()}">Edit</a>

									<a class="btn btn-outline-danger btn-sm" href="${pageContext.request.contextPath}/confermaAutore.jsp?id=${a.getId()}">Delete</a>
									</c:if>
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