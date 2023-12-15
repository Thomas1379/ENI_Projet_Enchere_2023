<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="fr.eni.enchere.error.LecteurMessage"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Enchères</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/styles/normalize.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/styles/style.scss">
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

	<nav>
		<a href="<%=request.getContextPath()%>" class="logo">ENI-Encheres</a>
		<div>
			<a href="<%=request.getContextPath()%>/AjoutArticle">Vendre un
				article</a> <a href="<%=request.getContextPath()%>/Profile">Mon
				Profil</a> <a href="<%=request.getContextPath()%>/Logout">Déconnexion</a>
		</div>
	</nav>

	<main class="enchere">

		<c:if test="${success != null }">
			<p>L'enchère a bien était crée</p>
		</c:if>

		<c:if test="${codesError != null }">
			<p>Erreur : L'enchere n'a pas pu être ajouté :</p>
			<c:forEach items="${codesError}" var="error">
				<p>${LecteurMessage.getMessageErreur(error)}</p>
			</c:forEach>
		</c:if>

		<h1><b>Détail vente</b></h1>
		<div class="article_control_data">
			<div class="info_article_fk">
				<h1>
					<b><c:out value="${article.getNom_article()}" /></b>
				</h1>
				<p>
					<b>Description :</b>
					<c:out value="${article.getDescription()}" />
				</p>
				<p>
					<b>Catégorie :</b>
					<c:out value="${categorie.getLibelle()}" />
				</p>
				<c:choose>
					<c:when
						test="${enchere.getMontant_enchere() != '0' && enchere != null}">
						<p>
							<b>Prix initial :</b>
							<c:out value="${article.getPrix_initial()}" />
						</p>
						<p>
							<b>Meilleure offre :</b>
							<c:out value="${enchere.getMontant_enchere()}" />
						</p>
					</c:when>
					<c:otherwise>
						<p>
							<b>Prix initial :</b>
							<c:out value="${article.getPrix_initial()}" />
						</p>
					</c:otherwise>
				</c:choose>
				<p>
					<b>Début de l'enchère le :</b>
					<c:out
						value="${article.getDate_debut_encheres().format(DateTimeFormatter.ofPattern(\"dd MMMM yyyy HH:mm\")) }" />
				</p>
				<p>
					<b>Fin de l'enchère le :</b>
					<c:out
						value="${article.getDate_fin_encheres().format(DateTimeFormatter.ofPattern(\"dd MMMM yyyy HH:mm\")) }" />
				</p>
				<p>
					<b>Il reste </b><strong id="timer"><c:out value="${duration }" /></strong>
					avant la fin de l'enchère
				</p>
				<p>
					<b>Retrait :</b>
					<c:out value="${user.getRue()}" />
					,
					<c:out value="${user.getCode_postal()}" />
					<c:out value="${user.getVille()}" />
				</p>
				<p>
					<b>Vendeur :</b>
					<c:out value="${user.getPseudo()}" />
				</p>
	
				<form method="post" action="">
					<label for="montant">Faire une enchère : </label> 
					<input type="number" name="montant" id="montant" /> <input class="fucking_button" type="submit" value="Enchérir" />
				</form>
			</div>
				<img src="https://icancycling.com/cdn/shop/articles/1602491866_1602491862_1601025617_1601025614_SN04_fatbike_b8d050ba-fad4-44b6-89a8-50d66f56930d.jpg?v=1640662944" alt="Article Image">
		</div>

	</main>
</body>
</html>