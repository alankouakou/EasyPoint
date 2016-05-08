<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
	   

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet"
	href="../../resources/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
</script>
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>


<script
	src="../../resources/js/bootstrap.min.js"></script>
</head>
<body>
	
<tilesx:useAttribute name="current" />
    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.html"><img src="../../resources/images/allianz-icon.png" /> </a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="${current == 'index' ? 'active' : '' }"><a href="<spring:url value="/index.html" />">Home</a></li>
            <li class="${current == 'vehicules' ? 'active' : ''}"><a href="<spring:url value="/vehicules.html" />">Vehicules</a></li>
			<security:authorize access="hasRole('ROLE_ADMIN')">
			<li class="${current == 'users' ? 'active' : '' }">   <a href="<spring:url value="/users.html" />">Utilisateurs</a></li>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_ADMIN')">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin tasks <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li class="dropdown-header">Parametres generaux</li>
                <li><a href="<spring:url value="/index.html" />">Home</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="<spring:url value="/register-user.html" />">Ajouter utilisateur</a></li>
                <li><a href="<spring:url value="/register-vehicule.html" />">Ajouter vehicule</a></li>
                <li><a href="<spring:url value="/register-vehicule.html" />">Ajouter Client</a></li>
                <li><a href="<spring:url value="/register-vehicule.html" />">Ajouter Type Consommation</a></li>
                <li><a href="<spring:url value="/register-vehicule.html" />">Ajouter Consommation</a></li>
              </ul>
            </li>
            </security:authorize>
            <security:authorize access="! isAuthenticated()">
             <li class="${current == 'login' ? 'active' : ''}" ><a href="<spring:url value="/login.html" />">Connexion</a></li>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
                <li class="${current == 'logout' ? 'active' : ''}" ><a href="<spring:url value="/logout" />">Deconnexion</a></li>
            </security:authorize>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
<br /><br /><br /><br />
	
	<!-- Begin page content -->
	<div class="container">  
		<div class="page-header">
			<h1><tiles:getAsString name="title" /></h1>
		</div>
		<p class="lead">

			<tiles:insertAttribute name="body" />
			.
		</p>
		<p>
			<tiles:getAsString name="signature" />
		</p>
		<tiles:insertAttribute name="footer" />
	</div>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>
