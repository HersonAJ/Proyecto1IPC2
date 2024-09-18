<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mi Perfil - Revista Digital</title>
    <jsp:include page="/resources/resources.jsp" />
</head>
<body>
    <jsp:include page="/resources/header.jsp" />
    
    <div class="container my-4">
        <h2>Información del Perfil</h2>
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body text-center">
                        <%
                            String fotoPerfilBase64 = (String) request.getAttribute("fotoPerfilBase64");
                            if (fotoPerfilBase64 != null) {
                        %>
                            <img src="data:image/jpeg;base64,<%= fotoPerfilBase64 %>" alt="Foto de Perfil" class="img-fluid rounded-circle mb-3"style="width: 150px; height: 150px; object-fit: cover;" alt="Imagen Circular">
                        <%
                            } else {
                        %>
                            <img src="img/default-profile.png" alt="Foto de Perfil" class="img-fluid rounded-circle mb-3">
                        <%
                            }
                        %>
                        <h5 class="card-title"><%= request.getAttribute("nombre") %></h5>
                        <p class="card-text"><%= request.getAttribute("email") %></p>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card">
                    <div class="card-body">
                        <p><strong>Descripción:</strong> <%= request.getAttribute("descripcion") %></p>
                        <p><strong>Hobbies:</strong> <%= request.getAttribute("hobbies") %></p>
                        <p><strong>Temas de Interés:</strong> <%= request.getAttribute("temasInteres") %></p>
                        <p><strong>Tipo de Usuario:</strong> <%= request.getAttribute("tipoUsuario") %></p>

                    </div>
                </div>
                <div class="mt-3">
                    <a href="Perfil/EditarPerfil.jsp" class="btn btn-primary">Editar Perfil</a>
                    <a href="Perfil/CambiarContraseña.jsp" class="btn btn-secondary">Cambiar Contraseña</a>
                </div>
            </div>
        </div>
    </div>
    
    <footer class="text-center py-4">
        <p>© 2024 Revista Digital. Todos los derechos reservados.</p>
    </footer>
</body>
</html>

