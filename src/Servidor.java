import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Servidor implements Runnable {
    private static final int PUERTO = 5000;
    private static final ArrayList<Pasaje> pasajes = new ArrayList<>();

    private final Socket socket;

    public Servidor(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en puerto " + PUERTO + "...");
            while (true) {
                Socket cliente = servidor.accept();
                new Thread(new Servidor(cliente)).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try (
            socket;
            BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter    out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String linea;
            while ((linea = in.readLine()) != null) {
                String[] p = linea.split("\\|");
                if (p.length == 4 && p[0].equals("VENDER")) {
                    out.println(vender(p[1], Integer.parseInt(p[2]), p[3]));
                } else {
                    out.println("ERROR|Comando desconocido.");
                }
            }
        } catch (IOException e) {
            System.out.println("Cliente desconectado.");
        }
    }

    private static synchronized String vender(String nombre, int asiento, String destino) {
        for (Pasaje p : pasajes) {
            if (p.asiento == asiento)
                return "ERROR|El asiento " + asiento + " ya está ocupado.";
        }
        pasajes.add(new Pasaje(nombre, asiento, destino));
        return "OK|Vendido: " + nombre + " | Asiento: " + asiento + " | Destino: " + destino;
    }
}