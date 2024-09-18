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

/**
 *
 * @author herson
 */
public class UsuarioService {

    public boolean registrarUsuario(Usuario usuario, byte[] fotoPerfilBytes) {
        try (Connection connection = ConexionDB.getConnection()) {
            String emailCheckSql = "SELECT COUNT(*) FROM Usuario WHERE email = ?";
            try (PreparedStatement emailCheckStmt = connection.prepareStatement(emailCheckSql)) {
                emailCheckStmt.setString(1, usuario.getEmail());
                ResultSet resultSet = emailCheckStmt.executeQuery();
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    return false; // El correo electrónico ya está registrado
                }
            }

            // Insertar el usuario en la base de datos
            String sql = "INSERT INTO Usuario (nombre, email, contraseña, foto_perfil, descripcion, hobbies, temas_interes, tipo_usuario, saldo_cartera) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, usuario.getNombre());
                statement.setString(2, usuario.getEmail());
                statement.setString(3, usuario.getContraseña());
                if(fotoPerfilBytes != null){
                    statement.setBytes(4, fotoPerfilBytes);
                }else {
                    statement.setNull(4, java.sql.Types.BLOB);
                }
                statement.setString(5, usuario.getDescripcion());
                statement.setString(6, String.join(",", usuario.getHobbies()));
                statement.setString(7, String.join(",", usuario.getTemasInteres()));
                statement.setString(8, usuario.getTipoUsuario());
                statement.setDouble(9, usuario.getSaldoCartera());

                int rowsInserted = statement.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("algo salio mal");
            return false;
        }
    }
}