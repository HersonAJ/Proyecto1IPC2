/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import backendDB.Busqueda;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * @author herson
 */
@WebServlet(name = "BusquedaServlet", urlPatterns = {"/BusquedaServlet"})
public class BusquedaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 

     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        String tipoBusqueda = request.getParameter("tipoBusqueda"); // parametro para determinar tipo de busqueda
        Busqueda busqueda = new Busqueda();
        List<Map<String, Object>> resultados = null;

        if (query != null && !query.trim().isEmpty()) {
            if ("categoria".equalsIgnoreCase(tipoBusqueda)) {
                resultados = busqueda.buscarPorCategoria(query);
            } else if ("tags".equalsIgnoreCase(tipoBusqueda)) {
                String[] tags = query.split(",");
                resultados = busqueda.buscarPorTags(tags);
            }
        }

        request.setAttribute("resultados", resultados);
        request.getRequestDispatcher("/Revista/ResultadoBusqueda.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }


}
