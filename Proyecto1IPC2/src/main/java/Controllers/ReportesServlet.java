/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;


import backendDB.ConexionDB;
import backendDB.ReporteComentario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author herson
 */
@WebServlet(name = "ReportesServlet", urlPatterns = {"/ReportesServlet"})
public class ReportesServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }


    
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Obtener parámetros del formulario
    String fechaInicioStr = request.getParameter("fechaInicio");
    String fechaFinStr = request.getParameter("fechaFin");
    String tituloRevista = request.getParameter("tituloRevista");

    // Convertir fechas a Timestamp
    Timestamp fechaInicio = null;
    Timestamp fechaFin = null;
    try {
        if (fechaInicioStr != null && !fechaInicioStr.isEmpty()) {
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicioStr);
            fechaInicio = new Timestamp(parsedDate.getTime());
        }
        if (fechaFinStr != null && !fechaFinStr.isEmpty()) {
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinStr);
            fechaFin = new Timestamp(parsedDate.getTime());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Obtener el usuario de la sesión
    int idUsuario = (int) request.getSession().getAttribute("idUsuario");

    // Obtener la conexión a la base de datos usando ConexionDB
    Connection connection = null;
    try {
        connection = ConexionDB.getConnection();
    } catch (SQLException e) {
        throw new ServletException("Error al obtener la conexión a la base de datos", e);
    }

    // Obtener los comentarios del usuario
    List<ReporteComentario> comentarios = ReporteComentario.obtenerComentariosPorUsuario(connection, idUsuario, fechaInicio, fechaFin, tituloRevista);

    // Establecer los comentarios como atributo de la solicitud
    request.setAttribute("comentarios", comentarios);

    // Verificar si se solicitó la exportación a PDF
    String exportarPDF = request.getParameter("exportarPDF");
    if (exportarPDF != null && exportarPDF.equals("true")) {
        try {
            // Cargar el archivo .jasper
            InputStream inputStream = getServletContext().getResourceAsStream("/reportes/ReportePrueba.jasper");
            if (inputStream == null) {
                throw new ServletException("No se pudo encontrar el archivo ReportePrueba.jasper");
            }
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);

            // Crear el JRBeanCollectionDataSource con los comentarios
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(comentarios);

            // Preparar los parámetros
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("tituloRevista", tituloRevista);
            parameters.put("TipoReporte", "Comentarios");
            parameters.put("Duracion", "Del " + fechaInicioStr + " al " + fechaFinStr);
            // Cargar la imagen si es necesario
            InputStream imageStream = getServletContext().getResourceAsStream("/images/logo.png");
            parameters.put("Imagen", imageStream);
            // Pasar el dataSource como parámetro
            parameters.put("dataSource", dataSource);

            // Llenar el reporte con datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            // Exportar a PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=ComentariosReporte.pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } catch (JRException e) {
            throw new ServletException("Error al generar el reporte PDF", e);
        }
    } else {
        // Redirigir a la página JSP para mostrar los comentarios
        request.getRequestDispatcher("${pageContext.request.contextPath}/Reportes/ReportesComentario.jsp").forward(request, response);
    }
}
}




