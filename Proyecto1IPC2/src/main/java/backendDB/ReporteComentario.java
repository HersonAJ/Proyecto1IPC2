/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author herson
 */
public class ReporteComentario {
    private int id;
    private String revista;
    private String usuario;
    private String comentario;
    private Timestamp fecha;

    // Constructor
    public ReporteComentario(String revista, String usuario, String comentario, Timestamp fecha) {
        this.revista = revista;
        this.usuario = usuario;
        this.comentario = comentario;
        this.fecha = fecha;
    }

        public int getId() {
        return id;
    }
    
    // Getters and Setters
    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ReporteComentario{" +
                "revista='" + revista + '\'' +
                ", usuario='" + usuario + '\'' +
                ", comentario='" + comentario + '\'' +
                ", fecha=" + fecha +
                '}';
    }
public static List<ReporteComentario> obtenerComentariosPorUsuario(Connection connection, int idUsuario, Timestamp fechaInicio, Timestamp fechaFin, String tituloRevista) {
    List<ReporteComentario> comentarios = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT r.titulo AS Revista, u.nombre AS Usuario, c.contenido AS Comentario, c.fecha_creacion AS Fecha " +
                 "FROM Comentario c " +
                 "JOIN Usuario u ON c.id_usuario = u.id_usuario " +
                 "JOIN Revista r ON c.id_revista = r.id_revista " +
                 "WHERE r.id_autor = ?");

    // Si ambas fechas están presentes, aplicamos el filtro de rango de fechas
    if (fechaInicio != null && fechaFin != null) {
        sql.append(" AND c.fecha_creacion BETWEEN ? AND ?");
    }

    // Si se proporciona un título de revista, aplicamos el filtro de título de revista
    if (tituloRevista != null && !tituloRevista.isEmpty()) {
        sql.append(" AND r.titulo = ?");
    }

    try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
        int paramIndex = 1;
        statement.setInt(paramIndex++, idUsuario);

        // Establecer los parámetros del rango de fechas
        if (fechaInicio != null && fechaFin != null) {
            statement.setTimestamp(paramIndex++, fechaInicio);
            statement.setTimestamp(paramIndex++, fechaFin);
        }

        // Establecer el parámetro del título de la revista
        if (tituloRevista != null && !tituloRevista.isEmpty()) {
            statement.setString(paramIndex++, tituloRevista);
        }

        // Ejecutar la consulta y obtener los resultados
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String revista = resultSet.getString("Revista");
                String usuario = resultSet.getString("Usuario");
                String comentario = resultSet.getString("Comentario");
                Timestamp fecha = resultSet.getTimestamp("Fecha");

                ReporteComentario reporteComentario = new ReporteComentario(revista, usuario, comentario, fecha);
                comentarios.add(reporteComentario);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return comentarios;
}
}
