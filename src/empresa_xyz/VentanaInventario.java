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
  public class VentanaInventario extends JFrame {
    private JTextArea areaInventario;
    private JButton btnActualizarInventario;

    public VentanaInventario() {
        setTitle("Inventario de Productos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        areaInventario = new JTextArea();
        areaInventario.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaInventario);
        add(scrollPane, BorderLayout.CENTER);

        btnActualizarInventario = new JButton("Actualizar Inventario");
        add(btnActualizarInventario, BorderLayout.SOUTH);

        // Agregar ActionListener al botón
        btnActualizarInventario.addActionListener(e -> actualizarInventario());

        // Agregar WindowListener para cerrar la conexión al cerrar la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Conexion.closeConnection(); // Cerrar la conexión a la base de datos
                dispose(); // Cerrar la ventana
            }
        });

        // Mostrar el inventario al abrir la ventana
        actualizarInventario();

        setVisible(true);
    }

    private void actualizarInventario() {
        String inventario = Procedimientos.obtenerInventario();
        areaInventario.setText(inventario); // Mostrar el inventario en el JTextArea
    }
}