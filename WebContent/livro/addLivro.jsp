<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="../template/head.jsp"></jsp:include>

<!-- MODAL / POPUP -->
<jsp:include page="../template/modal.jsp"></jsp:include>

<div class="panel panel-primary panel-addLivro">

	<div class="panel-heading text-center ">Cadastro Livro</div>


	<div class="panel-body">

		<jsp:include page="/template/msg.jsp"></jsp:include>
            
            <div  style="padding-bottom:8%;">
        <form method="post" action='upload' enctype="multipart/form-data">
			<label class="form-label ages">Arquivo:</label>
			<input class="form-control" id="file" name="file" value="${param.file}" type="file" maxlength="120">
			
			<div style="padding-top:3%;">
			<input class="btn btn-primary pull-right" type="submit"	value="Enviar">
			</div>
			
			</form>
			</div>
			

		<form method="post" action="main?acao=cadastraLivro">
			<div class="form-group">
				<label  class="form-label"> ISBN Livro</label>
				<input class="form-control" name="isbn" id="isbn">
				<label class="form-label" > Titulo Livro</label>
				<input class="form-control" name="titulo" id="titulo">
				
				<!-- Testando Modal -->
				<hr>
				
				<button type="button" class="btn btn-info" data-toggle="modal" data-target="#modalCap">Novo Capítulo</button>
				
				<hr>
				<div class="text-center">
					<input class="btn btn-danger" type="reset"
						value="Cancelar">
					<input class="btn btn-info" type="submit"
						value="Cadastrar">
				</div>
				
			</div>
			
			</div>

	</div>

<jsp:include page="/template/foot.jsp"></jsp:include>
