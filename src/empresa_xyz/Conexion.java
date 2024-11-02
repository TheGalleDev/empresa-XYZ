/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresa_xyz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
  @author Camilo Gallego B
 */
public class Conexion {
    private static Connection connection = null;

    // Método para obtener la conexión
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Reemplaza estos datos con los de tu base de datos
                String url = "jdbc:mysql://localhost:3306/empresa_xyz";
                String usuario = "root";
                String contraseña = "1873";

                connection = DriverManager.getConnection(url, usuario, contraseña);
                System.out.println("Conexión a la base de datos exitosa.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null; // Para permitir una nueva conexión si es necesario
            }
        }
    }
}