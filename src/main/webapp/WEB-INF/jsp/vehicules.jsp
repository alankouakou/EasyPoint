<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myModal">Ajouter vehicule</button>
<!-- Modal -->
<form:form modelAttribute="vehicule" cssClass="form-horizontal">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Nouveau vehicule</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="login" class="col-sm-4 control-label">Immatriculation :</label>
						<div class="col-sm-4">
							<form:input path="immatriculation" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="marque" class="col-sm-4 control-label">Marque :</label>
						<div class="col-sm-4">
							<form:select path="marque" items="${marques}"  cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="owner" class="col-sm-4 control-label">Owner :</label>
						<div class="col-sm-4">
							<form:select path="owner" items="${clients}" itemValue="id" itemLabel="nom" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="typeVehicule" class="col-sm-4 control-label">Type vehicule :</label>
						<div class="col-sm-4">
							<form:select path="typeVehicule" items="${typeVehicules}"  itemValue="id" itemLabel="libelle" cssClass="form-control" />
						</div>
					</div>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
					<input type="submit" class="btn btn-primary" value="Enregistrer" />
				</div>
			</div>
		</div>
	</div>
</form:form>
<table class="table table-condensed table-striped table-hover">
<thead>
	<tr>
		<th>Marque</th>
		<th>Type véhicule</th>
		<th>Immatriculation</th>
		<th>Owner</th>
		<th></th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${vehicules}" var="vehicule">
		<tr>
			<td>${vehicule.marque}</td>
			<td>${vehicule.typeVehicule.libelle}</td>
			<td>${vehicule.immatriculation}</td>
			<td>${vehicule.owner.nom}</td>
			<td><a href="/autoservice/${vehicule.id}.html" class="btn btn-sm btn-success">Laver</a>
		</tr>
	</c:forEach>
	</tbody>
</table>
