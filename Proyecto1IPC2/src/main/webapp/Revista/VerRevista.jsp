
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="backendDB.RevistaService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalles de la Revista - Revista Digital</title>
    <jsp:include page="/resources/resources.jsp" />
</head>
<body>
    <jsp:include page="/resources/header.jsp" />
    
    <div class="container my-4">
        <div class="row">
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
            
            <main class="col-md-9">
                <section>
                    <h2>Detalles de la Revista</h2>
<%
    int idRevista = Integer.parseInt(request.getParameter("id"));
    int idUsuario = (Integer) session.getAttribute("idUsuario");
    RevistaService revistaService = new RevistaService();
    boolean isSuscrito = false;
    boolean permiteComentarios = false;
    ResultSet revistaResultSet = null;
    ResultSet comentariosResultSet = null;

    try {
        isSuscrito = revistaService.isUsuarioSuscrito(idUsuario, idRevista);
        revistaResultSet = revistaService.getRevistaDetalles(idRevista);
        comentariosResultSet = revistaService.getComentarios(idRevista);

        if (revistaResultSet.next()) {
            permiteComentarios = revistaResultSet.getBoolean("permite_comentarios");
%>
                    <div class="card mb-4">
                        <div class="card-body">
                            <h3 class="card-title"><%= revistaResultSet.getString("titulo") %></h3>
                            <p class="card-text"><strong>Descripción:</strong> <%= revistaResultSet.getString("descripcion") %></p>
                            <p class="card-text"><strong>Autor:</strong> <%= revistaResultSet.getString("autor") %></p>
                            <p class="card-text"><strong>Categoría:</strong> <%= revistaResultSet.getString("categoria") %></p>
                            <p class="card-text"><strong>Me Gusta:</strong> <%= revistaResultSet.getInt("total_megusta") %></p>
                            
                            <iframe src="${pageContext.request.contextPath}/VerPdfServlet?id=<%= idRevista %>" width="100%" height="600px"></iframe>
                            <a href="${pageContext.request.contextPath}/VerPdfServlet?id=<%= idRevista %>" class="btn btn-primary mt-3" download>Descargar PDF</a>
                        </div>
                    </div>
<%
        } else {
%>
                    <p>No se encontraron detalles para esta revista.</p>
<%
        }

        if (comentariosResultSet != null && comentariosResultSet.next()) {
%>
                    <div class="card mb-4">
                        <div class="card-body">
                            <h4 class="card-title">Comentarios</h4>
<%
            do {
%>
                            <div class="mb-3">
                                <p><strong><%= comentariosResultSet.getString("nombre") %>:</strong> <%= comentariosResultSet.getString("contenido") %></p>
                                <p class="text-muted"><small><%= comentariosResultSet.getTimestamp("fecha_creacion") %></small></p>
                            </div>
<%
            } while (comentariosResultSet.next());
%>
                        </div>
                    </div>
<%
        }

        if (isSuscrito && permiteComentarios) {
%>
                    <div class="card mb-4">
                        <div class="card-body">
                            <h4 class="card-title">Agregar un comentario</h4>
                            <form action="${pageContext.request.contextPath}/AgregarComentarioServlet" method="post">
                                <div class="form-group">
                                    <textarea class="form-control" name="contenido" rows="3" required></textarea>
                                </div>
                                <input type="hidden" name="idRevista" value="<%= idRevista %>">
                                <button type="submit" class="btn btn-primary mt-2">Enviar</button>
                            </form>
                        </div>
                    </div>
<%
        } else if (!isSuscrito) {
%>
                    <p>Debes estar suscrito para agregar comentarios.</p>
<%
        } else if (!permiteComentarios) {
%>
                    <p>Esta revista no permite comentarios.</p>
<%
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (revistaResultSet != null) try { revistaResultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (comentariosResultSet != null) try { comentariosResultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
%>
                </section>
            </main>
        </div>
    </div>
    
    <footer class="text-center py-4">
        <p>© 2024 Revista Digital. Todos los derechos reservados.</p>
    </footer>
</body>
</html>