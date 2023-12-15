<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import ="fr.eni.enchere.error.LecteurMessage" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>S'enregistrer</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/normalize.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/style.scss">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/style.css">
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="register">


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

	<div class="error-alerts">
		<c:if test="${utilisateur != null }">
				<p class="text-success">Votre profil a été créé avec succès</p>
		</c:if>
			
		<c:if test="${codesError != null }">
			<div class="error-control">
				<p class="text-error">Erreur : votre profil n'a pas pu être créé : </p>
				<c:forEach items="${codesError}" var="error">
					<p class="text-error-2">${LecteurMessage.getMessageErreur(error)}</p>
				</c:forEach>
			</div>
		</c:if>
	</div>
	
		<div class="my_profil_text">
			<h1>créer un compte</h1>
		</div>
	
	<form method="post" action="" class="register_form">
	
		<div class="align_inputs">
			<div class="form_inputs_one">
				<div class="div-control">
					<label for="pseudo">Pseudo : </label>
					<input type="text" name="pseudo" id="pseudo" value="<%= request.getAttribute("codesError") !=null?request.getParameter("pseudo"):""%>"/>
				</div>
				<div class="div-control">
					<label for="prenom">Prénom : </label>
					<input type="text" name="prenom" id="prenom" value="<%= request.getAttribute("codesError") !=null?request.getParameter("prenom"):""%>"/>
				</div>
				<div class="div-control">
					<label for="tel">Téléphone : </label>
					<input type="tel" name="tel" id="tel" value="<%= request.getAttribute("codesError") !=null?request.getParameter("tel"):""%>"/>
				</div>
				<div class="div-control">
					<label for="CP">Code Postal : </label>
					<input type="text" name="CP" id="CP" value="<%= request.getAttribute("codesError") !=null?request.getParameter("CP"):""%>"/>
				</div>
				<div class="div-control">
					<label for="psw">Mot de passe : </label>
					<input type="password" name="psw" id="psw" />
				</div>
			</div>
			
			<div class="form_inputs_two">
				<div class="div-control">
					<label for="nom">Nom : </label>
					<input type="text" name="nom" id="nom" value="<%= request.getAttribute("codesError") !=null?request.getParameter("nom"):""%>"/>
				</div>
				<div class="div-control">
					<label for="email">Email : </label>
					<input type="email" name="email" id="email" value="<%= request.getAttribute("codesError") !=null?request.getParameter("email"):""%>"/>
				</div>
				<div class="div-control">
					<label for="rue">Rue : </label>
					<input type="text" name="rue" id="rue" value="<%= request.getAttribute("codesError") !=null?request.getParameter("rue"):""%>"/>
				</div>
				<div class="div-control">
					<label for="ville">Ville : </label>
					<input type="text" name="ville" id="ville" value="<%= request.getAttribute("codesError") !=null?request.getParameter("ville"):""%>"/>
				</div>
				<div class="div-control">
					<label for="pswconfirm">Confirmation : </label>
					<input type="password" name="pswconfirm" id="pswconfirm" />
				</div>
			</div>
		</div>
		<div class="control_buttons">
				<input type="submit" value="Créer" class="button_create" />
				<a href="<%=request.getContextPath()%>" class="button_cancel">Annuler</a>
		</div>
	</form>
</body>
</html>
