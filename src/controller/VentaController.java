package controller;

import model.Pasaje;
import view.VentanaVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; // Para la lista
import javax.swing.JOptionPane;

public class VentaController {
    private VentanaVenta vista;
    private ArrayList<Pasaje> listaPasajes; // Nuestra "base de datos" temporal

    public VentaController(VentanaVenta vista) {
        this.vista = vista;
        this.listaPasajes = new ArrayList<>();
        
        this.vista.btnVender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarVenta();
            }
        });
    }

    private void ejecutarVenta() {
        try {
            String nombre = vista.txtPasajero.getText();
            int asiento = Integer.parseInt(vista.txtAsiento.getText());
            String destino = (String) vista.comboDestino.getSelectedItem();

            // --- VALIDACIÓN DE ASIENTO ---
            for (Pasaje p : listaPasajes) {
                if (p.getAsiento() == asiento) {
                    JOptionPane.showMessageDialog(vista, "¡Error! El asiento " + asiento + " ya está ocupado.");
                    return; // Corta acá y no vende nada
                }
            }

            // Si llegamos acá, el asiento está libre
            Pasaje nuevoPasaje = new Pasaje(nombre, asiento, destino);
            listaPasajes.add(nuevoPasaje); // Lo guardamos

            vista.areaResultado.append("✅ Vendido: " + nombre + " | Asiento: " + asiento + " | Destino: " + destino + "\n");
            
            // Limpiamos los campos para la próxima venta
            vista.txtPasajero.setText("");
            vista.txtAsiento.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Poné un número de asiento válido, fiera.");
        }
    }
}