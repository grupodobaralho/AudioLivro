<%@page import="br.ages.crud.model.Livro"%>
<%@page import="java.util.List"%>
<jsp:include page="../template/head.jsp"></jsp:include>

<!-- MODAL / POPUP -->
<jsp:include page="../template/modal.jsp"></jsp:include>
 		
<div class="panel panel-primary">
   		
	<div class="panel-heading text-center">Lista de Livros</div>
               
       <div class="panel-body">
       
		<jsp:include page="/template/msg.jsp"></jsp:include>
        <div class="table-responsive">
        
        <table id="listaAlunos" class="table table-responsive table-striped table-hover table-condensed table-bordered">

            <thead>
                <tr>
                    <th style="text-align: center;">ID</th>
                    <th style="text-align: center;">ISBN</th>
                    <th style="text-align: center;">Título</th>
					<th style="text-align: center;">Autores</th>
					<th style="text-align: center;"></th>
					<th style="text-align: center;"></th>
                </tr>
            </thead>

            <tbody> 
            	<%
					List<Livro> listaLivros = (List<Livro>) request.getAttribute("listaLivros");
					for (Livro livro : listaLivros) {
				%>
				          
            	<tr>
            		<td align="center"><%=livro.getIdLivro()%></td>
	            	<td align="center"><%=livro.getISBN() %></td>
	            	<td align="center"><%=livro.getTitulo() %></td>
	            	<td align="center"><%=livro.getAutores() %></td>
	            	<td align="center">
						<form action="" method="post">
            				<a href="main?acao=telaLivro&livroIdLivro=<%=livro.getIdLivro().toString()%>" title="Editar"> <i class="glyphicon glyphicon-pencil"></i></a>
            			</form>
            		</td>
            		
            		<td align="center">
            			<form action="" method="post">
            				<a href="" title="Deletar"> <i class="glyphicon glyphicon-trash"></i></a>
            			</form>
            		</td>
            	</tr>
				<% 
					} 
				%>
			</tbody>
            
        </table> 
		</div>
    </div>
</div>
<jsp:include page="../template/foot.jsp"></jsp:include>
<script>

$(document).ready(function(){
	$('#listaAlunos').dataTable({
	    "language": {
            "lengthMenu": "Mostrando _MENU_ registros por página",
            "zeroRecords": "Sem registros - sorry",
            "info": "Mostrando _PAGE_ de _PAGES_ páginas",
            "infoEmpty": "Nenhum registros encontrados!",
            "infoFiltered": "(Filtrado _MAX_ do total deregistros)",
            "search":"Busca",
           	"paginate": {
                "first":      "Primeiro",
                "last":       "Último",
                "next":       "Próximo",
                "previous":   "Anterior"
	        },
        }
	});
});;
</script>