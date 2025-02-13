
	
	<div class="form-group" style="width: 100%; display: ${msgErro != null ? 'block' : 'none'}">
		<div class="alert alert-danger fade in text-center" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	    	<span class="sr-only">Erro:</span>
	    	${msgErro != null ? msgErro : ''}                
		</div>
	</div>
	
	<div class="form-group" style="width: 100%; display: ${msgSucesso != null ? 'block' : 'none'}">
		<div class="alert alert-success fade in text-center" role="alert">
			<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
	    	<span class="sr-only">Sucesso:</span>
	    	${msgSucesso != null ? msgSucesso : ''}                
		</div>
	</div>
	
	<div class="form-group" style="width: 100%; display: ${msgAviso != null ? 'block' : 'none'}">
		<div class="alert alert-warning fade in text-center" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	    	<span class="sr-only">Erro:</span>
	    	${msgAviso != null ? msgAviso : ''}              
		</div>
	</div>
	
	<div class="form-group" style="width:100%; display:none;" id="divMsgDadosInvalidos">
		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<strong>Aten��o!</strong> Os dados informados est�o inv�lidos. Por favor, verifique.
		</div>
	</div>