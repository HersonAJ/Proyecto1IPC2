/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendDB;

import Modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;

/**
 *
 * @author herson
 */
public class UsuarioService2 {
    
        public Usuario obtenerUsuarioPorEmail(String email) {
            Usuario usuario = null;
            try(Connection connection = ConexionDB.getConnection()){
                String sql = "SELECT * FROM Usuario WHERE email = ?";
                try(PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, email);
                    try(ResultSet resultSet = statement.executeQuery()){
                        if(resultSet.next()) {
                        usuario = new Usuario();
                        usuario.setNombre(resultSet.getString("nombre"));
                        usuario.setEmail(resultSet.getString("email"));
                        usuario.setContraseña(resultSet.getString("contraseña"));
                        usuario.setDescripcion(resultSet.getString("descripcion"));
                        usuario.setHobbies(Arrays.asList(resultSet.getString("hobbies").split(",")));
                        usuario.setTemasInteres(Arrays.asList(resultSet.getString("temas_interes").split(",")));
                        usuario.setTipoUsuario(resultSet.getString("tipo_usuario"));
                        usuario.setFotoPerfil(resultSet.getBytes("foto_perfil"));
                        
                        
                        
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return usuario;
    }
    
}
