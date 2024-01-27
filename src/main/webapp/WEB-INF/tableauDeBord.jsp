<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tableau de bord</title>
    <link rel="stylesheet" href="style/dashboard.css">
</head>
<body>
    <jsp:include page="navbar.jsp"></jsp:include>
    <div class="container">
        <h1 class="main-title">Tableau de bord</h1>
        <h2 class="main-title">Bienvenue ${sessionScope.utilisateurConnecte.nom.toUpperCase()} ${sessionScope.utilisateurConnecte.prenom}</h2>
        <h2 class="section-title">Mes réservations</h2>

        <table class="table custom-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Date de début</th>
                    <th>Date de fin</th>
                    <th>Statut</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="reservation" items="${sessionScope.utilisateurConnecte.reservations}">
                    <tr>
                        <td>${reservation.id}</td>
                        <td>${reservation.dateDebut}</td>
                        <td>${reservation.dateFin}</td>
                        <td>${reservation.statut.nom}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
