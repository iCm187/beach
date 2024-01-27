<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message d'erreur</title>
<meta http-equiv="refresh" content="5;url=/">
</head>
<body>

<div style="text-align: center; padding: 50px;">
    <h1>Oups ! Une erreur <%= request.getAttribute("codeRetour") %> s'est produite.</h1>
    <p>Nous vous redirigeons vers la page d'accueil dans quelques secondes...</p>
</div>

<!-- Redirection après 5 secondes -->
<script>
    setTimeout(function() {
        window.location.href = '/'; // Remplacez par l'URL de la page d'accueil
    }, 5000); // 5000 ms = 5 secondes
</script>

</body>
</html>

