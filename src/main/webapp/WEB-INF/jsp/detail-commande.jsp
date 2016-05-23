<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>

<div class="pull-right"><a href="<spring:url value="/commandes.html" />" ><small>retour menu</small></a></div>
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myModal">Ajouter ligne commande</button>
<!-- Modal -->
<form:form modelAttribute="ligneCommande" cssClass="form-horizontal">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Ligne commande</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="nom" class="col-sm-2 control-label">Consommation
							:</label>
						<div class="col-sm-4">
							<form:select items="${consommations}" itemLabel="name"
								itemValue="id" path="consommation" cssClass="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">Quantité
							:</label>
						<div class="col-sm-4">
							<form:input path="quantite" cssClass="form-control" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
					<input type="submit" class="btn btn-primary" value="Ajouter detail" />
				</div>
			</div>
		</div>
	</div>
</form:form>
<table class="">
	<tr>
		<td>N&deg; Commande :</td>
		<td>${commande.id}</td>
	</tr>
	<tr>
		<td>Date Commande :</td>
		<td><fmt:formatDate pattern="dd/MM/yyyy"
				value="${commande.dateCommande}" /></td>
	</tr>
	<tr>
		<td>Client :</td>
		<td>${commande.client.nom}</td>
	</tr>
</table>
<br />
<table class="table table-condensed table-hover">
	<thead>
		<tr>
			<th>N&deg; ligne</th>
			<th>Consommation</th>
			<th>Prix unitaire</th>
			<th>Quantité</th>
			<th>Montant</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${lignesCommande}" var="detailCommande">
			<tr>
				<td>${detailCommande.id}</td>
				<td>${detailCommande.consommation.name}</td>
				<td>${detailCommande.prixUnitaire}</td>
				<td>${detailCommande.quantite}</td>
				<td>${detailCommande.montant}</td>
			</tr>
		</c:forEach>
		<tr>
			<td><p class="lead">Total Commande :</p></td>
			<td></td>
			<td></td>
			<td></td>
			<td><p class="lead">
					<fmt:formatNumber value="${commande.totalCommande}" type="currency"
						maxFractionDigits="0" currencySymbol="F CFA" />
				</p></td>
		</tr>
	</tbody>
</table>