<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Map<String, Object>> resultados = (List<Map<String, Object>>) request.getAttribute("resultados");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultados de Búsqueda - Revista Digital</title>
    <jsp:include page="/resources/resources.jsp" />
</head>
<body>
    <jsp:include page="/resources/header.jsp" />
    
    <!-- Contenedor principal -->
    <div class="container my-4">
        <div class="row">
            <!-- Anuncios aleatorios  -->
            <aside class="col-md-3">
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">Anuncio</h5>
                        <p class="card-text">Texto de anuncio aquí.</p>
                    </div>
                </div>
                <div class="card mb-4">
                    <img src="data:image/jpeg;" class="card-img-top" alt="Anuncio de imagen">
                    <div class="card-body">
                        <h5 class="card-title">Anuncio</h5>
                    </div>
                </div>
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">Anuncio</h5>
                        <video controls class="w-100">
                            <source src="https://www.w3schools.com/html/mov_bbb.mp4" type="video/mp4">
                            Tu navegador no soporta la reproducción de videos.
                        </video>
                    </div>
                </div>
            </aside>
            
            <!-- Contenido principal -->
            <main class="col-md-9">
                <section>
                    <h2>Resultados de Búsqueda</h2>
                    <div class="row">
                        <%
                            if (resultados != null && !resultados.isEmpty()) {
                                for (Map<String, Object> revista : resultados) {
                        %>
                        <div class="col-md-6 mb-4">
                            <div class="card h-100">
                                <div class="card-body">
                                    <h3 class="card-title"><%= revista.get("titulo") %></h3>
                                    <p class="card-text"><%= revista.get("descripcion") %></p>
                                    <small>Categoría: <%= revista.get("categoria") %></small>
                                    <br>
                                    <a href="VerRevistaBST.jsp?id=<%= revista.get("id_revista") %>" class="btn btn-primary mt-3">Ver Detalles</a>
                                    <div class="mt-3">
                                        <form action="SubscribeServlet" method="post" class="d-inline">
                                            <input type="hidden" name="id_usuario" value="<%= session.getAttribute("idUsuario") %>">
                                            <input type="hidden" name="id_revista" value="<%= revista.get("id_revista") %>">
                                            <button type="submit" class="btn btn-warning">Suscribirse</button>
                                        </form>
                                        
                                        <form action="LikeServlet" method="post" class="d-inline">
                                            <input type="hidden" name="id_usuario" value="<%= session.getAttribute("idUsuario") %>">
                                            <input type="hidden" name="id_revista" value="<%= revista.get("id_revista") %>">
                                            <button type="submit" class="btn btn-success" <%= (Boolean) revista.get("permite_megusta") ? "" : "disabled" %>>Me Gusta</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                                }
                            } else {
                        %>
                        <p>No se encontraron resultados para la búsqueda.</p>
                        <%
                            }
                        %>
                    </div>
                </section>
            </main>
        </div>
    </div>
    
    <footer class="text-center py-4">
        <p>© 2024 Revista Digital. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
