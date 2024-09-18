/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Modelos.Revista;
import backendDB.UploadService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author herson
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // 10MB
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UploadService uploadService = new UploadService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean success = uploadService.uploadRevista(request, response);
        if (success) {
            //response.sendRedirect("/Revista/MisPublicacionesBST.jsp");
            response.sendRedirect(request.getContextPath() + "/Revista/MisPublicaciones.jsp");
        } else {
            // Mostrar modal de error
            request.getRequestDispatcher("errorModal.jsp").forward(request, response);
        }
    }
}



