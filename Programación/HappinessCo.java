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
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1: anadirUsuario(scanner); break;
                case 2: eliminarUsuario(scanner); break;
                case 3: anadirEvento(scanner); break;
                case 4: eliminarEvento(scanner); break;
                case 5: anadirGaleria(scanner); break;
                case 0: System.out.println("¡Gracias por usar HAPPINESS&CO!"); break;
                default: System.out.println("❌ Opción no válida");
            }
        } while (opcion != 0);

        scanner.close();
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
        System.out.println("0. Salir");
        System.out.println("=".repeat(20));
    }

    private static void anadirUsuario(Scanner scanner) {
        System.out.print("📝 Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("📧 Email: ");
        String email = scanner.nextLine();
        System.out.print("🔒 Password: ");
        String password = scanner.nextLine();

        if (usuarios.containsKey(email)) {
            System.out.println("❌ El usuario ya existe");
        } else {
            Usuario usuario = new Usuario(nombre, email, password);
            usuarios.put(email, usuario);
            System.out.println("✅ Usuario creado correctamente");
        }
    }

    private static void eliminarUsuario(Scanner scanner) {
        System.out.print("📧 Email del usuario a eliminar: ");
        String email = scanner.nextLine();

        if (usuarios.remove(email) != null) {
            System.out.println("✅ Usuario eliminado correctamente");
        } else {
            System.out.println("❌ El usuario no existe");
        }
    }

    private static void anadirEvento(Scanner scanner) {
        System.out.print("📅 Fecha (yyyy-MM-dd): ");
        LocalDate fecha = null;
        try {
            fecha = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("❌ Formato de fecha inválido (use yyyy-MM-dd)");
            return;
        }

        System.out.print("🎉 Título: ");
        String titulo = scanner.nextLine();
        System.out.print("📍 Ubicación: ");
        String ubicacion = scanner.nextLine();
        System.out.print("📝 Descripción: ");
        String descripcion = scanner.nextLine();

        int id = ++contadorEvento;
        Evento evento = new Evento(id, fecha, titulo, ubicacion, descripcion);
        eventos.put(id, evento);
        System.out.println("✅ Evento creado correctamente (ID: " + id + ")");
    }

    private static void eliminarEvento(Scanner scanner) {
        if (eventos.isEmpty()) {
            System.out.println("❌ No hay eventos");
            return;
        }

        System.out.println("\n📋 Lista de eventos:");
        eventos.values().forEach(evento -> System.out.println("  " + evento));

        System.out.print("🗑️ ID del evento a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (eventos.remove(id) != null) {
            System.out.println("✅ Evento eliminado correctamente");
        } else {
            System.out.println("❌ El evento no existe");
        }
    }

    private static void anadirGaleria(Scanner scanner) {
        if (eventos.isEmpty()) {
            System.out.println("❌ No hay eventos");
            return;
        }

        System.out.println("\n📋 Lista de eventos:");
        eventos.values().forEach(evento -> System.out.println("  " + evento));

        System.out.print("🎨 ID del evento para la galería: ");
        int idEvento = scanner.nextInt();
        scanner.nextLine();

        if (!eventos.containsKey(idEvento)) {
            System.out.println("❌ ID de evento incorrecto");
            return;
        }

        System.out.print("🖼️ Título de la galería: ");
        String titulo = scanner.nextLine();

        int contadorGaleria = contadorGaleriaPorEvento.getOrDefault(idEvento, 0) + 1;
        contadorGaleriaPorEvento.put(idEvento, contadorGaleria);
        int idGaleria = contadorGaleria;

        Galeria galeria = new Galeria(idGaleria, titulo, idEvento);
        eventos.get(idEvento).getGaleriaList().add(galeria);
        System.out.println("✅ Galería creada correctamente (ID: " + idGaleria + ")");
    }
}
