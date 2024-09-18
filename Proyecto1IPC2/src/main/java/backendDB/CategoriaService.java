/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;

/**
 *
 * @author herson
 */
public class CategoriaService {
    
    public HashSet<String> obtenerCategorias(){
        HashSet<String> categorias = new HashSet<>();
        try(Connection connection = ConexionDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT categoria FROM Revista")){
            
            while (resultSet.next()){
                categorias.add(resultSet.getString("categoria"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorias;
    }
    
    public ResultSet obtenerRevistasPorCategoria(String categoria){
        ResultSet rsRevistas = null;
        
        try{
        Connection connection = ConexionDB.getConnection();
        String sql = "SELECT * FROM Revista WHERE categoria = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, categoria);
        rsRevistas = preparedStatement.executeQuery();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return rsRevistas;
}
}
