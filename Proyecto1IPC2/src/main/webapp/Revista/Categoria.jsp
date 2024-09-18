
<%@page import="backendDB.CategoriaService"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.HashSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Categorías - Revista Digital</title>
    <jsp:include page="/resources/resources.jsp" />
    <style>
        .selected-category {
            background-color: #f8f9fa;
            border-left: 5px solid #007bff;
        }
        .selected-category h5 {
            color: #007bff;
        }
    </style>
</head>
<body>
    <jsp:include page="/resources/header.jsp" />
    
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
                    <h2>Categorías de Revistas</h2>
                    <div class="list-group">
                        <%
                            CategoriaService categoriaService = new CategoriaService();
                            HashSet<String> categorias = categoriaService.obtenerCategorias();
                            for (String categoria : categorias) {
                                String categoriaSeleccionada = request.getParameter("categoria");
                        %>
                        <form action="${pageContext.request.contextPath}/Revista/Categoria.jsp" method="post" class="d-inline">
                            <input type="hidden" name="categoria" value="<%= categoria %>">
                            <button type="submit" class="list-group-item list-group-item-action <%= categoriaSeleccionada != null && categoriaSeleccionada.equals(categoria) ? "selected-category" : "" %>">
                                <h5 class="mb-1"><%= categoria %></h5>
                            </button>
                        </form>
                        <%
                                if (categoriaSeleccionada != null && categoriaSeleccionada.equals(categoria)) {
                                    ResultSet rsRevistas = categoriaService.obtenerRevistasPorCategoria(categoriaSeleccionada);
                                    while (rsRevistas.next()) {
                        %>
                        <div class="col-md-12 mb-4">
                            <div class="card h-100">
                                <div class="card-body">
                                    <h3 class="card-title"><%= rsRevistas.getString("titulo") %></h3>
                                    <p class="card-text"><%= rsRevistas.getString("descripcion") %></p>
                                    <a href="VerRevistaBST.jsp?id=<%= rsRevistas.getInt("id_revista") %>" class="btn btn-primary">Ver Detalles</a>
                                </div>
                            </div>
                        </div>
                        <%
                                    }
                                    rsRevistas.close();
                                }
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
