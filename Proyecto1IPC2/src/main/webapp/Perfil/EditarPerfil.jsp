<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Perfil - Revista Digital</title>
    <jsp:include page="/resources/resources.jsp" />
</head>
<body>
    <jsp:include page="/resources/header.jsp" />
    
    <div class="container my-4">
        <h2>Editar Perfil</h2>
        <form action="${pageContext.servletContext.contextPath}/EditarPerfilServlet" method="post">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>" required>
            </div>
            <div class="form-group">
                <label for="descripcion">Descripción:</label>
                <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required><%= request.getAttribute("descripcion") != null ? request.getAttribute("descripcion") : "" %></textarea>
            </div>
            <div class="form-group">
                <label for="hobbies">Hobbies:</label>
                <input type="text" class="form-control" id="hobbies" name="hobbies" value="<%= request.getAttribute("hobbies") != null ? request.getAttribute("hobbies") : "" %>" required>
            </div>
            <div class="form-group">
                <label for="temasInteres">Temas de Interés:</label>
                <input type="text" class="form-control" id="temasInteres" name="temasInteres" value="<%= request.getAttribute("temasInteres") != null ? request.getAttribute("temasInteres") : "" %>" required>
            </div>
            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        </form>
    </div>
    
    <footer class="text-center py-4">
        <p>© 2024 Revista Digital. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
