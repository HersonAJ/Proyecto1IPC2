
<div class="px-3 py-2 text-bg-dark border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center my-2 my-lg-0 me-lg-auto text-white text-decoration-none">
                <i class="bi bi-bootstrap"></i>
            </a>
            <ul class="nav col-12 col-lg-auto my-2 justify-content-center my-md-0 text-small">
                <li class="text-center">
                    <a href="HomePage3.jsp" class="nav-link text-secondary">
                        <i class="bi bi-house d-block mx-auto mb-1"></i>
                        Inicio
                    </a>
                </li>
                <li class="text-center">
                    <a href="CategoriasBST.jsp" class="nav-link text-white">
                        <i class="bi bi-speedometer2 d-block mx-auto mb-1"></i>
                        Categorías
                    </a>
                </li>
                <li class="text-center">
                    <a href="MisSuscripciones.jsp" class="nav-link text-white">
                        <i class="bi bi-table d-block mx-auto mb-1"></i>
                        Mis Suscripciones
                    </a>
                </li>
                <li class="text-center">
                    <a href="miPerfil" class="nav-link text-white">
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
                        <a href="MisPublicacionesBST.jsp" class="nav-link text-white">
                            <i class="bi bi-people d-block mx-auto mb-1"></i>
                            Mis Publicaciones
                        </a>
                    </li>
                <%
                        } else if ("suscriptor".equalsIgnoreCase(tipoUsuario)){
                %>
                    <li class="text-center">
                        <a href="MagazineDatailsBST.jsp" class="nav-link text-white">
                            <i class="bi bi-journal-bookmark-fill d-block mx-auto mb-1"></i>
                            Ver Revistas
                        </a>
                    </li>
                <%
                        }
                %>
                    <li class="text-center">
                        <a href="Logout.jsp" class="nav-link text-white">
                            <i class="bi bi-box-arrow-right d-block mx-auto mb-1"></i>
                            Cerrar Sesión
                        </a>
                    </li>
                <%
                    } else {
                %>
                    <li class="text-center">
                        <a href="Login.jsp" class="nav-link text-white">
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

