<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/normalize.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/style.scss">
<script src="https://cdn.tailwindcss.com"></script>
<title>Insert title here</title>
</head>
	<body class="edit-profile-page">
		<nav>
			<a href="<%=request.getContextPath()%>" class="logo">ENI-Encheres</a>
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
	
   		<div class="my_profil_text">
			<h1>Edit Profile</h1>
		</div>
		
		<div class="user_info_text">
			<h2>User information :</h2>
		</div>

		<div class="user_info">
			<div class="control_1_user_info">
				<p><b>userId:</b> ${user.no_utilisateur}</p>
			    <p><b>Prénom: </b>${user.prenom}</p>
			    <p><b>Nom: </b>${user.nom}</p>
			    <p><b>Telephone: </b>${user.telephone}</p>
		    </div>
		    <div class="control_2_user_info">
			    <p><b>Code Postal: </b>${user.code_postal}</p>
			    <p><b>Email: </b>${user.email}</p>
			    <p><b>Rue: </b>${user.rue}</p>
			    <p><b>Ville: </b>${user.ville}</p>
		    </div>
		</div>
    

		<form action="EditProfile" method="post" class="profile_edit_form">
			<div class="align_inputs">
			    <div class="left_side">
			    	<div class="div-control">
		   		        <label for="pseudo">User Pseudo:</label>
			        	<input type="text" id="pseudo" name="pseudo" value="${user.pseudo}">
			    	</div>
		
					<div class="div-control">
				        <label for="prenom">Prenom:</label>
			        	<input type="text" id="prenom" name="prenom" value="${user.prenom}">
					</div>
		
					<div class="div-control">
				        <label for="telephone">Telephone:</label>
				        <input type="tel" id="telephone" name="telephone" value="${user.telephone}">
			        </div>
			
					<div class="div-control">
				        <label for="code_postal">Code Postal:</label>
				        <input type="text" id="code_postal" name="code_postal" value="${user.code_postal}">
			        </div>
			
					<div class="div-control">
				        <label for="mot_de_passe">Mot de Passe:</label>
				        <input type="password" id="mot_de_passe" name="mot_de_passe">
			        </div>
			        
			    </div>
			
			    <div class="right_side">
			    	<div class="div-control">
				        <label for="nom">Nom:</label>
				        <input type="text" id="nom" name="nom" value="${user.nom}">
			        </div>
			
					<div class="div-control">
				        <label for="email">Email:</label>
				        <input type="email" id="email" name="email" value="${user.email}">
			        </div>
			
					<div class="div-control">
				        <label for="rue">Rue:</label>
				        <input type="text" id="rue" name="rue" value="${user.rue}">
			        </div>
			
					<div class="div-control">
				        <label for="ville">Ville:</label>
				        <input type="text" id="ville" name="ville" value="${user.ville}">
			        </div>
			        <div class="div-control btn-enregistrer"> <input type="submit" class="input_btn_enregistrer" value="Enregistrer"></div>
			    </div>
		   </div>
		</form>
		<!--<div class="adjust_form_delete_account">
		        <form action="DeleteAccount" method="post" class="delete-account-form">
		            <!-- You can add a confirmation message here if needed 
		            <input type="hidden" name="deleteConfirmation" value="true">
		            <input type="submit" class="btn_control_delete_account" value="Supprimer mon compte">
		        </form>
		    </div>-->
	    
	</body>
</html>