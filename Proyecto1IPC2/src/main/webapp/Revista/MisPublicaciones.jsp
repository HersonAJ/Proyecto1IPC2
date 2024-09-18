<%@page import="Modelos.Publicacion"%>
<%@page import="backendDB.MisPublicacionesService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mis Publicaciones - Revista Digital</title>
    <jsp:include page="/resources/resources.jsp" />
</head>
<body>
    <jsp:include page="/resources/header.jsp" />
    
    <div class="container my-4">
        <h2>Mis Publicaciones</h2>
        <div class="row">
            <div class="col-md-12">
                <ul class="list-group">
                    <%
                        // Obtener el email del usuario desde la sesión
                        MisPublicacionesService service = new MisPublicacionesService();
                        String email = (String) session.getAttribute("usuario");
                        List<Publicacion> publicaciones = service.obtenerPublicacionesPorAutor(email);
                        
                        for (Publicacion publicacion : publicaciones) {
                    %>
                            <li class="list-group-item">
                                <h5 class="mb-1"><%= publicacion.getTitulo() %></h5>
                                <p class="mb-1"><%= publicacion.getDescripcion() %></p>
                                <small><strong>Categoría:</strong> <%= publicacion.getCategoria() %></small><br>
                                <small><strong>Fecha de Creación:</strong> <%= publicacion.getFechaCreacion() %></small><br>
                                <small><strong>Permite Comentarios:</strong> <%= publicacion.isPermiteComentarios() ? "Sí" : "No" %></small><br>
                                <small><strong>Permite Me Gusta:</strong> <%= publicacion.isPermiteMegusta() ? "Sí" : "No" %></small><br>
                                <form action="${pageContext.servletContext.contextPath}/ActualizarEstadoRevistaServlet" method="post">
                                    <input type="hidden" name="idRevista" value="<%= publicacion.getIdRevista() %>">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="permiteComentarios" <%= publicacion.isPermiteComentarios() ? "checked" : "" %>>
                                        <label class="form-check-label">Permitir Comentarios</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="permiteMegusta" <%= publicacion.isPermiteMegusta() ? "checked" : "" %>>
                                        <label class="form-check-label">Permitir Me Gusta</label>
                                    </div>
                                    <button type="submit" class="btn btn-primary mt-2">Actualizar</button>
                                </form>
                            </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
        
        <button type="button" class="btn btn-success" onclick="location.href='CargarNuevasRevistas.jsp'">Agregar Nuevas Revistas</button>
    </div>
    
    <footer class="text-center py-4">
        <p>© 2024 Revista Digital. Todos los derechos reservados.</p>
    </footer>
</body>
</html>

