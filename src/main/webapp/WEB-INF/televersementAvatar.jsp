<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Téléversement de l'avatar</title>
<!-- Ajout des liens vers les styles Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<div class="container mt-4">
    <h1>Téléversement de l'avatar pour l'utilisateur ${utilisateur.nom}</h1>
    <form action="televersementAvatar" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="avatarFile">Sélectionnez l'avatar :</label>
            <input class="form-control-file" type="file" name="FICHIER"/>
        </div>
        <input class="btn btn-primary" type="submit" value="Envoyer" />
        <input type="hidden" value="${utilisateur.id}" name="ID_UTILISATEUR">
    </form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<!-- Ajout du script Bootstrap -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
