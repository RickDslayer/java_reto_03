package s08reto03;

public class EntregaPlastico extends Entrega {
    public EntregaPlastico(String id, int gramos) {
        super(id, gramos);
    }

    @Override
    public String material() {
        return "Plastico";
    }

    @Override
    protected int puntosPorBloque() {
        return 2;
    }
}
