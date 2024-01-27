<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des clients</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="style/clients.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>

	<div class="container mt-4">
		<h1>Liste des clients</h1>

		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>Nom</th>
					<th>Prénom</th>
					<th>Pays</th>
					<th>Date et Heure d'Inscription</th>
					<th>Action</th>
					<th>Avatar</th>
				</tr>
			</thead>
			<tbody>
				<!-- Le forEach ci-dessous parcourt le contenu de la page de clients -->
				<c:forEach items="${pageDeClients.content}" var="client">
					<td><img class="img-thumbnail"
						style="max-height: 200px; max-width: 200px;"
						src="images/${client.id}.jpg"
						alt="Avatar de ${client.nom} ${client.prenom}"></td>
					<tr>
						<td>${client.nom}</td>
						<td>${client.prenom}</td>
						<td>${client.pays.nom}</td>
						<td>${client.dateHeureInscription}</td>
						<td><a class="btn btn-primary"
							href="client?ID_CLIENT=${client.id}">Modifier </a></td>
						<td><a class="btn btn-primary"
							href="televerserAvatar?ID_UTILISATEUR=${client.id}">Avatar </a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="d-flex justify-content-between align-items-center">
			<div class="pagination">
				<c:if test="${!pageDeClients.isFirst()}">
					<a class="btn btn-link" href="clients?page=0&sort=${sort}">Première</a>
					<a class="btn btn-link"
						href="clients?page=${pageDeClients.number-1}&sort=${sort}">Précédente</a>
				</c:if>
				<c:forEach var="i"
					begin="${pageDeClients.getNumber()-2 > 0 ? pageDeClients.getNumber()-2 : 0}"
					end="${pageDeClients.getNumber()+2}" step="1">
					<c:if test="${i < pageDeClients.totalPages}">
						<a class="btn btn-link" href="clients?page=${i}&sort=${sort}">${i+1}</a>
					</c:if>
				</c:forEach>
				<c:if test="${!pageDeClients.last}">
					<a class="btn btn-link"
						href="clients?page=${pageDeClients.number+1}&sort=${pageDeClients.sort.iterator().next().property},${pageDeClients.sort.iterator().next().direction}">Suivante</a>
					<a class="btn btn-link"
						href="clients?page=${pageDeClients.totalPages - 1}&sort=${sort}">Dernière</a>
				</c:if>
			</div>

			<p class="mb-0">Client de ${pageDeClients.totalElements == 0 ? 0 : pageDeClients.size * pageDeClients.number+1}
				à ${pageDeClients.numberOfElements + (pageDeClients.size * pageDeClients.number)}
				sur ${pageDeClients.getTotalElements()} Clients</p>
		</div>

		<p class="mt-3">
			<a class="btn btn-secondary"
				href="clients?page=0&sort=dateHeureInscription,DESC">Trier par
				date heure inscription décroissante</a> <a class="btn btn-secondary"
				href="clients?page=0&sort=nom">Trier par nom</a> <a
				class="btn btn-primary" href="client">Ajouter un client</a>
		</p>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
