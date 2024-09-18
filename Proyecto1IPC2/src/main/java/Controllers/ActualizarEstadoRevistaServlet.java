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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author herson
 */
@WebServlet(name = "ActualizarEstadoRevistaServlet", urlPatterns = {"/ActualizarEstadoRevistaServlet"})
public class ActualizarEstadoRevistaServlet extends HttpServlet {




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

  
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idRevista = Integer.parseInt(request.getParameter("idRevista"));
        boolean permiteComentarios = request.getParameter("permiteComentarios") != null;
        boolean permiteMegusta = request.getParameter("permiteMegusta") != null;
        
        try(Connection connection = ConexionDB.getConnection()){
            String sql = "UPDATE Revista SET permite_comentarios = ?, permite_megusta = ? WHERE id_revista = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setBoolean(1, permiteComentarios);
                statement.setBoolean(2, permiteMegusta);
                statement.setInt(3, idRevista);
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        
        response.sendRedirect(request.getContextPath() + "/Revista/MisPublicaciones.jsp");
    }
}


