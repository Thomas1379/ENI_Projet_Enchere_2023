<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"><link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/accueil.css">
<script src="https://cdn.tailwindcss.com"></script>
<title>Accueil</title>
</head>
<body>
	<nav>
		<a href="<%=request.getContextPath()%>" class="logo">Les objets sont nos amis</a>
		<div>
			<a href="<%=request.getContextPath()%>/Login">Login/register</a>
		</div>
	</nav>
	
	<main class="accueil">
		<h1>Liste des enchères</h1>
		<form method="post" action="">
				<label for="search">Filtres : </label>
				<div class="search">
					<input type="text" name="search" id="search" placeholder="Ex: Téléphone" />
				</div>
				<br />
				<div class="categories">
					<label for="categorie">Catégorie : </label>
					<select name="categorie" id="categorie">
						<option value="">Choisissez une catégorie</option>
						<c:forEach items="${categories}" var="categorie">
							<option value="${categorie.getNo_categorie()}"><c:out value="${categorie.getLibelle()}"/></option>
						</c:forEach>
					</select>
				</div>
				<input type="submit" value="Rechercher" />
		</form>
		<div class="container off_login_articles">
			<c:forEach items="${articles}" var="article">
				<div class="card">
   					 <img src="https://upload.wikimedia.org/wikipedia/commons/a/ac/No_image_available.svg">

					<h3><a href="<%=request.getContextPath()%>/Enchere?id=${article.getNo_article()}"><strong><c:out value="${article.getNom_article()}"/></strong></a></h3>
					
					<div class="control_card_info">
						<p><b>Prix : </b>
						<c:choose>
							<c:when test="${article.getPrix_vente() != '0'}">
								<c:out value="${article.getPrix_vente()}"/>
							</c:when>
							<c:otherwise>
								<c:out value="${article.getPrix_initial()}"/> (Prix initial)
							</c:otherwise>
						</c:choose>
						 Points</p>
						<p><b>Fin de l'enchère : </b><c:out value="${article.getDate_fin_encheres().format(DateTimeFormatter.ofPattern(\"dd MMMM yyyy\"))}"/></p>
						<p><b>Vendeur : </b>
						<c:forEach items="${users}" var="user">
							<c:if test="${article.getNo_utilisateur() == user.getNo_utilisateur()}">
								<c:out value="${user.getPseudo()}"/>
							</c:if>
						</c:forEach>
						</p>
					</div>
				</div>
			</c:forEach>
		
			<!-- Contenu de toute la recherche -->
			<c:if test="${articles.size() == '0'}">
			<div class="article_not_found">
				<p>Rien n'a été trouvé, vérifiez si le champ catégorie est sélectionné !</p>
				<img src="https://cdn-icons-png.flaticon.com/512/6134/6134065.png" alt="Article Image not found">
			</div>
			</c:if>
		</div>
	</main>
</body>
</html>
