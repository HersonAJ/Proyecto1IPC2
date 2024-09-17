/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author herson
 */
public class ConexionDB {
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/RevistaDigital";
    private static final String USER = "root";
    private static final String PASSWORD = "123";
    
    private static Connection connection;
    
    // Constructor privado para evitar instanciación
    private ConexionDB() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || isClosed(connection)) {
            synchronized (ConexionDB.class) {
                if (connection == null || isClosed(connection)) {
                    try {
                      
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        System.out.println("Intentando conectar a la base de datos...");
                        connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
                        System.out.println("Conectado a la base de datos.");
                        System.out.println("Esquema: " + connection.getCatalog());
                    } catch (ClassNotFoundException e) {
                        System.out.println("Error: No se encontró el controlador JDBC.");
                        e.printStackTrace();
                    } catch (SQLException e) {
                        System.out.println("Error al conectar a la DB");
                        e.printStackTrace();
                        throw e;
                    }
                }
            }
        }
        return connection;
    }

    private static boolean isClosed(Connection connection) {
        try {
            return connection == null || connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
}