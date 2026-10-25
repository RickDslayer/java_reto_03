package s08reto03;

import java.util.List;

// Cliente que solo conoce el contrato Ficha, no las subclases.
public class ImpresoraFichas {
    public static void imprimir(Ficha ficha) {
        System.out.println("  " + ficha.generarFicha());
    }

    public static void imprimirTodas(List<Entrega> entregas) {
        if (entregas.isEmpty()) {
            System.out.println("  (sin entregas)");
            return;
        }
        for (Entrega entrega : entregas) {
            imprimir(entrega);
        }
    }
}
