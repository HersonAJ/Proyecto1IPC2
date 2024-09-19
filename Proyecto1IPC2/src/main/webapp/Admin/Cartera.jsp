<%-- 
    Document   : Cartera
    Created on : 18 sept 2024, 12:05:32
    Author     : herson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Administración de Cartera - Revista Digital</title>
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
                    <h2>Administración de Cartera Global</h2>
                    <form action="${pageContext.request.contextPath}/CarteraServlet" method="post">
                        <div class="form-group">
                            <label for="monto">Monto a recargar:</label>
                            <input type="number" id="monto" name="monto" step="0.01" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Recargar</button>
                    </form>
                    <c:if test="${not empty mensaje}">
                        <div class="alert alert-info mt-3">
                            ${mensaje}
                        </div>
                    </c:if>
                    <h3>Saldo Actual:  ${saldoActual} </h3>
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
    
    <footer class="text-center py-4">
        <p>© 2024 Revista Digital. Todos los derechos reservados.</p>
    </footer>
</body>
</html>






