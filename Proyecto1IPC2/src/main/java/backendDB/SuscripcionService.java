/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author herson
 */
public class SuscripcionService {

    public ResultSet getSuscripciones(int idUsuario) throws SQLException {
        Connection connection = ConexionDB.getConnection();
        String sql = "SELECT r.id_revista, r.titulo, r.descripcion FROM Revista r " +
                     "JOIN Suscripcion s ON r.id_revista = s.id_revista " +
                     "WHERE s.id_usuario = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idUsuario);
        return statement.executeQuery();
    }
}
