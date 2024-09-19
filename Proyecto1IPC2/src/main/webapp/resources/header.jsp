
<div class="px-3 py-2 text-bg-dark border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center my-2 my-lg-0 me-lg-auto text-white text-decoration-none">
                <i class="bi bi-bootstrap"></i>
            </a>
            <ul class="nav col-12 col-lg-auto my-2 justify-content-center my-md-0 text-small">
                <li class="text-center">
                    <a href="${pageContext.request.contextPath}/index.jsp" class="nav-link text-secondary">
                        <i class="bi bi-house d-block mx-auto mb-1"></i>
                        Inicio
                    </a>
                </li>
                <li class="text-center">
                    <a href="${pageContext.request.contextPath}/Revista/Categoria.jsp" class="nav-link text-white">
                        <i class="bi bi-speedometer2 d-block mx-auto mb-1"></i>
                        Categorías
                    </a>
                </li>
                <li class="text-center">
                    <a href="${pageContext.request.contextPath}/Revista/MisSuscripciones.jsp" class="nav-link text-white">
                        <i class="bi bi-table d-block mx-auto mb-1"></i>
                        Mis Suscripciones
                    </a>
                </li>
                <li class="text-center">
                    <a href="${pageContext.request.contextPath}/MiPerfilServlet" class="nav-link text-white">
                        <i class="bi bi-people d-block mx-auto mb-1"></i>
                        Mi Perfil
                    </a>
                </li>

                <% 
                    if (session != null && session.getAttribute("usuario") != null) { 
                        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
                        if ("editor".equalsIgnoreCase(tipoUsuario)) {
                %>
                    <li class="text-center">
                        <a href="${pageContext.request.contextPath}/Revista/MisPublicaciones.jsp" class="nav-link text-white">
                            <i class="bi bi-file-earmark-post d-block mx-auto mb-1"></i>
                            Mis Publicaciones
                        </a>
                    </li>
                    <li class="text-center">
                        <a href="${pageContext.request.contextPath}/admin/Reportes.jsp" class="nav-link text-white">
                            <i class="bi bi-file-earmark-text d-block mx-auto mb-1"></i>
                            Reportes
                        </a>
                    </li>
                <%
                        } else if ("suscriptor".equalsIgnoreCase(tipoUsuario)) {
                %>
                    <li class="text-center">
                        <a href="${pageContext.request.contextPath}/Revista/MagazineDetails.jsp" class="nav-link text-white">
                            <i class="bi bi-journal-bookmark-fill d-block mx-auto mb-1"></i>
                            Ver Revistas
                        </a>
                    </li>
                <%
                        } else if ("administrador".equalsIgnoreCase(tipoUsuario)) {
                %>
                    <!-- Opciones adicionales para Administrador -->
                    <li class="text-center">
                        <a href="${pageContext.request.contextPath}/admin/GestionAnuncios.jsp" class="nav-link text-white">
                            <i class="bi bi-megaphone d-block mx-auto mb-1"></i>
                            Gestión de Anuncios
                        </a>
                    </li>
                    <li class="text-center">
                        <a href="${pageContext.request.contextPath}/admin/AsignarCostos.jsp" class="nav-link text-white">
                            <i class="bi bi-currency-dollar d-block mx-auto mb-1"></i>
                            Asignar Costos
                        </a>
                    </li>
                    <li class="text-center">
                        <a href="${pageContext.request.contextPath}/Admin/Cartera.jsp" class="nav-link text-white">
                            <i class="bi bi-wallet d-block mx-auto mb-1"></i>
                            Cartera
                        </a>
                    </li>
                    <li class="text-center">
                        <a href="${pageContext.request.contextPath}/admin/Reportes.jsp" class="nav-link text-white">
                            <i class="bi bi-bar-chart d-block mx-auto mb-1"></i>
                            Reportes
                        </a>
                    </li>
                <%
                        }
                %>
                    <li class="text-center">
                        <a href="${pageContext.request.contextPath}/cuenta/Logout.jsp" class="nav-link text-white">
                            <i class="bi bi-box-arrow-right d-block mx-auto mb-1"></i>
                            Cerrar Sesión
                        </a>
                    </li>
                <%
                    } else {
                %>
                    <li class="text-center">
                        <a href="${pageContext.request.contextPath}/cuenta/Login.jsp" class="nav-link text-white">
                            <i class="bi bi-box-arrow-in-right d-block mx-auto mb-1"></i>
                            Iniciar Sesión
                        </a>
                    </li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</div>
