public class Galeria {
    private int id;
    private String titulo;
    private int idEvento;

    public Galeria(int id, String titulo, int idEvento) {
        this.id = id;
        this.titulo = titulo;
        this.idEvento = idEvento;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getIdEvento() { return idEvento; }
    public void setIdEvento(int idEvento) { this.idEvento = idEvento; }

    @Override
    public String toString() {
        return "Galeria{id=" + id + ", titulo='" + titulo + "', idEvento=" + idEvento + "}";
    }
}
