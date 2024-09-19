<%@page import="backendDB.ConexionDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalles de Revistas - Revista Digital</title>
    <jsp:include page="/resources/resources.jsp" />
</head>
<body>
    <jsp:include page="/resources/header.jsp" />
        <!-- Formulario de búsqueda -->
<div class="container my-4">
    <form action="${pageContext.request.contextPath}/BusquedaServlet" method="get" class="form-inline">
        <div class="form-group mx-sm-3 mb-2">
            <label for="search" class="sr-only">Buscar</label>
            <input type="text" class="form-control" id="search" name="query" placeholder="Buscar revistas">
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <select class="form-control" id="tipoBusqueda" name="tipoBusqueda">
                <option value="categoria">Categoría</option>
                <option value="tags">Tags</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mb-2">Buscar</button>
    </form>
</div>
    
    
    <div class="container my-4">
        <div class="row">
            <!-- Anuncios aleatorios -->
            <aside class="col-md-3">
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">Anuncio</h5>
                        <p class="card-text">Este es un anuncio de texto que puede ser un poco más extenso para ver cómo podría quedar dentro del div cambiando todo.</p>
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
                    <h2>Revistas Disponibles</h2>
                    <div class="row">
                        <%
                            Connection connection = null;
                            Statement statement = null;
                            ResultSet resultSet = null;
                            try {
                                connection = ConexionDB.getConnection();
                                statement = connection.createStatement();
                                resultSet = statement.executeQuery("SELECT * FROM Revista");
                                while (resultSet.next()) {
                        %>
                        <div class="col-md-6 mb-4">
                            <div class="card h-100">
                                <div class="card-body">
                                    <h3 class="card-title"><%= resultSet.getString("titulo") %></h3>
                                    <p class="card-text"><%= resultSet.getString("descripcion") %></p>
                                    <a href="VerRevista.jsp?id=<%= resultSet.getInt("id_revista") %>" class="btn btn-primary">Ver Detalles</a>
                                    <div class="mt-3">

                                         <form action="${pageContext.request.contextPath}/SuscripcionServlet" method="post" class="d-inline">
                                            <input type="hidden" name="id_usuario" value="<%= session.getAttribute("idUsuario") %>">
                                            <input type="hidden" name="id_revista" value="<%= resultSet.getInt("id_revista") %>">
                                            
                                            <div class="mb-3">
                                            <label for="fechaSuscripcion" class="form-label">Fecha de Suscripción:</label>
                                            <input type="date" name="fechaSuscripcion" id="fechaSuscripcion" class="form-control" required>
                                            </div>
                                            
                                            <button type="submit" class="btn btn-warning">Suscribirse</button>
                                        </form>                                       
                                                 
                                        
                                        
<form action="${pageContext.request.contextPath}/LikeServlet" method="post" class="d-inline">
    <input type="hidden" name="id_usuario" value="<%= session.getAttribute("idUsuario") %>">
    <input type="hidden" name="id_revista" value="<%= resultSet.getInt("id_revista") %>">
    <button type="submit" class="btn btn-success" <%= resultSet.getBoolean("permite_megusta") ? "": "disabled" %>   >Me Gusta</button>
</form>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (resultSet != null) try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
                                if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
                                if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
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
