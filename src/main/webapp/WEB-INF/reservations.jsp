<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des réservations</title>
<link rel="stylesheet" href="style/reservations.css">
<!-- Ajouter le lien vers le fichier Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
		<h1 class="mt-4">Liste des réservations</h1>
		<ul class="list-group mt-4">
			<!-- Le forEach ci-dessous parcourt le contenu de la page de reservations -->
			<c:forEach items="${pageDeReservations.content}" var="reservation">
				<li class="list-group-item">${reservation.client.nom}
					${reservation.client.prenom} (${reservation.client.pays.nom})<br>
					Du ${reservation.dateDebut} au ${reservation.dateFin}<br> 
					<a
					class="btn btn-sm btn-primary mt-2"
					href="reservation?ID_RESERVATION=${reservation.id}">Modifier</a> 
					<a
					class="btn btn-sm btn-secondary mt-2"
					href="reservationPDF?ID_RESERVATION=${reservation.id}">PDF</a>
				</li>
			</c:forEach>
		</ul>
		<div class="pagination text-center mt-4">
			<c:if test="${!pageDeReservations.isFirst()}">
				<a class="btn btn-light" href="reservations?page=0&sort=${sort}">&lt;&lt;</a>
				<a class="btn btn-light"
					href="reservations?page=${pageDeReservations.number-1}&sort=${sort}">&laquo;</a>
			</c:if>

			<c:forEach var="i"
				begin="${pageDeReservations.getNumber()-2 > 0 ? pageDeReservations.getNumber()-2 : 0}"
				end="${pageDeReservations.getNumber()+2}" step="1">
				<c:if test="${i < pageDeReservations.totalPages}">
					<a class="btn btn-light" href="reservations?page=${i}&sort=${sort}">${i+1}</a>
				</c:if>
			</c:forEach>
			<c:if test="${!pageDeReservations.last}">
				<a class="btn btn-light"
					href="reservations?page=${pageDeReservations.number+1}&sort=${pageDeReservations.sort.iterator().next().property},${pageDeReservations.sort.iterator().next().direction}">&raquo;</a>
				<a class="btn btn-light"
					href="reservations?page=${pageDeReservations.totalPages - 1}&sort=${sort}">&gt;&gt;</a>
			</c:if>
		</div>
		<p class="mt-4">Réservations de ${pageDeReservations.totalElements == 0 ? 0 : pageDeReservations.size * pageDeReservations.number+1}
			à ${pageDeReservations.numberOfElements + (pageDeReservations.size * pageDeReservations.number)}
			sur ${pageDeReservations.getTotalElements()} réservations</p>
		<p>
			<a class="btn btn-primary"
				href="reservations?page=0&sort=dateDebut,DESC">Trier sur date de
				début décroissante</a>
		</p>
		<p>
			<a class="btn btn-primary"
			 href="exportExcel">Export Excel des réservations de la
				semaine en cours</a>
		</p>
		<p>
			<a class="btn btn-success" href="reservation">Ajouter une
				réservation</a>
		</p>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
