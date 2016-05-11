<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="./template/head.jsp"></jsp:include>

<div class="panel panel-primary panel-addUser">

	<div class="panel-heading text-center">MODELO</div>


	<div class="panel-body">

		<jsp:include page="/template/msg.jsp"></jsp:include>


		<form method="post" action="main?acao=login">
			<div class="form-group">
				<table>
					<div class="row">
						<div class="col-sm-6">
							<img src="img/images.jpg" class="img-responsive imgLivro"
								alt="livroe">Livro1
						</div>
						<div class="col-sm-6">
							<img src="img/images.jpg" class="img-responsive imgLivro"
								alt="livroe">Livro1
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<img src="img/images.jpg" class="img-responsive imgLivro"
								alt="livroe">Livro1
						</div>
						<div class="col-sm-6">
							<img src="img/images.jpg" class="img-responsive imgLivro"
								alt="livroe">Livro1
						</div>
					</div>
				</table>
				<hr>
				<div class="text-center">
					<input class="btn btn-info login pull-center " type="submit"
						value="Perfil">
				</div>
			</div>
		</form>
	</div>
</div>

<jsp:include page="/template/foot.jsp"></jsp:include>
