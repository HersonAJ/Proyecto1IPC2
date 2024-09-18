/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Modelos.Usuario;
import backendDB.UsuarioService2;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Base64;
//
/**
 *
 * @author herson
 */
@WebServlet(name = "MiPerfilServlet", urlPatterns = {"/MiPerfilServlet"})
public class MiPerfilServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            String email = (String) session.getAttribute("usuario");
            
            // Mensaje para buscar el error 
            System.out.println("Usuario logueado: " + email);
            
            UsuarioService2 usuarioService = new UsuarioService2();
            Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email);
            
            if (usuario != null) {
                System.out.println("Datos del perfil obtenidos: ");
                System.out.println("Nombre: " + usuario.getNombre());
                System.out.println("Descripción: " + usuario.getDescripcion());
                System.out.println("Hobbies: " + usuario.getHobbies());
                System.out.println("Temas de Interés: " + usuario.getTemasInteres());
                System.out.println("Tipo de Usuario: " + usuario.getTipoUsuario());
                System.out.println("Saldo de Cartera: " + usuario.getSaldoCartera());
                
                request.setAttribute("nombre", usuario.getNombre());
                request.setAttribute("email", usuario.getEmail());
                request.setAttribute("descripcion", usuario.getDescripcion());
                request.setAttribute("hobbies", String.join(",", usuario.getHobbies()));
                request.setAttribute("temasInteres", String.join(",", usuario.getTemasInteres()));
                request.setAttribute("tipoUsuario", usuario.getTipoUsuario());
                request.setAttribute("saldoCartera", usuario.getSaldoCartera());
                request.setAttribute("fotoPerfilBase64", usuario.getFotoPerfil() != null ? Base64.getEncoder().encodeToString(usuario.getFotoPerfil()) : null);
            }
            
            request.getRequestDispatcher("/Perfil/MiPerfil.jsp").forward(request, response);
        } else {
            System.out.println("No hay sesión activa o el usuario no está logueado");
            response.sendRedirect("${pageContext.request.contextPath}/Login.jsp");
        }
    }
}







