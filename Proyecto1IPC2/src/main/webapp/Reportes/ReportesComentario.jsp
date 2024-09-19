

<%@page import="backendDB.ReporteComentario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/resources/resources.jsp" />
    <meta charset="UTF-8">
    <title>Generar Reporte de Comentarios</title>

</head>
<body>
    <jsp:include page="/resources/header.jsp" />
    <div class="container mt-5">
        <h1 class="text-center">Generar Reporte de Comentarios</h1>
        <form action="${pageContext.request.contextPath}ReportesServlet" method="post" class="mt-4">
            <div class="form-group">
                <label for="fechaInicio">Fecha de Inicio:</label>
                <input type="date" id="fechaInicio" name="fechaInicio" class="form-control">
            </div>
            <div class="form-group">
                <label for="fechaFin">Fecha de Fin:</label>
                <input type="date" id="fechaFin" name="fechaFin" class="form-control">
            </div>
            <div class="form-group">
                <label for="tituloRevista">Título de la Revista:</label>
                <input type="text" id="tituloRevista" name="tituloRevista" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary btn-block">Generar Reporte</button>
            <button type="submit" name="exportarPDF" value="true" class="btn btn-secondary btn-block mt-2">Exportar a PDF</button>
        </form>

        <%
            List<ReporteComentario> comentarios = (List<ReporteComentario>) request.getAttribute("comentarios");
            if (comentarios != null && !comentarios.isEmpty()) {
        %>
            <h2 class="text-center mt-5">Comentarios Obtenidos</h2>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th>Revista</th>
                        <th>Usuario</th>
                        <th>Comentario</th>
                        <th>Fecha</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (ReporteComentario comentario : comentarios) {
                    %>
                        <tr>
                            <td><%= comentario.getRevista() %></td>
                            <td><%= comentario.getUsuario() %></td>
                            <td><%= comentario.getComentario() %></td>
                            <td><%= comentario.getFecha() %></td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        <%
            }
        %>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
