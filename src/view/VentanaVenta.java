package view;

import javax.swing.*;
import java.awt.*;

public class VentanaVenta extends JFrame {
    public JTextField txtPasajero = new JTextField(20);
    public JTextField txtAsiento = new JTextField(5);
    public JComboBox<String> comboDestino = new JComboBox<>(new String[]{"Mendoza", "San Juan", "San Luis"});
    public JButton btnVender = new JButton("Vender Pasaje");
    public JTextArea areaResultado = new JTextArea(10, 30);

    public VentanaVenta() {
        setTitle("Sistema de Ventas - Colectivos");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JLabel("Pasajero:"));
        add(txtPasajero);
        add(new JLabel("Asiento:"));
        add(txtAsiento);
        add(new JLabel("Destino:"));
        add(comboDestino);
        add(btnVender);
        add(new JScrollPane(areaResultado));

        pack();
        setLocationRelativeTo(null); // Centra la ventana
    }
}