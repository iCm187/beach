<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajout d'un client</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/monstyle.css" rel="stylesheet">

<link href="style/formclient.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container mt-4 mb-4">
		<div class="form-container">
			<h1 class="form-title mb-4">Ajout d'un client</h1>
			<form:form method="post" modelAttribute="client" action="client"
				class="mb-4">
				<div class="form-group">
					<form:label path="nom">Nom</form:label>
					<form:input path="nom" cssClass="form-control" />
					<form:errors cssClass="erreur" path="nom" />
				</div>
				<div class="form-group">
					<form:label path="prenom">Prénom</form:label>
					<form:input path="prenom" cssClass="form-control" />
					<form:errors cssClass="erreur" path="prenom" />
				</div>
				<div class="form-group">
					<form:label path="email">Email</form:label>
					<form:input path="email" cssClass="form-control" />
					<form:errors cssClass="erreur" path="email" />
				</div>
				<div class="form-group">
					<form:label path="motDePasse">Mot de passe</form:label>
					<form:input path="motDePasse" cssClass="form-control" />
					<form:errors cssClass="erreur" path="motDePasse" />
				</div>
				<div class="form-group">
					<form:label path="pays">Pays</form:label>
					<form:select path="pays" cssClass="form-control">
						<form:option value="0">Merci de choisir un pays</form:option>
						<form:options items="${pays}" itemValue="code" itemLabel="nom" />
					</form:select>
					<form:errors cssClass="erreur" path="pays" />
				</div>
				<div class="form-group">
					<form:label path="lienDeParente">Lien de parenté</form:label>
					<form:select path="lienDeParente" cssClass="form-control">
						<form:option value="0">Merci de choisir un lien de parenté</form:option>
						<form:options items="${liensDeParente}" itemValue="id"
							itemLabel="nom" />
					</form:select>
					<form:errors cssClass="erreur" path="lienDeParente" />
				</div>
				<div class="form-button">
					<form:button class="btn btn-success">Enregistrer</form:button>
				</div>
			</form:form>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
