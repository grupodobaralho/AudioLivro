<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="../template/head.jsp"></jsp:include>

<div class="panel panel-primary panel-addLivro">

	<div class="panel-heading text-center ">Cadastro Livro</div>


	<div class="panel-body">

		<jsp:include page="/template/msg.jsp"></jsp:include>


            
        <form method="post" action='upload' enctype="multipart/form-data" >
			<label class="form-label ages">Projeto: </label>

			<label class="form-label ages">Arquivo:</label>
			<input class="form-control" id="file" name="file" value="${param.file}" type="file" maxlength="120">
			
			<input class="btn btn-primary pull-right" type="submit"	value="Enviar">
		</form>
            

	</div>
</div>

<jsp:include page="/template/foot.jsp"></jsp:include>
