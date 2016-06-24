
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
	  	var capituloId = botao.data('capituloId');
		
	  	$(this).find('.modal-title').text('Adicionar Blocos');
	  	
	  	$('#formBloco').attr('action', "main?acao=telaBloco&id_capitulo=" + capituloId);
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
		        	<h4 class="modal-title">Cadastrar Capitulo</h4>
	      		</div>
		      	<div class="modal-body">
	        		<form method="post" action="main?acao=cadastraLivro">
		        		<div class="form-group">
		        			<label  class="form-label"> Nome capitulo</label>
							<input class="form-control" name="nome" id="nome">
							<label class="form-label" > Numero capitulo</label>
							<input class="form-control" name="numero" id="numero">
	        			</div>
        			</form>	
		      	</div>
		      	<div class="modal-footer">
		      		<form action="" method="post" id="formBloco">
		      			<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
		      			<button type="submit" class="btn btn-primary">Salvar</button>
		      		</form>
		      	</div>
		    </div>
	  	</div>
	</div>