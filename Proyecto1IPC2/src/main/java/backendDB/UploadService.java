/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendDB;

import Modelos.Revista;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author herson
 */
public class UploadService {
public boolean uploadRevista(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session = request.getSession(false);
    if (session != null && session.getAttribute("usuario") != null) {
        String email = (String) session.getAttribute("usuario");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String categoria = request.getParameter("categoria");
        String costoPorDiaStr = request.getParameter("costoPorDia");
        Part filePart = request.getPart("file");
        boolean permiteComentarios = request.getParameter("permiteComentarios") != null;
        boolean permiteMeGusta = request.getParameter("permiteMeGusta") != null;
        String tags = request.getParameter("tags");
        String fechaCreacionStr = request.getParameter("fechaCreacion");
        if (fechaCreacionStr == null) {
    request.setAttribute("errorMessage", "La fecha de creación es obligatoria.");
    return false;
}
    java.sql.Date fechaCreacion;
try {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date parsed = format.parse(fechaCreacionStr);
    fechaCreacion = new java.sql.Date(parsed.getTime());
} catch (ParseException e) {
    request.setAttribute("errorMessage", "La fecha de creación no es válida.");
    return false;
}    

        if (email == null || titulo == null || descripcion == null || categoria == null || costoPorDiaStr == null || filePart == null || fechaCreacionStr == null) {
            request.setAttribute("errorMessage", "Todos los campos son obligatorios.");
            return false;
        }

        double costoPorDia;
        try {
            costoPorDia = Double.parseDouble(costoPorDiaStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "El costo por día debe ser un número válido.");
            return false;
        }



        try (InputStream inputStream = filePart.getInputStream();
             Connection connection = ConexionDB.getConnection()) {

            // Obtener el id_usuario del editor
            String sqlUsuario = "SELECT id_usuario FROM Usuario WHERE email = ?";
            try (PreparedStatement statementUsuario = connection.prepareStatement(sqlUsuario)) {
                statementUsuario.setString(1, email);
                try (ResultSet resultSet = statementUsuario.executeQuery()) {
                    if (resultSet.next()) {
                        int idAutor = resultSet.getInt("id_usuario");

                        // Crear objeto Revista
                        Revista revista = new Revista();
                        revista.setTitulo(titulo);
                        revista.setDescripcion(descripcion);
                        revista.setFechaCreacion(fechaCreacion);
                        revista.setCostoPorDia(costoPorDia);
                        revista.setArchivoPdf(inputStream.readAllBytes());
                        revista.setPermiteComentarios(permiteComentarios);
                        revista.setPermiteMeGusta(permiteMeGusta);

                        // Insertar la revista en la base de datos
                        String sqlRevista = "INSERT INTO Revista (titulo, descripcion, categoria, fecha_creacion, id_autor, costo_por_dia, estado, archivo_pdf, permite_comentarios, permite_megusta) VALUES (?, ?, ?, ?, ?, ?, 'Activo', ?, ?, ?)";
                        try (PreparedStatement statementRevista = connection.prepareStatement(sqlRevista, Statement.RETURN_GENERATED_KEYS)) {
                            statementRevista.setString(1, revista.getTitulo());
                            statementRevista.setString(2, revista.getDescripcion());
                            statementRevista.setString(3, categoria);
                            statementRevista.setDate(4, fechaCreacion);
                            statementRevista.setInt(5, idAutor);
                            statementRevista.setDouble(6, revista.getCostoPorDia());
                            statementRevista.setBlob(7, new ByteArrayInputStream(revista.getArchivoPdf()));
                            statementRevista.setBoolean(8, revista.isPermiteComentarios());
                            statementRevista.setBoolean(9, revista.isPermiteMeGusta());
                                int rowsInserted = statementRevista.executeUpdate();

                                if (rowsInserted > 0) {
                                    try (ResultSet generatedKeys = statementRevista.getGeneratedKeys()) {
                                        if (generatedKeys.next()) {
                                            int idRevista = generatedKeys.getInt(1);

                                            // Insertar los tags en la base de datos
                                            if (tags != null && !tags.isEmpty()) {
                                                String[] tagArray = tags.split(",");
                                                for (String tag : tagArray) {
                                                    tag = tag.trim();
                                                    int idTag = 0;

                                                    // Verificar si el tag ya existe
                                                    String sqlTag = "SELECT id_tag FROM Tag WHERE nombre = ?";
                                                    try (PreparedStatement statementTag = connection.prepareStatement(sqlTag)) {
                                                        statementTag.setString(1, tag);
                                                        try (ResultSet resultSetTag = statementTag.executeQuery()) {
                                                            if (resultSetTag.next()) {
                                                                idTag = resultSetTag.getInt("id_tag");
                                                            } else {
                                                                // Insertar el nuevo tag
                                                                String sqlInsertTag = "INSERT INTO Tag (nombre) VALUES (?)";
                                                                try (PreparedStatement statementInsertTag = connection.prepareStatement(sqlInsertTag, Statement.RETURN_GENERATED_KEYS)) {
                                                                    statementInsertTag.setString(1, tag);
                                                                    statementInsertTag.executeUpdate();
                                                                    try (ResultSet generatedTagKeys = statementInsertTag.getGeneratedKeys()) {
                                                                        if (generatedTagKeys.next()) {
                                                                            idTag = generatedTagKeys.getInt(1);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }

                                                    // Insertar la relación revista-tag
                                                    String sqlRevistaTag = "INSERT INTO RevistaTag (id_revista, id_tag) VALUES (?, ?)";
                                                    try (PreparedStatement statementRevistaTag = connection.prepareStatement(sqlRevistaTag)) {
                                                        statementRevistaTag.setInt(1, idRevista);
                                                        statementRevistaTag.setInt(2, idTag);
                                                        statementRevistaTag.executeUpdate();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    return true;
                                }
                            }
                        }
                    }
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error al cargar la revista: " + e.getMessage());
                return false;
            }
        }
        return false;
    }
}
