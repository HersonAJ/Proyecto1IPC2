<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
 
    <jsp:include page="/resources/resources.jsp" />
</head>
<body>
    <jsp:include page="/resources/header.jsp" />
    
    <div class="container my-4">
        

        
        <h2 class="mt-4">Cargar Nueva Revista</h2>
        <form action="${pageContext.servletContext.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="titulo" class="form-label">Título:</label>
                <input type="text" name="titulo" id="titulo" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="descripcion" class="form-label">Descripción:</label>
                <textarea name="descripcion" id="descripcion" class="form-control" required></textarea>
            </div>
            <div class="mb-3">
                <label for="categoria" class="form-label">Categoría:</label>
                <input type="text" name="categoria" id="categoria" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="costoPorDia" class="form-label">Costo por Día:</label>
                <input type="number" name="costoPorDia" id="costoPorDia" class="form-control" step="0.01" required>
            </div>
            <div class="mb-3">
                <label for="file" class="form-label">Seleccionar archivo PDF:</label>
                <input type="file" name="file" id="file" class="form-control" accept=".pdf" required>
            </div>
            
            
            
            
            <div class="mb-3">
                <label for="tags" class="form-label">Tags (separados por comas):</label>
                <input type="text" name="tags" id="tags" class="form-control" placeholder="Ejemplo: Tecnología, Innovación, Ciencia">
            </div>            
            
            
            
            
            
            
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="permiteComentarios" id="permiteComentarios">
                <label class="form-check-label" for="permiteComentarios">Permitir Comentarios</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="permiteMeGusta" id="permiteMeGusta">
                <label class="form-check-label" for="permiteMeGusta">Permitir Me Gusta</label>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Cargar</button>
        </form>
    </div>
    
    <footer class="text-center py-4">
        <p>© 2024 Revista Digital. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
