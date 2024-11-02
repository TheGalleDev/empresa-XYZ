/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresa_xyz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *
 * @author Camilo Gallego B
 */
public class VentanaRegistroVenta extends JFrame {
    private JTextField productoField, cantidadField, precioField;
    private JButton registrarButton;

    public VentanaRegistroVenta() {
        setTitle("Registrar Venta");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        // Crear componentes
        add(new JLabel("Producto:"));
        productoField = new JTextField();
        add(productoField);

        add(new JLabel("Cantidad:"));
        cantidadField = new JTextField();
        add(cantidadField);

        add(new JLabel("Precio Unitario:"));
        precioField = new JTextField();
        add(precioField);

        registrarButton = new JButton("Registrar Venta");
        add(registrarButton);

        // Agregar ActionListener al botón
        registrarButton.addActionListener(e -> registrarVenta());

        // Agregar WindowListener para cerrar la conexión al cerrar la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Conexion.closeConnection(); // Cerrar la conexión a la base de datos
                dispose(); // Cerrar la ventana
            }
        });

        setVisible(true);
    }

    private void registrarVenta() {
        try {
            String producto = productoField.getText();
            int cantidad = Integer.parseInt(cantidadField.getText());
            double precio = Double.parseDouble(precioField.getText());

            Procedimientos.registrarCompraYActualizarInventario(producto, cantidad, precio);
            JOptionPane.showMessageDialog(this, "Venta registrada exitosamente.");

            // Limpiar campos
            productoField.setText("");
            cantidadField.setText("");
            precioField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar la venta: " + ex.getMessage());
        }
    }
}