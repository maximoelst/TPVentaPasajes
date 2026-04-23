import view.VentanaVenta;
import controller.VentaController;

public class Main {
    public static void main(String[] args) {
        // 1. Instanciamos la Vista
        VentanaVenta vista = new VentanaVenta();
        
        // 2. Instanciamos el Controlador y le pasamos la vista
        new VentaController(vista);
        
        // 3. Hacemos visible la ventana
        vista.setVisible(true);
    }
}