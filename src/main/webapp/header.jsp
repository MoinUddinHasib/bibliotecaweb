<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="./assets/css/bootstrap.min.css" >

<!-- Favicons -->
<link rel="apple-touch-icon" href="./assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="./assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="./assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="./assets/img/favicons/manifest.json">
<link rel="mask-icon" href="./assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
<link rel="icon" href="./assets/img/favicons/favicon.ico">
<meta name="msapplication-config" content="./assets/img/favicons/browserconfig.xml">
<meta name="theme-color" content="#563d7c">

<style>
	.bd-placeholder-img {
	  font-size: 1.125rem;
	  text-anchor: middle;
	  -webkit-user-select: none;
	  -moz-user-select: none;
	  -ms-user-select: none;
	  user-select: none;
	}

  @media (min-width: 768px) {
    .bd-placeholder-img-lg {
      font-size: 3.5rem;
    }
  }
  
</style>
<script src="./assets/js/jquery-3.4.1.min.js">
</script>
<script >
$(document).ready(function(){
	$("#submit").click(function(){
		var error=""
 		if($("#nome").length && $("#nome").val()==""){
			error+="nome; "
		}
		if($("#cognome").length && $("#cognome").val()==""){
			error+="cognome; "
		}
		if($("#data").length && $("#data").val()==""){
			error+="data; "
		}
		if($("#titolo").length && $("#titolo").val()==""){
			error+="titolo; "
		}
		if($("#genere").length && $("#genere").val()==""){
			error+="genere; "
		}
		if($("#trama").length && $("#trama").val()==""){
			error+="trama; "
		}
		if($("#username").length && $("#username").val()==""){
			error+="username; "
		}
		if($("#password").length && $("#password").val()==""){
			error+="password; "
		}
		if($("#admin").length && $("#admin").is(":not(:checked)") && $("#classic").is(":not(:checked)") && $("#guest").is(":not(:checked)")){
			error+="selezione di almeno un ruolo "
		}
		if(error!=""){
			error+=" -> Manca"
			alert(error)
			event.preventDefault()
		}
	});
});
</script>
