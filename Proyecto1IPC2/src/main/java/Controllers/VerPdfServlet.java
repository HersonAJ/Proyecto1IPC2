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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author herson
 */
@WebServlet(name = "VerPdfServlet", urlPatterns = {"/VerPdfServlet"})
public class VerPdfServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idRevista = Integer.parseInt(request.getParameter("id"));
        try (Connection connection = ConexionDB.getConnection()) {
            String sql = "SELECT archivo_pdf FROM Revista WHERE id_revista = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idRevista);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        byte[] pdfData = resultSet.getBytes("archivo_pdf");
                        response.setContentType("application/pdf");
                        response.setContentLength(pdfData.length);
                        response.getOutputStream().write(pdfData);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

  

}
