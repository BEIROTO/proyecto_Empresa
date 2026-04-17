import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HappinessCo {
    private static HashMap<String, Usuario> usuarios = new HashMap<>();
    private static HashMap<Integer, Evento> eventos = new HashMap<>();
    private static ArrayList<Favorito> favoritos = new ArrayList<>();

    private static int contadorEvento = 0;
    private static int contadorGaleria = 0;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");

            while (!teclado.hasNextInt()) {
                System.out.println("Debe introducir un número.");
                teclado.nextLine();
                System.out.print("Seleccione una opción: ");
            }

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    anadirUsuario(teclado);
                    break;
                case 2:
                    eliminarUsuario(teclado);
                    break;
                case 3:
                    anadirEvento(teclado);
                    break;
                case 4:
                    eliminarEvento(teclado);
                    break;
                case 5:
                    anadirGaleria(teclado);
                    break;
                case 6:
                    eliminarGaleria(teclado);
                    break;
                case 7:
                    anadirFavorito(teclado);
                    break;
                case 8:
                    eliminarFavorito(teclado);
                    break;
                case 9:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 9);

        teclado.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n====================");
        System.out.println("   HAPPINESS&CO");
        System.out.println("====================");
        System.out.println("1. Añadir usuario");
        System.out.println("2. Eliminar usuario");
        System.out.println("3. Añadir evento");
        System.out.println("4. Eliminar evento");
        System.out.println("5. Añadir galería");
        System.out.println("6. Eliminar galería");
        System.out.println("7. Añadir favorito");
        System.out.println("8. Eliminar favorito");
        System.out.println("9. Salir");
        System.out.println("====================");
    }

    private static void anadirUsuario(Scanner teclado) {
        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("Email: ");
        String email = teclado.nextLine();

        System.out.print("Password: ");
        String password = teclado.nextLine();

        if (usuarios.containsKey(email)) {
            System.out.println("El usuario ya existe");
        } else {
            Usuario usuario = new Usuario(nombre, email, password);
            usuarios.put(email, usuario);
            System.out.println("Usuario creado correctamente");
        }
    }

    private static void eliminarUsuario(Scanner teclado) {
        System.out.print("Correo del usuario a eliminar: ");
        String email = teclado.nextLine();

        if (usuarios.containsKey(email)) {
            usuarios.remove(email);
            favoritos.removeIf(f -> f.getCorreoUsuario().equals(email));
            System.out.println("Usuario eliminado correctamente");
        } else {
            System.out.println("El usuario no existe");
        }
    }

    private static void anadirEvento(Scanner teclado) {
        try {
            System.out.print("Fecha (yyyy-MM-dd): ");
            String fechaTexto = teclado.nextLine();
            LocalDate fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            System.out.print("Título: ");
            String titulo = teclado.nextLine();

            System.out.print("Ubicación: ");
            String ubicacion = teclado.nextLine();

            System.out.print("Descripción: ");
            String descripcion = teclado.nextLine();

            contadorEvento++;
            Evento evento = new Evento(contadorEvento, fecha, titulo, ubicacion, descripcion);
            eventos.put(contadorEvento, evento);

            System.out.println("Evento creado correctamente");
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto");
        }
    }

    private static void eliminarEvento(Scanner teclado) {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos");
            return;
        }

        System.out.println("\nListado de eventos:");
        for (Evento evento : eventos.values()) {
            System.out.println(evento);
        }

        System.out.print("ID del evento a eliminar: ");
        int idEvento = teclado.nextInt();
        teclado.nextLine();

        if (eventos.containsKey(idEvento)) {
            eventos.remove(idEvento);
            favoritos.removeIf(f -> f.getIdEvento() == idEvento);
            System.out.println("Evento eliminado correctamente");
        } else {
            System.out.println("El evento no existe");
        }
    }

    private static void anadirGaleria(Scanner teclado) {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos");
            return;
        }

        System.out.println("\nListado de eventos:");
        for (Evento evento : eventos.values()) {
            System.out.println(evento);
        }

        System.out.print("ID del evento al que añadir galería: ");
        int idEvento = teclado.nextInt();
        teclado.nextLine();

        if (!eventos.containsKey(idEvento)) {
            System.out.println("ID de evento incorrecto");
            return;
        }

        System.out.print("Título de la galería: ");
        String titulo = teclado.nextLine();

        contadorGaleria++;
        Galeria galeria = new Galeria(contadorGaleria, titulo, idEvento);
        eventos.get(idEvento).getGaleriaList().add(galeria);

        System.out.println("Galería creada correctamente");
    }

    private static void eliminarGaleria(Scanner teclado) {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos");
            return;
        }

        System.out.println("\nListado de eventos:");
        for (Evento evento : eventos.values()) {
            System.out.println(evento);
        }

        System.out.print("ID del evento del que quieres eliminar una galería: ");
        int idEvento = teclado.nextInt();
        teclado.nextLine();

        if (!eventos.containsKey(idEvento)) {
            System.out.println("ID de evento incorrecto");
            return;
        }

        ArrayList<Galeria> galerias = eventos.get(idEvento).getGaleriaList();

        if (galerias.isEmpty()) {
            System.out.println("No hay galerías en este evento");
            return;
        }

        System.out.println("\nListado de galerías del evento:");
        for (Galeria galeria : galerias) {
            System.out.println(galeria);
        }

        System.out.print("ID de la galería a eliminar: ");
        int idGaleria = teclado.nextInt();
        teclado.nextLine();

        boolean eliminada = galerias.removeIf(g -> g.getId() == idGaleria);

        if (eliminada) {
            System.out.println("Galería eliminada correctamente");
        } else {
            System.out.println("La galería no existe");
        }
    }

    private static void anadirFavorito(Scanner teclado) {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios");
            return;
        }

        if (eventos.isEmpty()) {
            System.out.println("No hay eventos");
            return;
        }

        System.out.println("\nListado de usuarios:");
        for (Usuario usuario : usuarios.values()) {
            System.out.println(usuario);
        }

        System.out.println("\nListado de eventos:");
        for (Evento evento : eventos.values()) {
            System.out.println(evento);
        }

        System.out.print("Correo del usuario: ");
        String correoUsuario = teclado.nextLine();

        if (!usuarios.containsKey(correoUsuario)) {
            System.out.println("Correo de usuario incorrecto");
            return;
        }

        System.out.print("ID del evento: ");
        int idEvento = teclado.nextInt();
        teclado.nextLine();

        if (!eventos.containsKey(idEvento)) {
            System.out.println("Evento incorrecto");
            return;
        }

        for (Favorito f : favoritos) {
            if (f.getCorreoUsuario().equals(correoUsuario) && f.getIdEvento() == idEvento) {
                System.out.println("El favorito ya existe");
                return;
            }
        }

        Favorito favorito = new Favorito(correoUsuario, idEvento);
        favoritos.add(favorito);
        System.out.println("Favorito creado correctamente");
    }

    private static void eliminarFavorito(Scanner teclado) {
        if (favoritos.isEmpty()) {
            System.out.println("No hay favoritos");
            return;
        }

        System.out.println("\nListado de favoritos:");
        for (Favorito favorito : favoritos) {
            System.out.println(favorito);
        }

        System.out.print("Correo del usuario: ");
        String correoUsuario = teclado.nextLine();

        System.out.print("ID del evento: ");
        int idEvento = teclado.nextInt();
        teclado.nextLine();

        boolean eliminado = favoritos.removeIf(f ->
            f.getCorreoUsuario().equals(correoUsuario) && f.getIdEvento() == idEvento
        );

        if (eliminado) {
            System.out.println("Favorito eliminado correctamente");
        } else {
            System.out.println("El favorito no existe");
        }
    }
}
