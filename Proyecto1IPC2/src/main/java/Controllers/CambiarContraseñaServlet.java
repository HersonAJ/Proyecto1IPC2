/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Modelos.Codificador;
import backendDB.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author herson
 */
@WebServlet(name = "CambiarContraseñaServlet", urlPatterns = {"/CambiarContraseñaServlet"})
public class CambiarContraseñaServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            String email = (String) session.getAttribute("usuario");
            String nuevaContraseña = request.getParameter("nuevaContraseña");
            String contraseñaCodificada = Codificador.codificar(nuevaContraseña);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean actualizado = usuarioDAO.cambiarContraseña(email, contraseñaCodificada);

            if (actualizado) {
                response.sendRedirect(request.getContextPath() + "/MiPerfilServlet");
            } else {
                response.getWriter().println("Error al cambiar la contraseña.");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
        }
    }
}
