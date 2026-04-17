package src;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HappinessCo {
    private static HashMap<String, Usuario> usuarios = new HashMap<>();
    private static HashMap<Integer, Evento> eventos = new HashMap<>();
    private static HashMap<Integer, Integer> contadorGaleriaPorEvento = new HashMap<>();
    private static int contadorEvento = 0;
    private static List<Favorito> favoritos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
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
                    anadirFavorito(teclado);
                    break;
                case 7:
                    eliminarFavorito(teclado);
                    break;
                case 8:
                    mostrarFavoritos();
                    break;
                case 0:
                    System.out.println("¡Gracias por usar HAPPINESS&CO!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 0);

        teclado.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n" + "=".repeat(20));
        System.out.println("   HAPPINESS&CO");
        System.out.println("=".repeat(20));
        System.out.println("1. Añadir usuario");
        System.out.println("2. Eliminar usuario");
        System.out.println("3. Añadir evento");
        System.out.println("4. Eliminar evento");
        System.out.println("5. Añadir galería");
        System.out.println("6. Añadir favorito");
        System.out.println("7. Eliminar favorito");
        System.out.println("8. Mostrar favoritos");
        System.out.println("0. Salir");
        System.out.println("=".repeat(20));
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
        System.out.print("Email del usuario a eliminar: ");
        String email = teclado.nextLine();

        if (usuarios.remove(email) != null) {
            System.out.println("Usuario eliminado correctamente");
        } else {
            System.out.println("El usuario no existe");
        }
    }

    private static void anadirEvento(Scanner teclado) {
        System.out.print("Fecha (yyyy-MM-dd): ");
        LocalDate fecha = null;

        try {
            fecha = LocalDate.parse(teclado.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido (use yyyy-MM-dd)");
            return;
        }

        System.out.print("Título: ");
        String titulo = teclado.nextLine();
        System.out.print("Ubicación: ");
        String ubicacion = teclado.nextLine();
        System.out.print("Descripción: ");
        String descripcion = teclado.nextLine();

        int id = ++contadorEvento;
        Evento evento = new Evento(id, fecha, titulo, ubicacion, descripcion);
        eventos.put(id, evento);
        System.out.println("Evento creado correctamente (ID: " + id + ")");
    }

    private static void eliminarEvento(Scanner teclado) {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos");
            return;
        }

        System.out.println("\nLista de eventos:");
        eventos.values().forEach(evento -> System.out.println("  " + evento));

        System.out.print("ID del evento a eliminar: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        if (eventos.remove(id) != null) {
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

        System.out.println("\nLista de eventos:");
        eventos.values().forEach(evento -> System.out.println("  " + evento));

        System.out.print("ID del evento para la galería: ");
        int idEvento = teclado.nextInt();
        teclado.nextLine();

        if (!eventos.containsKey(idEvento)) {
            System.out.println("ID de evento incorrecto");
            return;
        }

        System.out.print("Título de la galería: ");
        String titulo = teclado.nextLine();

        int contadorGaleria = contadorGaleriaPorEvento.getOrDefault(idEvento, 0) + 1;
        contadorGaleriaPorEvento.put(idEvento, contadorGaleria);
        int idGaleria = contadorGaleria;

        Galeria galeria = new Galeria(idGaleria, titulo, idEvento);
        eventos.get(idEvento).getGaleriaList().add(galeria);
        System.out.println("Galería creada correctamente (ID: " + idGaleria + ")");
    }

    private static void anadirFavorito(Scanner teclado) {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
            return;
        }

        if (eventos.isEmpty()) {
            System.out.println("No hay eventos registrados");
            return;
        }

        System.out.println("\nLista de usuarios:");
        usuarios.values().forEach(usuario -> System.out.println("  " + usuario));

        System.out.println("\nLista de eventos:");
        eventos.values().forEach(evento -> System.out.println("  " + evento));

        System.out.print("Correo del usuario: ");
        String correoUsuario = teclado.nextLine();

        if (!usuarios.containsKey(correoUsuario)) {
            System.out.println("El usuario no existe");
            return;
        }

        System.out.print("ID del evento: ");
        int idEvento = teclado.nextInt();
        teclado.nextLine();

        if (!eventos.containsKey(idEvento)) {
            System.out.println("El evento no existe");
            return;
        }

        for (Favorito f : favoritos) {
            if (f.getCorreoUsuario().equals(correoUsuario) && f.getIdEvento() == idEvento) {
                System.out.println("Ese favorito ya existe");
                return;
            }
        }

        Favorito favorito = new Favorito(correoUsuario, idEvento);
        favoritos.add(favorito);
        System.out.println("Favorito añadido correctamente");
    }

    private static void eliminarFavorito(Scanner teclado) {
        if (favoritos.isEmpty()) {
            System.out.println("No hay favoritos guardados");
            return;
        }

        mostrarFavoritos();

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

    private static void mostrarFavoritos() {
        if (favoritos.isEmpty()) {
            System.out.println("No hay favoritos guardados");
            return;
        }

        System.out.println("\nLista de favoritos:");
        for (Favorito f : favoritos) {
            System.out.println("  " + f);
        }
    }
}
