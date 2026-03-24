import java.time.LocalDate;
import java.util.ArrayList;

public class Evento {
    private int id;
    private LocalDate fecha;
    private String titulo;
    private String ubicacion;
    private String descripcion;
    private ArrayList<Galeria> galeriaList;

    public Evento(int id, LocalDate fecha, String titulo, String ubicacion, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.galeriaList = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public ArrayList<Galeria> getGaleriaList() { return galeriaList; }
    public void setGaleriaList(ArrayList<Galeria> galeriaList) { this.galeriaList = galeriaList; }

    @Override
    public String toString() {
        return "Evento{id=" + id + ", fecha=" + fecha + ", titulo='" + titulo + "', ubicacion='" + ubicacion + "'}";
    }
}
