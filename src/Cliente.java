import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String[] DESTINOS = {"1. Mendoza", "2. San Juan", "3. San Luis"};

    public static void main(String[] args) {
        try (
            Socket         socket = new Socket("localhost", 5000);
            PrintWriter    out    = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in     = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner        sc     = new Scanner(System.in)
        ) {
            System.out.println("=== Sistema de Venta de Pasajes ===");
            System.out.println("Conectado al servidor.\n");

            while (true) {
                System.out.print("Nombre del pasajero (o 'salir'): ");
                String nombre = sc.nextLine().trim();
                if (nombre.equalsIgnoreCase("salir")) break;

                System.out.print("Número de asiento: ");
                String asientoTexto = sc.nextLine().trim();

                System.out.println("Destino:");
                for (String d : DESTINOS) System.out.println("  " + d);
                System.out.print("Elegí (1-3): ");
                String opcion = sc.nextLine().trim();

                String destino;
                switch (opcion) {
                    case "1" -> destino = "Mendoza";
                    case "2" -> destino = "San Juan";
                    case "3" -> destino = "San Luis";
                    default  -> { System.out.println("Opción inválida.\n"); continue; }
                }

                int asiento;
                try {
                    asiento = Integer.parseInt(asientoTexto);
                } catch (NumberFormatException e) {
                    System.out.println("Asiento inválido.\n");
                    continue;
                }

                out.println("VENDER|" + nombre + "|" + asiento + "|" + destino);
                String respuesta = in.readLine();
                String[] partes  = respuesta.split("\\|", 2);

                if ("OK".equals(partes[0])) {
                    System.out.println("✔ " + partes[1] + "\n");
                } else {
                    System.out.println("✘ " + partes[1] + "\n");
                }
            }

            System.out.println("Conexión cerrada.");

        } catch (IOException e) {
            System.out.println("No se pudo conectar. ¿Corriste Servidor?");
        }
    }
}