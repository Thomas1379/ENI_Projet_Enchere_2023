<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.eni.enchere.error.LecteurMessage"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/normalize.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/style.scss">
<script src="https://cdn.tailwindcss.com"></script>
<title>Ajouter un article</title>
</head>
<body class="nouvele-vente-page">

	<nav>
		<a href="<%=request.getContextPath()%>" class="logo">Les objets sont nos amis</a>
		<div>
			<c:choose>
				<c:when test="${user != null}">
					<a href="<%=request.getContextPath()%>/AjoutArticle">Vendre un article</a>
					<a href="<%=request.getContextPath()%>/Profile">Mon Profil</a>
					<a href="<%=request.getContextPath()%>/Logout">Déconnexion</a>
				</c:when>
				<c:otherwise>
					<a href="<%=request.getContextPath()%>/Login">Login/register</a>
				</c:otherwise>
			</c:choose>
		</div>
	</nav>

	<c:if test="${article != null }">
		<p>L'article a été ajouté avec succès</p>
	</c:if>
	
	<c:if test="${codesError != null }">
		<p>Erreur : L'article n'a pas pu être ajouté : </p>
	<c:forEach items="${codesError}" var="error">
		<p>${LecteurMessage.getMessageErreur(error)}</p>
	</c:forEach>
	</c:if>
	<div class="my_vente_text">
			<h1>Nouvelle Vente</h1>
	</div>
	
	
	<form method="post" action="">
		<div class="article_name_and_categorie">
			<div class="div-control article_name">
				<label for="article">Article : </label> 
				<input type="text" name="article" id="article" value="<%= request.getAttribute("codesError")!=null?request.getParameter("article"):""%>" /> 
			</div>
		</div>
		<div class="div-control categorie_name">
				<label for="categorie">Catégorie </label>
				<select name="categorie" id="categorie">
					<option value="" hidden="hidden">Choisissez une catégorie</option>
					<c:forEach items="${categories}" var="categorie">
						<option value="${categorie.getNo_categorie()}"><c:out value="${categorie.getLibelle()}"/></option>
					</c:forEach>
				</select>
		</div>
		<div class="div-control price-div">
			<label for="prix">Mise à prix : </label>
			<input type="text" name="prix" id="prix" value="<%= request.getAttribute("codesError") !=null?request.getParameter("prix"):""%>"/>
		</div>
		<div class="start_date_end_date_div">
			<div class="div-control start_date">
				<label for="debut">Début de l'enchère : </label> 
				<input type="datetime-local" name="debut" id="debut" value="<%= request.getAttribute("codesError") !=null?request.getParameter("debut"):""%>"/>
			</div>
			<div class="div-control end_date">
				<label for="fin">Fin de l'enchère : </label> 
				<input type="datetime-local" name="fin" id="fin" value="<%= request.getAttribute("codesError") !=null?request.getParameter("fin"):""%>" />
			</div>
		</div>
		<div class="retrait_div_control">
			<fieldset>
				<legend class="retrait_text">Retrait : </legend>
				<div class="div-control">
					<label for="rue">Rue : </label> 
					<input type="text" name="rue" id="rue" value="<c:out value="${user.rue }"/>" />
				</div>
				<div class="div-control">
					<label for="CP">Code Postal : </label> 
					<input type="text" name="CP" id="CP" value="<c:out value="${user.code_postal }"/>"/> 
				</div> 
				<div class="div-control">
					<label for="ville">Ville : </label>
					<input type="text" name="ville" id="ville" value="<c:out value="${user.ville }"/>"/>
				</div>
			</fieldset>
		</div>
		
		<div class="div-control description-control">
			<label for="description">Description : </label>
			<textarea name="description" id="description" placeholder="Description de l'article" ><%= request.getAttribute("codesError") !=null?request.getParameter("description"):""%></textarea>
		</div>
		
		<div class="enregisrer_div_control">
				<input type="submit" value="Enregistrer" />
				<a href="<%=request.getContextPath()%>">Annuler</a>
		</div>

	</form>
</body>
</html>










