package model;

public class Pasaje {
    private String pasajero;
    private int asiento;
    private String destino;

    public Pasaje(String pasajero, int asiento, String destino) {
        this.pasajero = pasajero;
        this.asiento = asiento;
        this.destino = destino;
    }

    // Getters para que el Controller pueda leer los datos
    public String getPasajero() { return pasajero; }
    public int getAsiento() { return asiento; }
    public String getDestino() { return destino; }
}