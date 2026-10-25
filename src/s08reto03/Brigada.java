package s08reto03;

import java.util.ArrayList;
import java.util.List;

public class Brigada {
    // ArrayList: cantidad variable, se recorre en orden de registro y se consulta por índice.
    private final List<Entrega> entregas = new ArrayList<>();

    public boolean registrarPapel(String id, int gramos) {
        if (!puedeRegistrar(id, gramos)) {
            return false;
        }
        entregas.add(new EntregaPapel(id, gramos));
        return true;
    }

    public boolean registrarPlastico(String id, int gramos) {
        if (!puedeRegistrar(id, gramos)) {
            return false;
        }
        entregas.add(new EntregaPlastico(id, gramos));
        return true;
    }

    // Se valida todo antes de crear o agregar cualquier objeto.
    private boolean puedeRegistrar(String id, int gramos) {
        return Entrega.esIdValido(id) && Entrega.esPesoValido(gramos) && posicionDe(id) == -1;
    }

    private int posicionDe(String id) {
        String buscado = Entrega.normalizarId(id);
        for (int i = 0; i < entregas.size(); i++) {
            if (entregas.get(i).getId().equals(buscado)) {
                return i;
            }
        }
        return -1;
    }

    public Entrega buscar(String id) {
        int posicion = posicionDe(id);
        if (posicion == -1) {
            return null; // El cliente debe comprobarlo antes de usarlo.
        }
        return entregas.get(posicion);
    }

    public boolean retirar(String id) {
        int posicion = posicionDe(id);
        if (posicion == -1) {
            return false;
        }
        entregas.remove(posicion);
        return true;
    }

    // Los totales se calculan siempre sobre la lista actual, sin acumuladores históricos.
    public int cantidad() {
        return entregas.size();
    }

    public int pesoTotal() {
        int total = 0;
        for (Entrega entrega : entregas) {
            total += entrega.getGramos();
        }
        return total;
    }

    public int puntosTotales() {
        int total = 0;
        for (Entrega entrega : entregas) {
            total += entrega.puntos();
        }
        return total;
    }

    public int puntosPorMaterial(String material) {
        int total = 0;
        for (Entrega entrega : entregas) {
            if (entrega.material().equalsIgnoreCase(material.strip())) {
                total += entrega.puntos();
            }
        }
        return total;
    }

    // Copia no modificable: quien la recibe no puede alterar la lista interna.
    public List<Entrega> listar() {
        return List.copyOf(entregas);
    }
}
