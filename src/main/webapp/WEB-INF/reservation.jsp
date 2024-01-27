<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Réservation</title>
<link rel="stylesheet" href="style/reservations.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container mt-5">
		<h1 class="mb-4">Réservation</h1>
		<form:form method="post" modelAttribute="reservation"
			action="reservation" class="form">

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">Date de début et de
					fin</label>
				<div class="col-sm-5">
					<div class="input-group">
						<form:input path="dateDebut" type="text"
							class="form-control datepicker" placeholder="Date de début" />
						<div class="input-group-append">
							<span class="input-group-text"><i
								class="far fa-calendar-alt"></i></span>
						</div>
					</div>
					<form:errors path="dateDebut" cssClass="erreur" />
				</div>
				<div class="col-sm-5">
					<div class="input-group">
						<form:input path="dateFin" type="text"
							class="form-control datepicker" placeholder="Date de fin" />
						<div class="input-group-append">
							<span class="input-group-text"><i
								class="far fa-calendar-alt"></i></span>
						</div>
					</div>
					<form:errors path="dateFin" cssClass="erreur" />
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">Client</label>
				<div class="col-sm-10">
					<form:select path="client.id" class="form-control">
						<form:option value="0">Sélectionnez un client</form:option>
						<c:forEach items="${clients}" var="client">
							<form:option value="${client.id}">${client.nom} ${client.prenom}</form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">Choisir Parasols</label>
				<div class="col-sm-10">
					<select name="parasols" multiple class="form-control">
						<c:choose>
							<c:when test="${not empty parasols}">
								<c:forEach items="${parasols}" var="parasol">
									<option value="${parasol.id}">Parasol
										${parasol.numEmplacement} en file ${parasol.file.id}</option>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<option value="0">Aucun parasol disponible</option>
							</c:otherwise>
						</c:choose>
					</select>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">Remarques</label>
				<div class="col-sm-10">
					<form:textarea path="remarques" class="form-control"></form:textarea>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-sm-10 offset-sm-2">
					<form:button class="btn btn-primary">Enregistrer</form:button>
				</div>
			</div>
		</form:form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	<script>
		$(document).ready(function() {
			$('.datepicker').datepicker({
				clearBtn : true,
				format : "dd/mm/yyyy",
				language : "fr"
			});
		});
	</script>
</body>
</html>
