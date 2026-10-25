package s08reto03;

public class RegistroDiario {
    public static final int DIAS = 5;
    public static final int GRAMOS_MAXIMOS_DIA = 100000;

    // Arreglo: exactamente cinco datos fijos; el índice 0..4 representa el día 1..5.
    private final int[] gramosPorDia = new int[DIAS];

    public static boolean esDiaValido(int dia) {
        return dia >= 1 && dia <= DIAS;
    }

    public static boolean esCantidadValida(int gramos) {
        return gramos >= 0 && gramos <= GRAMOS_MAXIMOS_DIA;
    }

    // Sirve tanto para registrar como para corregir un día.
    public boolean registrar(int dia, int gramos) {
        if (!esDiaValido(dia) || !esCantidadValida(gramos)) {
            return false;
        }
        gramosPorDia[dia - 1] = gramos;
        return true;
    }

    // Devuelve -1 si el día no existe; el cliente debe comprobarlo.
    public int consultar(int dia) {
        if (!esDiaValido(dia)) {
            return -1;
        }
        return gramosPorDia[dia - 1];
    }

    public int total() {
        int total = 0;
        for (int gramos : gramosPorDia) {
            total += gramos;
        }
        return total;
    }

    public double promedio() {
        return (double) total() / DIAS;
    }

    public int maximo() {
        return gramosPorDia[diaMaximo() - 1];
    }

    // Con ">" estricto, ante un empate se conserva el primer día.
    public int diaMaximo() {
        int indiceMaximo = 0;
        for (int i = 1; i < gramosPorDia.length; i++) {
            if (gramosPorDia[i] > gramosPorDia[indiceMaximo]) {
                indiceMaximo = i;
            }
        }
        return indiceMaximo + 1;
    }

    public int[] copiaDatos() {
        return gramosPorDia.clone();
    }
}
