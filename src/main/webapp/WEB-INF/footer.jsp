<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="style/footer.css" rel="stylesheet">
<div style="min-height: calc(100vh - 100px);"> <!-- Utilisez min-height pour pousser le footer vers le bas -->
    <!-- Votre contenu de page ici -->
</div>
<footer>
	<div class="footer-links">
		<a href="/swagger-ui/index.html#/" target="swagger">Swagger</a><br>
		<a href="/h2-console" target="h2_console">Console H2</a><br> <a
			href="/api-autogeneree/files" target="restrepo">API auto-générée
			par Spring REST</a><br> <a href="/beans" target="_beans">Liste
			des beans contenus dans le conteneur IoC de Spring</a><br> <a
			href="/health/custom" target="_health">Actuator Health</a>
	</div>
	<jsp:useBean id="dateFin" class="java.util.Date" />
	<c:set var="msFin" value="${dateFin.getTime()}" scope="page" />
	<p class="generation-time">Page générée en ${msFin - msDepart} ms</p>
</footer>
