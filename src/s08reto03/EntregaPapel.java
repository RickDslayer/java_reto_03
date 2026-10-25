package s08reto03;

public class EntregaPapel extends Entrega {
    public EntregaPapel(String id, int gramos) {
        super(id, gramos);
    }

    @Override
    public String material() {
        return "Papel";
    }

    @Override
    protected int puntosPorBloque() {
        return 1;
    }
}
