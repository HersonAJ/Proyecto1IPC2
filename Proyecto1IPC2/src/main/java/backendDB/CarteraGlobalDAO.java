/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author herson
 */
public class CarteraGlobalDAO {
    public static BigDecimal obtenerSaldo() {
        BigDecimal saldo = BigDecimal.ZERO;
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT saldo FROM CarteraGlobal WHERE id_cartera = 1")) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                saldo = resultSet.getBigDecimal("saldo");
            }
            System.out.println("Saldo obtenido en obtenerSaldo: " + saldo); // Mensaje de depuración
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saldo;
    }

    public static void recargarSaldo(BigDecimal monto, int idUsuario) {
        try (Connection connection = ConexionDB.getConnection()) {
            // Actualizar el saldo de la cartera global
            try (PreparedStatement statement = connection.prepareStatement("UPDATE CarteraGlobal SET saldo = saldo + ? WHERE id_cartera = 1")) {
                statement.setBigDecimal(1, monto);
                statement.executeUpdate();
            }

            // Insertar la transacción en TransaccionCarteraGlobal
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO TransaccionCarteraGlobal (id_cartera, monto, tipo_transaccion, id_usuario) VALUES (1, ?, 'Recarga', ?)")) {
                statement.setBigDecimal(1, monto);
                statement.setInt(2, idUsuario);
                statement.executeUpdate();
            }

            // Obtener y mostrar el saldo actualizado para depuración
            BigDecimal saldoActual = obtenerSaldo();
            System.out.println("Saldo después de recargar: " + saldoActual); // Mensaje de depuración
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}