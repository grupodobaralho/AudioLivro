
<script>
$( document ).ready(function() {
	$('#modalExcluir').on('show.bs.modal', function (event) {
	  	var botao = $(event.relatedTarget);
	  	var usuario = botao.data('usuario');
		var id = botao.data('id');
	  	
	  	$(this).find('.modal-title').text('Excluir usuário');
	  	$(this).find('#modal-descricao').text('Você realmente deseja excluir o usuário (' + usuario + ')?');
	  	
	  	$('#formExcluir').attr('action', "main?acao=removerUsuario&id_usuario=" + id);
	});
	
	
	$('#modalEditar').on('show.bs.modal', function (event) {
	  	var botao = $(event.relatedTarget);
	  	var usuario = botao.data('usuario');
		var id = botao.data('id');
	  	
	  	$(this).find('.modal-title').text('Editar usuário');
	  	$(this).find('#modal-descricao').text('Você realmente deseja editar o usuário (' + usuario + ')?');
	  	
	  	$('#formEditar').attr('action', "main?acao=telaUser&id_usuario=" + id + "&isEdit=true");
	});
	
	$('#modalBloco').on('show.bs.modal', function (event) {
	  	var botao = $(event.relatedTarget);
	  	var capituloId = botao.data('capitulo_id');
	  	var capituloNome = botao.data('capitulo_nome');
	  	var livroNome = botao.data('livro_nome');
	  	var capituloNumero = botao.data('capitulo_numero');
	  	var isbn = botao.data('livro_isbn');
		
	  	$(this).find('.modal-title').text('Adicionar Blocos');
		$(this).find('#livroNome').text(livroNome);
		$(this).find('#capituloNome').text(capituloNome);
		
		$( "#idCapitulo" ).val(capituloId);
		$( "#isbn" ).val(isbn);
	});
	
	$( this ).on('click', 'button#modalBlocoBtnSalvar', function() {
		$( 'form#formBloco' ).submit();
	});
	
	$('#modalExcluirLivro').on('show.bs.modal', function (event) {
	  	var botao = $(event.relatedTarget);
	  	var livro = botao.data('livro');
		var id = botao.data('id');
	  	
	  	$(this).find('.modal-title').text('Excluir livro');
	  	$(this).find('#modal-descricao').text('Você realmente deseja excluir o livro ' + livro + '?');
	  	
	  	$('#formExcluirLivro').attr('action', "main?acao=removerLivro&idLivro=" + id);
	});
});
</script>

	<div class="modal fade" id="modalExcluir" role="dialog">
  		<div class="modal-dialog">
	  		<div class="modal-content">
	  		
	      		<div class="modal-header modal-ages">
		        	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        	<h4 class="modal-title"></h4>
	      		</div>
	      		
		      	<div class="modal-body">
	        		<p id="modal-descricao"></p>
		      	</div>
		      	
		      	<div class="modal-footer">
		      		<form action="" method="post" id="formExcluir">
		      			<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
		      			<button type="submit" class="btn btn-primary">Excluir</button>
		      		</form>
		      	</div>
		      	
		    </div>
	  	</div>
	</div>
	
	<div class="modal fade" id="modalEditar" role="dialog">
  		<div class="modal-dialog">
	  		<div class="modal-content">
	  		
	      		<div class="modal-header modal-ages">
		        	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        	<h4 class="modal-title"></h4>
	      		</div>
	      		
		      	<div class="modal-body">
  	        		<p id="modal-descricao"></p>
		      	</div>
		      	
		      	<div class="modal-footer">
		      		<form action="" method="post" id="formEditar">
		      			<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
		      			<button type="submit" class="btn btn-primary">Editar</button>
		      		</form>
		      	</div>
		      	
		    </div>
	  	</div>
	</div>
	
	<div class="modal fade" id="modalBloco" role="dialog">
  		<div class="modal-dialog">
	  		<div class="modal-content">
	      		<div class="modal-header modal-ages">
		        	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        	<h4 class="modal-title">Cadastrar Bloco</h4>
	      		</div>
		      	<div class="modal-body">
		      		<div class="form-group">
        				<label for="livro" class="col-sm-2 control-label">Livro: </label>
        				<label for="livroNome" class="col-sm-10 control-label leftAli" id="livroNome"> </label>
        				
	        		</div>
	        		<div class="form-group">
	        			<label for="capitulo" class="col-sm-2 control-label">Capitulo: </label>
	        			<label for="capituloNome" class="col-sm-10 control-label leftAli" id="capituloNome" > </label>
	        		</div>
	        		<form method="post" action='upload' enctype="multipart/form-data" id="formBloco">
	        			<input type="hidden" id="idLivro" name="idLivro" value="" />
		      			<input type="hidden" id="isbn" name="isbn" value="" />
		      			<input type="hidden" id="idCapitulo" name="idCapitulo" value="" />
		      			
						<label class="form-label ages">Arquivo:</label>
						<input class="form-control" id="file" name="file" value="${param.file}" type="file" maxlength="120">
					</form>
		      	</div>
		      	<div class="modal-footer">
	      			<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
	      			<button type="button" class="btn btn-primary" id="modalBlocoBtnSalvar">Salvar</button>
		      	</div>
		    </div>
	  	</div>
	</div>
	
	<div class="modal fade" id="modalExcluirLivro" role="dialog">
  		<div class="modal-dialog">
	  		<div class="modal-content">
	  		
	      		<div class="modal-header modal-ages">
		        	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        	<h4 class="modal-title"></h4>
	      		</div>
	      		
		      	<div class="modal-body">
	        		<p id="modal-descricao"></p>
		      	</div>
		      	
		      	<div class="modal-footer">
		      		<form action="" method="post" id="formExcluirLivro">
		      			<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
		      			<button type="submit" class="btn btn-primary">Excluir</button>
		      		</form>
		      	</div>
		      	
		    </div>
	  	</div>
	</div>