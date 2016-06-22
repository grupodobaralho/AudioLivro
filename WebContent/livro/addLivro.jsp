<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="../template/head.jsp"></jsp:include>

<!-- MODAL / POPUP -->
<jsp:include page="../template/modal.jsp"></jsp:include>
<jsp:include page="/template/msg.jsp"></jsp:include>

<div class="panel panel-primary">
	<div class="panel-heading text-center">
		Cadastro Livro
	</div>
	<div class="panel-body">
		<form method="post" action="main?acao=cadastraLivro" class="form-horizontal">
			<div class="form-group">
				<label for="isbn" class="col-sm-2 control-label">ISBN</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="isbn" placeholder="ISBN">
			    </div>
			</div>
			<div class="form-group">
				<label for="titulo" class="col-sm-2 control-label">Título</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="titulo" placeholder="Título">
			    </div>
			</div>
			<div class="form-group">
				<label for="autores" class="col-sm-2 control-label">Autores</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="autores" placeholder="Autores">
			    </div>
			</div>
				<!-- <hr>
					<button type="button" class="btn btn-info" data-toggle="modal" data-target="#modalCap">Novo Capítulo</button>
				<hr> -->
			<hr>
			<h4>Capítulos</h4>
			<div class="form-group">
			    <div class="col-sm-8">
			      <input type="text" class="form-control" id="capituloNome" placeholder="Nome capítulo">
			    </div>
			    <div class="col-sm-3">
			      <input type="text" class="form-control" id="capituloNumero" placeholder="Número capítulo">
			    </div>
			    <div class="col-sm-1">
			    	<button type="button" class="btn btn-success">
			    		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			    	</button>
			    </div>
			</div>
			<div class="form-group">
				<div class="col-sm-12">
					<table class="table table-condensed">
						<thead>
							<tr>
								<th>Capítulo</th>
								<th>Nome</th>
								<th>#</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>Parte 1</td>
								<td>
									<button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalCap">
							    		<span class="glyphicon glyphicon-circle-arrow-down" aria-hidden="true"></span>
							    	</button>
								</td>
							</tr>
							<tr>
								<td>2</td>
								<td>Parte 2</td>
								<td>
									<button type="button" class="btn btn-primary btn-xs">
							    		<span class="glyphicon glyphicon-circle-arrow-down" aria-hidden="true"></span>
							    	</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-11"></div>
				<div class="col-sm-1">
					<input class="btn btn-info" type="submit" value="Salvar">
				</div>
			</div>
		</form>
	</div>
</div>
<jsp:include page="/template/foot.jsp"></jsp:include>

<script type="text/javascript">
	$( document ).ready(function() {
		
	});
</script>