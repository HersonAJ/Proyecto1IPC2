
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="backendDB.SuscripcionService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mis Suscripciones - Revista Digital</title>
    <jsp:include page="/resources/resources.jsp" />
</head>
<body>
    <jsp:include page="/resources/header.jsp" />
    
    <div class="container my-4">
        <h2>Mis Suscripciones</h2>
        <div class="row">
<%
    int idUsuario = (int) session.getAttribute("idUsuario");
    SuscripcionService suscripcionService = new SuscripcionService();
    ResultSet resultSet = null;

    try {
        resultSet = suscripcionService.getSuscripciones(idUsuario);

        while (resultSet.next()) {
%>
            <div class="col-md-6 mb-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h3 class="card-title"><%= resultSet.getString("titulo") %></h3>
                        <p class="card-text"><%= resultSet.getString("descripcion") %></p>
                        <a href="/Revista/VerRevista.jsp?id=<%= resultSet.getInt("id_revista") %>" class="btn btn-primary">Ver Detalles</a>
                    </div>
                </div>
            </div>
<%
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (resultSet != null) try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
%>
        </div>
    </div>
</body>
</html>