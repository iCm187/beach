<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<link href="style/parasol.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Prasol</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
	<h1>Bienvenue sur la page d'ajout de parasol</h1>
	<div class="form-container">
		<form:form method="post" modelAttribute="parasol" action="parasol">
			<div class="form-group">
				<form:label path="numEmplacement">Numéro d'emplacement</form:label>
				<form:input path="numEmplacement" cssClass="form-control" />
				<form:errors cssClass="erreur" path="numEmplacement" />
			</div>
			<br>
			<div class="form-group">
				<form:label path="file">File</form:label>
				<form:select path="file" cssClass="form-control">
					<form:option value="0">Merci de choisir une file</form:option>
					<form:options items="${files}" itemValue="id" itemLabel="numero" />
				</form:select>
				<form:errors cssClass="erreur" path="file" />
			</div>
			<br>
			<form:hidden path="id" />
			<div class="form-group">
				<form:button class="form-button">Enregistrer</form:button>
			</div>
		</form:form>
	</div>
</body>
<jsp:include page="footer.jsp" />
</html>
