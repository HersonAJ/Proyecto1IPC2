/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendDB;


import Modelos.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author herson
 */
public class RevistaService1 {

    public List<Revista> obtenerRevistasDestacadas() {
        List<Revista> revistas = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConexionDB.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Revista");

            while (resultSet.next()) {
                Revista revista = new Revista();
                revista.setTitulo(resultSet.getString("titulo"));
                revista.setDescripcion(resultSet.getString("descripcion"));
                revistas.add(revista);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
            if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
            if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return revistas;
    }
    
//metodos para VerRevista
    
    public boolean isUsuarioSuscrito(int idUsuario, int idRevista) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isSuscrito = false;

        try {
            connection = ConexionDB.getConnection();
            String suscripcionQuery = "SELECT COUNT(*) FROM Suscripcion WHERE id_usuario = ? AND id_revista = ?";
            preparedStatement = connection.prepareStatement(suscripcionQuery);
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setInt(2, idRevista);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                isSuscrito = true;
            }
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (connection != null) try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return isSuscrito;
    }

    public ResultSet getRevistaDetalles(int idRevista) throws SQLException {
        Connection connection = ConexionDB.getConnection();
        String revistaQuery = "SELECT r.titulo, r.descripcion, u.nombre AS autor, r.categoria, " +
                              "(SELECT COUNT(*) FROM MeGusta WHERE id_revista = r.id_revista) AS total_megusta, " +
                              "r.permite_comentarios " +
                              "FROM Revista r " +
                              "JOIN Usuario u ON r.id_autor = u.id_usuario " +
                              "WHERE r.id_revista = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(revistaQuery);
        preparedStatement.setInt(1, idRevista);
        return preparedStatement.executeQuery();
    }

    public ResultSet getComentarios(int idRevista) throws SQLException {
        Connection connection = ConexionDB.getConnection();
        String comentariosQuery = "SELECT c.contenido, u.nombre, c.fecha_creacion FROM Comentario c " +
                                  "JOIN Usuario u ON c.id_usuario = u.id_usuario " +
                                  "WHERE c.id_revista = ? ORDER BY c.fecha_creacion DESC";
        PreparedStatement preparedStatement = connection.prepareStatement(comentariosQuery);
        preparedStatement.setInt(1, idRevista);
        return preparedStatement.executeQuery();
    }
}
    

