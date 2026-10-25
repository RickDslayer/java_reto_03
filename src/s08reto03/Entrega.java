package s08reto03;

import java.util.Locale;

public abstract class Entrega implements Ficha {
    public static final int GRAMOS_MINIMOS = 100;
    public static final int GRAMOS_MAXIMOS = 10000;
    public static final int GRAMOS_POR_BLOQUE = 100;

    private final String id;
    private final int gramos;

    // Los datos llegan ya validados por Brigada (ver esIdValido / esPesoValido).
    protected Entrega(String id, int gramos) {
        this.id = normalizarId(id);
        this.gramos = gramos;
    }

    public static String normalizarId(String id) {
        if (id == null) {
            return "";
        }
        return id.strip().toUpperCase(Locale.ROOT);
    }

    public static boolean esIdValido(String id) {
        return !normalizarId(id).isBlank();
    }

    public static boolean esPesoValido(int gramos) {
        return gramos >= GRAMOS_MINIMOS && gramos <= GRAMOS_MAXIMOS;
    }

    public String getId() {
        return id;
    }

    public int getGramos() {
        return gramos;
    }

    public int bloquesCompletos() {
        return gramos / GRAMOS_POR_BLOQUE; // División entera: el sobrante no cuenta.
    }

    public int puntos() {
        return bloquesCompletos() * puntosPorBloque();
    }

    public abstract String material();

    protected abstract int puntosPorBloque();

    @Override
    public String generarFicha() {
        return String.format("%-6s | %-8s | %5d g | %3d pts", id, material(), gramos, puntos());
    }
}
