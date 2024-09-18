/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Modelos.Codificador;
import Modelos.Usuario;
import backendDB.LoginService;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {





    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String contraseñaCodificada = Codificador.codificar(password);
        LoginService loginService = new LoginService();
        Usuario usuario = loginService.autenticarUsuario(username, contraseñaCodificada);
        
        if(usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario.getEmail());
            session.setAttribute("tipoUsuario", usuario.getTipoUsuario());
            session.setAttribute("idUsuario", usuario.getIdUsuario());
            
            System.out.println("Usuario Logueado: " + usuario.getEmail());
            System.out.println("Tipo de Usuario: " + usuario.getTipoUsuario());
            System.out.println("Id Usuario: " + usuario.getIdUsuario());
            
            response.sendRedirect("index.jsp");
        
        } else {
                       // Usuario no encontrado, mostrar mensaje de error
            response.setContentType("text/html");
            response.getWriter().println("<script type='text/javascript'>");
            response.getWriter().println("alert('Usuario o contraseña incorrectos.');");
            response.getWriter().println("window.location.href = 'cuenta/Login.jsp';"); //cambien el login por loginBST para el boostrap
            response.getWriter().println("</script>"); 
        }
 
    }

}
