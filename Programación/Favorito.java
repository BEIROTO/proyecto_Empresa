public class Favorito {
    private String correoUsuario;
    private int idEvento;

    public Favorito(String correoUsuario, int idEvento) {
        this.correoUsuario = correoUsuario;
        this.idEvento = idEvento;
    }

    // Getters y Setters
    public String getCorreoUsuario() { return correoUsuario; }
    public void setCorreoUsuario(String correoUsuario) { this.correoUsuario = correoUsuario; }
    public int getIdEvento() { return idEvento; }
    public void setIdEvento(int idEvento) { this.idEvento = idEvento; }

    @Override
    public String toString() {
        return "Favorito{correoUsuario='" + correoUsuario + "', idEvento=" + idEvento + "}";
    }
}
