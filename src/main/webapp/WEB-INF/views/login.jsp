<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.eni.enchere.error.LecteurMessage"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/style.scss">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/login.css">
<script src="https://cdn.tailwindcss.com"></script>
<title>Login</title>
</head>
<body>
	<main class="login">
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
			<c:if test="${codesError != null }">
				<c:forEach items="${codesError}" var="error">
					<p class="text-error">${LecteurMessage.getMessageErreur(error)}</p>
				</c:forEach>
			</c:if>
		</div>
	
	
		<h2>Connectez-vous ou créez votre compte</h2>
		<div class="div_control_form_login">
		
			<form method="post" action="">
				<div class="div-control">
					<label for="identifiant">Identifiant : </label>
					<input type="text" name="identifiant" id="identifiant" placeholder="Email / Pseudo" />
				</div>
				<br />
				
				<div class="div-control">
					<label for="psw">Mot de passe</label>
					<input type="password" name="psw" id="psw" placeholder="Password" />
				</div>
					<br />
				
				<div class="div-control">
					<input type="checkbox" name="remember" id="remember" value="true" checked />
					<label for="checkbox">Se souvenir de moi</label>
				</div>
					<br />
				
				<div class="buttons_connection">
					<input type="submit" value="Connexion" />
					<a href="<%=request.getContextPath()%>/Login">Mot de passe oublié</a>
				</div>
			</form>
		
		</div>
		<br />
		<br />
		
		<div class="button_create_account" >
			<a href="<%=request.getContextPath()%>/Register">Créer un compte</a>
		</div>
	</main>
</body>
</html>