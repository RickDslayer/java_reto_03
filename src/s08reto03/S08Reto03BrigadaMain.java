package s08reto03;

import java.util.Arrays;

public class S08Reto03BrigadaMain {
    public static void main(String[] args) {
        escenarioBase();
        estadisticasDiarias();
        pruebasAdicionales();
    }

    private static void escenarioBase() {
        titulo("Escenario base");
        Brigada brigada = new Brigada();
        System.out.println("Registrar papel R-01 750 g: " + brigada.registrarPapel("R-01", 750));
        System.out.println("Registrar plastico R-02 450 g: " + brigada.registrarPlastico("R-02", 450));
        mostrarEstado("Antes de retirar", brigada);
        System.out.println("  Puntos papel: " + brigada.puntosPorMaterial("papel")
                + " | Puntos plastico: " + brigada.puntosPorMaterial("plastico"));

        System.out.println("Retirar R-01: " + brigada.retirar("R-01"));
        mostrarEstado("Despues de retirar R-01", brigada);
        System.out.println("Retirar R-01 otra vez: " + brigada.retirar("R-01"));
        mostrarEstado("Tras el rechazo", brigada);

        mostrarBusqueda(brigada, " r-02 ");
        mostrarBusqueda(brigada, "R-01");

        Evidencia<Integer> puntos = new Evidencia<>("Puntos vigentes", brigada.puntosTotales());
        Evidencia<String> conclusion = new Evidencia<>("Conclusion",
                "Al retirar R-01 los totales se recalculan con " + brigada.cantidad() + " entrega(s)");
        int puntosConsultados = puntos.getContenido();
        String textoConsultado = conclusion.getContenido();
        System.out.println(puntos);
        System.out.println(conclusion);
        System.out.println("Consulta Integer + 0: " + (puntosConsultados + 0)
                + " | Conclusion en mayusculas: " + textoConsultado.toUpperCase());
    }

    private static void estadisticasDiarias() {
        titulo("Estadisticas de cinco dias");
        RegistroDiario registro = new RegistroDiario();
        int[] datos = {1200, 800, 0, 2000, 1000};
        for (int i = 0; i < datos.length; i++) {
            registro.registrar(i + 1, datos[i]);
        }
        mostrarDias(registro);

        System.out.println("Corregir dia 6: " + registro.registrar(6, 500));
        System.out.println("Corregir dia 0: " + registro.registrar(0, 500));
        System.out.println("Dia 2 con 100001 g: " + registro.registrar(2, 100001));
        int consulta = registro.consultar(7);
        System.out.println("Consultar dia 7: " + (consulta == -1 ? "Posicion inexistente" : consulta + " g"));
        System.out.println("Arreglo sin cambios: " + Arrays.toString(registro.copiaDatos()));

        titulo("Empate de maximos");
        RegistroDiario empate = new RegistroDiario();
        empate.registrar(2, 3000);
        empate.registrar(5, 3000);
        mostrarDias(empate);
    }

    private static void pruebasAdicionales() {
        titulo("Limites de peso");
        Brigada limites = new Brigada();
        System.out.println("Papel 99 g: " + limites.registrarPapel("L-01", 99));
        System.out.println("Plastico 10001 g: " + limites.registrarPlastico("L-02", 10001));
        mostrarEstado("Tras los rechazos", limites);

        Brigada papelMinimo = new Brigada();
        System.out.println("Papel 100 g: " + papelMinimo.registrarPapel("M-01", 100));
        mostrarEstado("Papel minimo", papelMinimo);

        Brigada plasticoMinimo = new Brigada();
        System.out.println("Plastico 100 g: " + plasticoMinimo.registrarPlastico("M-02", 100));
        mostrarEstado("Plastico minimo", plasticoMinimo);

        Brigada maximo = new Brigada();
        System.out.println("Papel 10000 g: " + maximo.registrarPapel("X-01", 10000));
        mostrarEstado("Papel maximo", maximo);

        titulo("IDs");
        Brigada ids = new Brigada();
        System.out.println("Registrar 'a-10': " + ids.registrarPapel("a-10", 300));
        System.out.println("Registrar '  A-10 ' (duplicado normalizado): " + ids.registrarPlastico("  A-10 ", 300));
        System.out.println("Registrar ID en blanco: " + ids.registrarPapel("   ", 300));
        System.out.println("Registrar ID null: " + ids.registrarPapel(null, 300));
        System.out.println("Retirar Z-99 inexistente: " + ids.retirar("Z-99"));
        mostrarEstado("Estado conservado", ids);
        mostrarBusqueda(ids, "Z-99");

        titulo("Lista vacia");
        mostrarEstado("Brigada nueva", new Brigada());
    }

    private static void mostrarEstado(String momento, Brigada brigada) {
        System.out.println(momento + " -> entregas: " + brigada.cantidad()
                + " | peso: " + brigada.pesoTotal() + " g | puntos: " + brigada.puntosTotales());
        ImpresoraFichas.imprimirTodas(brigada.listar());
    }

    private static void mostrarBusqueda(Brigada brigada, String id) {
        Entrega encontrada = brigada.buscar(id);
        System.out.print("Buscar '" + id + "': ");
        if (encontrada == null) {
            System.out.println("No encontrado");
        } else {
            System.out.println(encontrada.generarFicha());
        }
    }

    private static void mostrarDias(RegistroDiario registro) {
        System.out.println("Datos: " + Arrays.toString(registro.copiaDatos()));
        System.out.println("Total: " + registro.total() + " g | Promedio: " + registro.promedio()
                + " g | Maximo: " + registro.maximo() + " g | Dia: " + registro.diaMaximo());
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("=== " + texto + " ===");
    }
}
