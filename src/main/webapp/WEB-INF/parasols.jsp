<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des parasols</title>
</head>
<link href="style/parasols.css" rel="stylesheet">
<body>
	<jsp:include page="navbar.jsp" />
	<h1>Liste des parasols</h1>
	<form>
		<select name="ID_FILE">
			<option value="0">Toutes les files</option>
			<c:forEach items="${files}" var="file">
				<option value="${file.id}"
					<c:if test="${file.id eq idFile}"> SELECTED</c:if>>${file.numero}</option>
			</c:forEach>
		</select> <input type="submit" value="Filtrer">
	</form>
	<ul class="parasol-grid">
		<c:forEach items="${pageDeParasols.content}" var="parasol">
			<li>Parasol ${parasol.numEmplacement} en file
				${parasol.file.numero} (${parasol.file.prixJournalier} &euro;) <a
				href="parasol?ID_PARASOL=${parasol.id}">Modifier</a>
			</li>
		</c:forEach>
	</ul>
	<div class="pagination-container">
		<div class="pagination">
			<c:if test="${!pageDeParasols.isFirst()}">
				<a href="?ID_FILE=${idFile}&page=0" class="page-nav">&lt;&lt;</a>
				<a href="?ID_FILE=${idFile}&page=${pageDeParasols.number-1}"
					class="page-nav">&laquo;</a>
			</c:if>
			Page ${pageDeParasols.getNumber()+1} sur
			${pageDeParasols.getTotalPages()}
			<c:if test="${!pageDeParasols.isLast()}">
				<a href="?ID_FILE=${idFile}&page=${pageDeParasols.number+1}"
					class="page-nav">&raquo;</a>
				<a
					href="?ID_FILE=${idFile}&page=${pageDeParasols.getTotalPages()-1}"
					class="page-nav">&gt;&gt;</a>
			</c:if>
		</div>
	</div>
	<p class="stats">Voici les parasols de
		${pageDeParasols.totalElements == 0 ? 0 : pageDeParasols.size * pageDeParasols.number+1}
		à ${pageDeParasols.numberOfElements + (pageDeParasols.size * pageDeParasols.number)}
		sur ${pageDeParasols.getTotalElements()} parasols</p>
	<br>
	<button onclick="location.href='parasol'" class="add-parasol-button">Ajouter
		un parasol</button>
</body>
<jsp:include page="footer.jsp" />
</html>
