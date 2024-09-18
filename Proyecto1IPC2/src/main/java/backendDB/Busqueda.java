/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author herson
 */
public class Busqueda {

    public List<Map<String, Object>> buscarPorCategoria(String categoria) {
        List<Map<String, Object>> resultados = new ArrayList<>();
        String sql = "SELECT * FROM Revista WHERE categoria = ?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categoria);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Map<String, Object> revista = new HashMap<>();
                    revista.put("id_revista", resultSet.getInt("id_revista"));
                    revista.put("titulo", resultSet.getString("titulo"));
                    revista.put("descripcion", resultSet.getString("descripcion"));
                    revista.put("categoria", resultSet.getString("categoria"));
                    revista.put("fecha_creacion", resultSet.getDate("fecha_creacion"));
                    revista.put("costo_por_dia", resultSet.getDouble("costo_por_dia"));
                    revista.put("permite_comentarios", resultSet.getBoolean("permite_comentarios"));
                    revista.put("permite_megusta", resultSet.getBoolean("permite_megusta"));
                    resultados.add(revista);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultados;
    }

    public List<Map<String, Object>> buscarPorTags(String[] tags) {
        List<Map<String, Object>> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT DISTINCT r.* FROM Revista r JOIN RevistaTag rt ON r.id_revista = rt.id_revista JOIN Tag t ON rt.id_tag = t.id_tag WHERE t.nombre IN (");
        for (int i = 0; i < tags.length; i++) {
            sql.append("?");
            if (i < tags.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < tags.length; i++) {
                statement.setString(i + 1, tags[i]);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Map<String, Object> revista = new HashMap<>();
                    revista.put("id_revista", resultSet.getInt("id_revista"));
                    revista.put("titulo", resultSet.getString("titulo"));
                    revista.put("descripcion", resultSet.getString("descripcion"));
                    revista.put("categoria", resultSet.getString("categoria"));
                    revista.put("fecha_creacion", resultSet.getDate("fecha_creacion"));
                    revista.put("costo_por_dia", resultSet.getDouble("costo_por_dia"));
                    revista.put("permite_comentarios", resultSet.getBoolean("permite_comentarios"));
                    revista.put("permite_megusta", resultSet.getBoolean("permite_megusta"));
                    resultados.add(revista);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultados;
    }
}
