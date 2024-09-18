/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendDB;

import Modelos.Publicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author herson
 */
public class MisPublicacionesService {

    public List<Publicacion> obtenerPublicacionesPorAutor(String email) {
        List<Publicacion> publicaciones = new ArrayList<>();

        String sqlRevista = "SELECT id_revista, titulo, descripcion, categoria, fecha_creacion, permite_comentarios, permite_megusta FROM Revista WHERE id_autor = ?";
        String sqlUsuario = "SELECT id_usuario FROM Usuario WHERE email = ?";

        try (Connection connection = ConexionDB.getConnection()) {
            try (PreparedStatement statementUsuario = connection.prepareStatement(sqlUsuario)) {
                statementUsuario.setString(1, email);
                try (ResultSet resultSetUsuario = statementUsuario.executeQuery()) {
                    if (resultSetUsuario.next()) {
                        int idAutor = resultSetUsuario.getInt("id_usuario");
                        try (PreparedStatement statementRevista = connection.prepareStatement(sqlRevista)) {
                            statementRevista.setInt(1, idAutor);
                            try (ResultSet resultSetRevista = statementRevista.executeQuery()) {
                                while (resultSetRevista.next()) {
                                    Publicacion publicacion = new Publicacion(
                                        resultSetRevista.getInt("id_revista"),
                                        resultSetRevista.getString("titulo"),
                                        resultSetRevista.getString("descripcion"),
                                        resultSetRevista.getString("categoria"),
                                        resultSetRevista.getDate("fecha_creacion"),
                                        resultSetRevista.getBoolean("permite_comentarios"),
                                        resultSetRevista.getBoolean("permite_megusta")
                                    );
                                    publicaciones.add(publicacion);
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicaciones;
    }
}
