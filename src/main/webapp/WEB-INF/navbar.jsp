<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Ajout de Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<link href="style/navbar.css" rel="stylesheet">
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-black">
		<a class="navbar-brand" href="/">BeachRental</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/clients">Clients</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="/parasols">Parasols</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="/tableauDeBord">Dashboard</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="/reservation">Réservation</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="/reservations">Réservations</a>
				</li>
				<li class="nav-item">
					<!--                 grace au lien ci-dessous on confie a spring la fin de la session -->
					<a class="nav-link" href="/logout">Déconnexion</a>
				</li>
			</ul>
			<p class="ml-auto">
				${utilisateur.prenom} ${utilisateur.nom}
			</p>
		</div>
	</nav>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
