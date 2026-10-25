package s08reto03;

public class Evidencia<T> {
    private final String descripcion;
    private final T contenido;

    public Evidencia(String descripcion, T contenido) {
        this.descripcion = descripcion;
        this.contenido = contenido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public T getContenido() {
        return contenido;
    }

    @Override
    public String toString() {
        return descripcion + ": " + contenido;
    }
}
