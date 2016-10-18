<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="br.ages.crud.model.Livro"%>
<%@page import="br.ages.crud.model.Capitulo"%>
<%@page import="java.util.ArrayList"%>

<jsp:include page="../template/head.jsp"></jsp:include>

<!-- MODAL / POPUP -->
<jsp:include page="../template/modal.jsp"></jsp:include>
<jsp:include page="/template/msg.jsp"></jsp:include>
<%
	Livro livro = (Livro) request.getAttribute("livro");
%>
<div class="panel panel-primary">
	<div class="panel-heading text-center">
		Cadastro Livro
	</div>
	<div class="panel-body">
		<form method="post" action="" class="form-horizontal" id="formSaveLivro">
			<input type="hidden" id="livroIdLivro" name="livroIdLivro" value="<%=(livro != null) ? livro.getIdLivro() : ""%>" />
			<input type="hidden" id="msg" name="msg" value="" />
			
			<div class="form-group">
				<label for="livroIsbn" class="col-sm-2 control-label">ISBN</label>
			    <div class="col-sm-10" id="divISBN">
			      <input type="text" class="form-control" id="livroIsbn" name="livroIsbn" maxlength="17" placeholder="ISBN" value="<%= (livro != null) ? livro.getISBN() : ""%>">
			    </div>
			</div>
			<div class="form-group">
				<label for="titulo" class="col-sm-2 control-label">Título</label>
			    <div class="col-sm-10" id="divTitulo">
			      <input type="text" class="form-control" id="titulo" name="titulo" maxlength="100" placeholder="Título" value="<%=(livro != null) ? livro.getTitulo() : ""%>">
			    </div>
			</div>
			<div class="form-group">
				<label for="autores" class="col-sm-2 control-label">Autores</label>
			    <div class="col-sm-10" id="divAutores">
			      <input type="text" class="form-control" id="autores" name="autores" maxlength="150" placeholder="Autores" value="<%=(livro != null) ? livro.getAutores() : ""%>">
			    </div>
			</div>
			<hr>
			<h4>Capítulos</h4>
			<div class="form-group">
				<input type="hidden" id="idCapituloEditado" value="0" />
			    <div class="col-sm-8" id="divCapituloNome">
			      <input type="text" class="form-control" id="capituloNome" placeholder="Nome capítulo">
			    </div>
			    <div class="col-sm-3" id="divCapituloNumero">
			      <input type="text" class="form-control" id="capituloNumero" placeholder="Número capítulo">
			    </div>
			    <div class="col-sm-1">
			    	<button type="button" class="btn btn-success" id="addTitulo" name="addTitulo">
			    		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			    	</button>
			    </div>
			</div>
			<div class="form-group">
				<div class="col-sm-12">
					<table class="table table-striped header-fixed" id="tableCapitulos">
						<thead>
							<tr>
								<th style="width: 15%; text-align: center;">ID</th>
								<th style="width: 15%; text-align: center;">Capítulo</th>
								<th style="width: 40%; text-align: center;">Nome</th>
								<th style="width: 15%; text-align: center;">Qtd Blocos</th>
								<th style="width: 15%; text-align: center;">Ações</th>
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList<Capitulo> capitulos = (ArrayList<Capitulo>) request.getAttribute("capitulos");
								if (capitulos != null) {
									for (Capitulo capitulo : capitulos) {
							%>
							<tr>
								<td style="width: 15%; text-align: center;"><%=capitulo.getIdCapitulo()%></td>
            					<td style="width: 15%; text-align: center;"><%=capitulo.getNumero()%></td>
            					<td style="width: 40%; text-align: center;"><%=capitulo.getNome()%></td>
            					<td style="width: 15%; text-align: center;">0</td>
            					<td style="width: 15%; text-align: center;">
            						<button type="button" class="btn btn-default btn-xs" title="Editar" id="editCapitulo">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									</button>
									<button type="button" class="btn btn-default btn-xs" title="Remover" id="deleteCapitulo">
										<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									</button>
									<button type="button" class="btn btn-default btn-xs" title="Adicionar bloco" data-toggle="modal" data-target="#modalBloco" data-livro_nome="<%=livro.getTitulo()%>" data-capitulo_nome="<%=capitulo.getNome()%>" data-capitulo_numero="<%=capitulo.getNumero()%>" data-capitulo_id="<%=capitulo.getIdCapitulo()%>" data-livro_isbn="<%=livro.getISBN()%>">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
									</button>
            					</td>
            				</tr>
							<% 
									}
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10"></div>
				<div class="col-sm-2">
					<input class="btn btn-info" type="button" id="saveLivro" value="Salvar">
				</div>
			</div>
		</form>
	</div>
</div>
<jsp:include page="/template/foot.jsp"></jsp:include>

<script type="text/javascript">
	$( document ).ready(function() {
		var arrCapitulos = [];
		var livro = null;
		
		function hasCapituloNumero(numero){
			var existe = false;
			var table = $('#tableCapitulos');

			table.find('tr').each(function(indice){
				if(indice>0){
					if(numero === this.cells[1].innerHTML){
						existe = true;
					}
				}
			    
			});
			return existe;
		}
		
		function hasCapituloNome(nome){
			var existe = false;
			var table = $('#tableCapitulos');

			table.find('tr').each(function(indice){
				if(indice>0){
					if(nome === this.cells[2].innerHTML){
						existe = true;
					}
				}
			    
			});
			return existe;
		}
		
		function addCapituloNaTabela(idCapitulo, capituloNumero, capituloNome){
			var contentToAppend = "	<tr>";
			contentToAppend+= "		<td style=\"width: 15%; text-align: center;\">"+ idCapitulo +"</td>";
			contentToAppend+= "		<td style=\"width: 15%; text-align: center;\">" + capituloNumero + "</td>";
			contentToAppend+= "		<td style=\"width: 40%; text-align: center;\">" + capituloNome + "</td>";
			contentToAppend+= "		<td style=\"width: 15%; text-align: center;\">0</td>";
			contentToAppend+= "		<td style=\"width: 15%; text-align: center;\">";
			contentToAppend+= "			<button type=\"button\" class=\"btn btn-default btn-xs\" title=\"Editar\" id=\"editCapitulo\">";
			contentToAppend+= "				<span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span>";
			contentToAppend+= "			</button>";
			contentToAppend+= "			<button type=\"button\" class=\"btn btn-default btn-xs\" title=\"Remover\" id=\"deleteCapitulo\">";
			contentToAppend+= "				<span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span>";
			contentToAppend+= "			</button>";
			contentToAppend+= "			<button type=\"button\" class=\"btn btn-default btn-xs\" title=\"Adicionar bloco\" data-toggle=\"modal\" data-target=\"#modalCap\" disabled=\"disabled\">";
			contentToAppend+= "				<span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span>";
			contentToAppend+= "			</button>";
			contentToAppend+= "		</td>";
			contentToAppend+= "	</tr>";
			$('#tableCapitulos > tbody:last-child').append(contentToAppend);
		}
		
		// Ação do botão que salva o formulário
		$( this ).on('click', '#saveLivro', function() {
			if ( validateForm() ) {
				$( "#saveLivro" ).val("Salvando...");
				$( this ).prop("disabled", true);
				
				livro = new Object();
				livro.idLivro = $( "#livroIdLivro" ).val() != "" ? $( "#livroIdLivro" ).val() : null;
				livro.ISBN = $( "#livroIsbn" ).val();
				livro.titulo = $( "#titulo" ).val();
				livro.autores = $( "#autores" ).val();
				
				console.log($( "#livroIdLivro" ).val());
				
				sendDataToBackend();
			}
		});
		// Ação do botão que adiciona títulos
		$( this ).on('click', '#addTitulo', function() {
			addCapitulo();
		});
		// Ação do botão que edita títulos
		$( this ).on('click', 'button#editCapitulo', function() {
			editCapitulo(this);
		});
		// Ação do botão que deleta títulos
		$( this ).on('click', 'button#deleteCapitulo', function() {
			deleteCapitulo(this);
		});
		
		// Functions
		function addCapitulo() {			
			if(validaCamposCapitulo()) {
				var idCapitulo = $( "input#idCapituloEditado" ).val();
				var capituloNome = $( "input#capituloNome" ).val();
				var capituloNumero = $( "input#capituloNumero" ).val().replace(/^0+/, '');
				var isNumeric = Math.floor(capituloNumero) == capituloNumero && $.isNumeric(capituloNumero);
				var capitulo_exists = existsCapitulo(capituloNumero);
				
				$( "#divCapituloNome" ).removeClass("has-error");
				$( "#divCapituloNumero" ).removeClass("has-error");
				$( '#divMsgDadosInvalidos' ).hide();
				
				// Cria o objeto de capitulo
				var obj = new Object();
				obj.idCapitulo = idCapitulo;
				obj.nome = capituloNome;
				obj.numero = capituloNumero;
				arrCapitulos.push(obj);
				
				$( "input#idCapituloEditado" ).val("0");
				$( "input#capituloNome" ).val("");
				$( "input#capituloNumero" ).val("");
				
				addCapituloNaTabela(idCapitulo, capituloNumero, capituloNome);
			}			
		}
		
		function validaCamposCapitulo(){
			var capituloNome = $( "input#capituloNome" ).val();
			var capituloNumero = $( "input#capituloNumero" ).val().replace(/^0+/, '');
			var isNumeric = Math.floor(capituloNumero) == capituloNumero && $.isNumeric(capituloNumero);
			var capitulo_exists = existsCapitulo(capituloNumero);
			
			var camposValidos = true;
			if ( capituloNome.length == 0 || hasCapituloNome(capituloNome)) {
				$( "#divCapituloNome" ).addClass("has-error");
				camposValidos = false;
			}
			if ( capituloNumero.length == 0 || !isNumeric || hasCapituloNumero(capituloNumero)) {
				$( "#divCapituloNumero" ).addClass("has-error");
				camposValidos = false;
			}
			if(!camposValidos){
				$( '#divMsgDadosInvalidos' ).show();
			}
			
			return camposValidos;
		}
		
		function editCapitulo(btn) {
			// Busca a coluna pai do botão
			var td_Btn = $( btn ).parent();
			// Busca a linha pai da coluna do botão
			var tr_td_Btn = $( td_Btn ).parent();
			// Busca todas as colunas da linha do botão
			var tds = $( tr_td_Btn ).children();
			
			// Seleciona apenas as colunas do numero do capítulo e do nome do capítulo
			var idCapituloEditado = tds.eq(0).text();
			var capituloNumero = tds.eq(1).text();
			var capituloNome = tds.eq(2).text();
			
			// Preenche os valores nos respectivos campos
			$( "input#idCapituloEditado" ).val(idCapituloEditado);
			$( "input#capituloNome" ).val(capituloNome);
			$( "input#capituloNumero" ).val(capituloNumero);
			
			// Remove a linha da tabela
			$( tr_td_Btn ).remove();
		}
		
		function deleteCapitulo(btn) {
			// Busca a coluna pai do botão
			var td_Btn = $( btn ).parent();
			// Busca a linha pai da coluna do botão
			var tr_td_Btn = $( td_Btn ).parent();
			// Busca todas as colunas da linha do botão
			var tds = $( tr_td_Btn ).children();
			
			// Seleciona apenas as colunas do numero do capítulo e do nome do capítulo
			var idCapitulo = tds.eq(0).text();
			var capituloNumero = tds.eq(1).text();
			var capituloNome = tds.eq(2).text();
			
			var existe = false;
			$.each(arrCapitulos, function( index, value ) {
				if(value.idCapitulo === idCapitulo && value.nome === capituloNome && value.numero === capituloNumero){
					existe = true;
					//Remover obj da lista :/
					arrCapitulos.pop(index);
					return;
				}
			});
			
			// Cria o objeto de capitulo
			if(!existe){
				var obj = new Object();
				obj.idCapitulo = idCapitulo;
				obj.nome = capituloNome;
				obj.numero = capituloNumero;
				arrCapitulos.push(obj);
			}
			
			// Remove a linha da tabela
			$( tr_td_Btn ).remove();
		}
		
		function validateForm() {
			if ( $( '#livroIsbn' ).val().length == 0 ||
				 $( '#titulo' ).val().length == 0 || 
				 $( '#autores' ).val().length == 0) {
				
				if ( $( '#livroIsbn' ).val().length == 0 ) {
					$( '#divISBN' ).addClass("has-error");
				}
				else {
					$( '#divISBN' ).removeClass("has-error");
				}
				
				if ( $( '#titulo' ).val().length == 0 ) {
					$( '#divTitulo' ).addClass("has-error");
				}
				else {
					$( '#divTitulo' ).removeClass("has-error");
				}
				
				if ( $( '#autores' ).val().length == 0 ) {
					$( '#divAutores' ).addClass("has-error");
				}
				else {
					$( '#divAutores' ).removeClass("has-error");
				}
				
				$( '#divMsgDadosInvalidos' ).show();
				
				return false;
			}
			
			$( '#divMsgDadosInvalidos' ).hide();
			
			return true;
		}
		
		function existsCapitulo(capituloNumero) {
			for ( x in arrCapitulos ) {
				capitulo = arrCapitulos[x];
				if ( capitulo.numero == capituloNumero ) {
					return true;
				}
			}
			return false;
		}
		
		function sendDataToBackend() {
			$.ajax({
				url: "main?acao=cadastraLivro",
				type: "POST",
				dataType: "JSON",
				data: {
					capitulosToUpsert: JSON.stringify(arrCapitulos),
					livro: JSON.stringify(livro)
				},
				statusCode: {
					200: function(p) {
						var arr = p.responseText.split(';');
						var idLivro = arr[0];
						var msg = arr[1];
						var urlComplement = "";
						
						if (msg != "") {
							$( "#msg" ).val(msg);
						}
						if(idLivro != null && idLivro != "0" && idLivro != "null") {
							urlComplement = "&livroIdLivro=" + idLivro;
						}
						
						$( "#saveLivro" ).prop("disabled", false);
						$( "#saveLivro" ).val("Salvar");
						
						$( "form#formSaveLivro" ).attr("action", "main?acao=telaLivro" + urlComplement);
						$( "form#formSaveLivro" ).submit();
					}
				}
			});
		}
	});
</script>