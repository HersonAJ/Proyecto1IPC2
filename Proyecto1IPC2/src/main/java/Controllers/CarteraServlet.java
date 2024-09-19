/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;


import backendDB.CarteraGlobalDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 *
 * @author herson
 */

@WebServlet(name = "CarteraServlet", urlPatterns = {"/CarteraServlet"})
public class CarteraServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el saldo actual de la cartera global
        BigDecimal saldoActual = CarteraGlobalDAO.obtenerSaldo();
        System.out.println("Saldo actual en doGet: " + saldoActual); // Mensaje de depuración
        request.setAttribute("saldoActual", saldoActual);
        request.getRequestDispatcher("/Admin/Cartera.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        int idUsuario = (int) session.getAttribute("idUsuario");

        if ("administrador".equalsIgnoreCase(tipoUsuario)) {
            String montoStr = request.getParameter("monto");
            BigDecimal monto = new BigDecimal(montoStr);

            // Recargar el saldo de la cartera global
            CarteraGlobalDAO.recargarSaldo(monto, idUsuario);

            request.setAttribute("mensaje", "Recarga exitosa de " + monto + " a la cartera global.");
        } else {
            request.setAttribute("mensaje", "No tienes permisos para recargar la cartera.");
        }

        doGet(request, response);
    }
}

