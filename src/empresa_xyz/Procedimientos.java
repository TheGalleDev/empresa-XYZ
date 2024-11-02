/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresa_xyz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Camilo Gallego B
 */
public class Procedimientos {

    // Método para registrar una compra y actualizar el inventario
    public static void registrarCompraYActualizarInventario(String producto, int cantidad, double precio) {
        String sqlInsertVenta = "INSERT INTO ventas (fecha_hora, producto, cantidad, precio_uni, precio_total) VALUES (?, ?, ?, ?, ?)";
        String sqlUpdateInventario = "UPDATE inventario SET unidades_disponibles = unidades_disponibles - ? WHERE nombre = ?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement pstmtInsertVenta = conn.prepareStatement(sqlInsertVenta); PreparedStatement pstmtUpdateInventario = conn.prepareStatement(sqlUpdateInventario)) {

            // Desactivar auto-commit para manejar transacciones
            conn.setAutoCommit(false);

            // Preparar y ejecutar la inserción en la tabla venta
            java.sql.Timestamp fechaHora = new java.sql.Timestamp(System.currentTimeMillis());
            pstmtInsertVenta.setTimestamp(1, fechaHora);
            pstmtInsertVenta.setString(2, producto);
            pstmtInsertVenta.setInt(3, cantidad);
            pstmtInsertVenta.setDouble(4, precio);
            pstmtInsertVenta.setDouble(5, cantidad * precio);

            int filasInsertadas = pstmtInsertVenta.executeUpdate();
            if (filasInsertadas == 0) {
                throw new SQLException("No se pudo registrar la venta.");
            }

            // Preparar y ejecutar la actualización del inventario
            pstmtUpdateInventario.setInt(1, cantidad);
            pstmtUpdateInventario.setString(2, producto);

            int filasActualizadas = pstmtUpdateInventario.executeUpdate();
            if (filasActualizadas == 0) {
                throw new SQLException("No se pudo actualizar el inventario.");
            }

            // Confirmar transacción
            conn.commit();
            System.out.println("Transacción completada exitosamente.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para obtener el inventario
    public static String obtenerInventario() {
        StringBuilder inventario = new StringBuilder();
        String query = "SELECT nombre, descripcion, precio_ven, unidades_disponibles FROM inventario";

        try (Connection connection = Conexion.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            // Procesar los resultados
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double precioVen = resultSet.getDouble("precio_ven");
                int unidadesDisponibles = resultSet.getInt("unidades_disponibles");

                inventario.append("Nombre: ").append(nombre)
                        .append(", Descripción: ").append(descripcion)
                        .append(", Precio de Venta: ").append(precioVen)
                        .append(", Unidades Disponibles: ").append(unidadesDisponibles)
                        .append("\n");
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
            return "Error al obtener el inventario: " + e.getMessage();
        }

        return inventario.toString();
    }
}
