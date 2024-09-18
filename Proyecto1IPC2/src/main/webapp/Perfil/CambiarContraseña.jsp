<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cambiar Contraseña - Revista Digital</title>
    <jsp:include page="/resources/resources.jsp" />
</head>
<body>
    <jsp:include page="/resources/header.jsp" />
    
    <div class="container my-4">
        <h2>Cambiar Contraseña</h2>
        <form action="${pageContext.servletContext.contextPath}/CambiarContraseñaServlet" method="post">
            <div class="form-group">
                <label for="nuevaContraseña">Nueva Contraseña:</label>
                <input type="password" class="form-control" id="nuevaContraseña" name="nuevaContraseña" required>
            </div>
            <div class="form-group">
                <label for="confirmarContraseña">Confirmar Nueva Contraseña:</label>
                <input type="password" class="form-control" id="confirmarContraseña" name="confirmarContraseña" required>
            </div>
            <button type="submit" class="btn btn-primary">Cambiar Contraseña</button>
        </form>
    </div>
    
    <footer class="text-center py-4">
        <p>© 2024 Revista Digital. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
