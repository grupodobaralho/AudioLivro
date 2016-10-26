package br.ages.crud.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import br.ages.audio.bo.BlocoBO;
import br.ages.crud.dao.BlocoDAO;
import br.ages.crud.model.Bloco;
import br.ages.crud.model.Projeto;
import br.ages.crud.model.StatusBlocoEnum;
import br.ages.crud.util.Constantes;
 
/**
 * A Java servlet that handles file upload from client.
 *
 * @author www.codejava.net
 *//*
@WebServlet(name="/upload")
public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "c:\\uploadProjetos";
 
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
 

/**
     * Upon receiving file upload submission, parses the request to read
     * upload data and saves the file on disk.
     *//*
   
       /
protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }
 
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
 
        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = getServletContext().getRealPath("")
                + File.separator + UPLOAD_DIRECTORY;
         
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
 
                        // saves the file on disk
                        item.write(storeFile);
                        request.setAttribute("message",
                            "Upload has been done successfully!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }
        // redirects client to message page
        getServletContext().getRequestDispatcher("/addLivro.jsp").forward(
                request, response);
    }
    */
@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 15, maxFileSize = Constantes.PROJETO_ARQUIVO_MAX_BYTES, maxRequestSize = 1024 * 1024 * 15)
public class FileUploadServlet extends HttpServlet {
	Logger logger = Logger.getLogger("servlet.FileUploadServlet");
	private static final long serialVersionUID = 2L;
	private static ResourceBundle configPath = ResourceBundle.getBundle(Constantes.AMBIENTE_PROPERTIES);

	private static final String SAVE_DIR =  configPath.getString(Constantes.PROJETO_UPLOAD_PATH);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			logger.debug("Iniciando o Upload");
			
			String savePath = SAVE_DIR + File.separator ;
			File fileSaveDir = new File(savePath);
			
			
			
			String isbn = request.getParameter("isbn");
			String idCap = request.getParameter("idCapitulo");
			System.out.println(isbn);
			
			if (!fileSaveDir.exists())
				fileSaveDir.mkdir();

			Part part = request.getPart("file");
			String fileName = extractFileName(part);
			File file1 = new File(savePath + File.separator + fileName);
			part.write(file1.toString());
			
			// caminho que será salvo no banco com o local onde o pdf está salvo
			String caminho = savePath + fileName;
			
			Bloco bloco = new Bloco(caminho, "nao definido", StatusBlocoEnum.DISPONIVEL_PARA_GRAVACAO);
			BlocoBO blocoBO = new BlocoBO();
			int	idbloco = blocoBO.cadastraBloco(bloco, Integer.parseInt(idCap));
			bloco.setId_bloco(idbloco);
			
			//novo caminho com o nome padrao do bloco
			caminho = savePath + isbn+"_CAP"+idCap+"_B"+idbloco+".pdf";
			
			//altera o nome para o nome do bloco
			alteraNome(file1, caminho);
			blocoBO.alteraCaminho(caminho, idbloco);
			
			request.setAttribute("msgSucesso", "Upload feito com sucesso!");
			
			
			
			getServletContext().getRequestDispatcher("/main?acao=telaLivro&idLivro=").forward(request, response);
			
			logger.info("Executado o Upload em: " + savePath + " - " + fileName );
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	
	public void alteraNome(File file1, String caminho){
		File file2 = new File(caminho);
		file1.renameTo(file2);
	}
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	super.doPost(req, resp);
    }
}