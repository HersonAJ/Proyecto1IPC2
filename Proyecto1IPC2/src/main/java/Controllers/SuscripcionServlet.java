/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import backendDB.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author herson
 */
@WebServlet(name = "SuscripcionServlet", urlPatterns = {"/SuscripcionServlet"})
public class SuscripcionServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idUsuario = (int) session.getAttribute("idUsuario");
        int idRevista = Integer.parseInt(request.getParameter("id_revista"));

        try (Connection connection = ConexionDB.getConnection()) {
            // Verificar si ya existe una suscripción
            String checkSql = "SELECT COUNT(*) FROM Suscripcion WHERE id_usuario = ? AND id_revista = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                checkStatement.setInt(1, idUsuario);
                checkStatement.setInt(2, idRevista);
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        // Ya existe una suscripción
                        response.getWriter().println("<script type='text/javascript'>");
                        response.getWriter().println("alert('Ya estas suscrito a esta revista.');");
                        response.getWriter().println("window.location.href = '" + request.getContextPath() + "/Revista/VerRevista.jsp?id=" + idRevista + "';");

                        response.getWriter().println("</script>");
                        return;
                    }
                }
            }

            // Insertar nueva suscripción
            String insertSql = "INSERT INTO Suscripcion (id_usuario, id_revista, fecha_suscripcion) VALUES (?, ?, CURDATE())";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                insertStatement.setInt(1, idUsuario);
                insertStatement.setInt(2, idRevista);
                insertStatement.executeUpdate();
                
                // Mensaje de depuración
                System.out.println("Usuario " + idUsuario + " suscrito a la revista " + idRevista);
                
                response.sendRedirect(request.getContextPath() + "/Revista/VerRevista.jsp?id=" + idRevista);
                //response.sendRedirect("VerRevistaBST.jsp?id=" + idRevista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al suscribirse a la revista.");
        }
    }
}



