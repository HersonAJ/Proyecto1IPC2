/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Modelos.Usuario;
import backendDB.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;

/**
 *
 * @author herson
 */
@WebServlet(name = "EditarPerfilServlet", urlPatterns = {"/EditarPerfilServlet"})
public class EditarPerfilServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            String email = (String) session.getAttribute("usuario");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String hobbies = String.join(",", request.getParameterValues("hobbies"));
            String temasInteres = String.join(",", request.getParameterValues("temasInteres"));

            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setNombre(nombre);
            usuario.setDescripcion(descripcion);
            usuario.setHobbies(Arrays.asList(hobbies.split(",")));
            usuario.setTemasInteres(Arrays.asList(temasInteres.split(",")));

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean actualizado = usuarioDAO.actualizarUsuario(usuario);

            if (actualizado) {
                response.sendRedirect(request.getContextPath() + "/MiPerfilServlet");
            } else {
                response.getWriter().println("Error al actualizar el perfil.");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
        }
    }



}
