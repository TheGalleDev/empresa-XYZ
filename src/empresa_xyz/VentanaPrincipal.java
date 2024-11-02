/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresa_xyz;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/** @author Camilo Gallego B*/
public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("Sistema de Ventas e Inventario");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnRegistrarVenta = new JButton("Registrar Venta");
        JButton btnVerInventario = new JButton("Ver Inventario");

        btnRegistrarVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaRegistroVenta();
            }
        });
        btnVerInventario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaInventario();
            }
        });
        JPanel panel = new JPanel();
        panel.add(btnRegistrarVenta);
        panel.add(btnVerInventario);

        add(panel);
        setVisible(true);
    }
}