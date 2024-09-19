<%-- 
    Document   : Reportes
    Created on : 19 sept 2024, 8:12:57
    Author     : herson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/resources/resources.jsp" />
    <meta charset="UTF-8">
    <title>Generar Reportes</title>
</head>
<body>
    <jsp:include page="/resources/header.jsp" />
    <div class="container mt-5">
        <h1 class="text-center">Generar Reportes</h1>
        <div class="d-flex justify-content-center mt-4">
            <form action="${pageContext.request.contextPath}/Reportes/ReportesComentario.jsp" method="post" class="mr-2">
                <button type="submit" class="btn btn-primary">Reporte de Comentarios</button>
            </form>
            <form action="ReportesSuscripcion.jsp" method="post" class="mr-2">
                <button type="submit" class="btn btn-primary">Reporte de Suscripciones</button>
            </form>
            <form action="OtroReporteServlet" method="post">
                <button type="submit" class="btn btn-primary">Otro Reporte</button>
            </form>
        </div>
    </div>

</body>
</html>
