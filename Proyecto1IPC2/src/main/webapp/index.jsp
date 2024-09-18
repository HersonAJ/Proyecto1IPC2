<%@page import="backendDB.RevistaService"%>
<%@page import="Modelos.Revista"%>
<%@page import="java.util.List"%>
<%@page import="backenDB.RevistaService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HomePage - Revista Digital</title>
    <jsp:include page="/resources/resources.jsp" />
</head>
<body>
    <jsp:include page="/resources/header.jsp" />
    
    <div class="container my-4">
        <div class="row">
            <!-- Anuncios a la izquierda -->
            <aside class="col-md-2">
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
            <main class="col-md-8">
                <section>
                    <h2>Revistas Destacadas</h2>
                    <!-- Aquí se mostrarán las revistas destacadas -->
                    <div class="row">
                        <%
                            RevistaService revistaService = new RevistaService();
                            List<Revista> revistas = revistaService.obtenerRevistasDestacadas();
                            for (Revista revista : revistas) {
                        %>
                        <div class="col-md-12">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h5 class="card-title"><%= revista.getTitulo() %></h5>
                                    <p class="card-text"><%= revista.getDescripcion() %></p>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </section>
            </main>
            
            <!-- Anuncios a la derecha -->
            <aside class="col-md-2">
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
        </div>
    </div>
</body>
</html>

