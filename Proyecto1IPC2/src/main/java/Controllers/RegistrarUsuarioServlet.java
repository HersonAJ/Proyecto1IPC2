/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Modelos.Codificador;
import Modelos.Usuario;
import backendDB.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.Arrays;

/**
 *
 * @author herson
 */
@WebServlet(name = "RegistrarUsuarioServlet", urlPatterns = {"/RegistrarUsuarioServlet"})
@MultipartConfig
public class RegistrarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String contraseña = request.getParameter("contraseña");
        Part fotoPerfilPart = request.getPart("fotoPerfil");
        String descripcion = request.getParameter("descripcion");

        String[] hobbiesArray = request.getParameterValues("hobbies");
        String hobbies = (hobbiesArray != null) ? String.join(",", hobbiesArray) : "";

        String[] temasInteresArray = request.getParameterValues("temasInteres");
        String temasInteres = (temasInteresArray != null) ? String.join(",", temasInteresArray) : "";

        String tipoUsuario = request.getParameter("tipoUsuario");
        String saldoCarteraStr = request.getParameter("saldoCartera");

        byte[] fotoPerfilBytes = null;
        if (fotoPerfilPart != null && fotoPerfilPart.getSize() > 0) {
            InputStream inputStream = fotoPerfilPart.getInputStream();
            fotoPerfilBytes = inputStream.readAllBytes();
        }

        double saldoCartera = 0.0;
        if (saldoCarteraStr != null && !saldoCarteraStr.trim().isEmpty()) {
            try {
                saldoCartera = Double.parseDouble(saldoCarteraStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.getWriter().println("Error: el saldo en la cartera debe ser un número válido");
                return;
            }
        }

        if (contraseña == null || contraseña.trim().isEmpty()) {
            response.getWriter().println("Error: la contraseña no puede estar vacía");
            return;
        }

        String contraseñaCodificada = Codificador.codificar(contraseña);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setContraseña(contraseñaCodificada);
        usuario.setDescripcion(descripcion);
        usuario.setHobbies(Arrays.asList(hobbies.split(",")));
        usuario.setTemasInteres(Arrays.asList(temasInteres.split(",")));
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setSaldoCartera(saldoCartera);

        UsuarioService usuarioService = new UsuarioService();
        boolean registroExitoso = usuarioService.registrarUsuario(usuario, fotoPerfilBytes);

        if (registroExitoso) {
            response.setContentType("text/html");
            response.getWriter().println("<script type='text/javascript'>");
            response.getWriter().println("alert('¡Registro exitoso!');");
            response.getWriter().println("window.location.href = 'index.jsp';");
            response.getWriter().println("</script>");
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<script type='text/javascript'>");
            response.getWriter().println("alert('El correo electrónico ya está registrado.');");
            response.getWriter().println("window.location.href = 'Registro.jsp';");
            response.getWriter().println("</script>");
        }
    }
}
