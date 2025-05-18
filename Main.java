import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Articulo> lista = new ArrayList<>();
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        
        // Precargar algunos artículos de ejemplo2
        precargarArticulos();
        
        do {
            System.out.println("\n### Menú de Artículos: ###");
            System.out.println("1. Crear Artículo");
            System.out.println("2. Listar Artículos");
            System.out.println("3. Modificar Artículo");
            System.out.println("4. Eliminar Artículo");
            System.out.println("5. Salir del Programa");
            System.out.print("Seleccione una opción: ");
            

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1 -> crearArticulo();
                case 2 -> listarArticulos();
                case 3 -> modificarArticulo();
                case 4 -> eliminarArticulo();
                case 5 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

            
        } while (opcion != 5);

        scanner.close();
    }

    public static void crearArticulo() {
        System.out.println("### Crear Artículo ###");
        System.out.print("Ingrese el ID del artículo: ");
        int id = scanner.nextInt(); scanner.nextLine(); // Limpiar el buffer
        if (existeArticuloConId(id)) {
            System.out.println("El ID ya existe. Por favor, ingrese un ID único.");
            return;
        }
        if (id <= 0 || id > 99999999) {
            System.out.println("El ID debe ser mayor a 0 y menor a 99999999.");
            return;
        }

        System.out.print("Ingrese el nombre del artículo: ");
        String nombre = scanner.nextLine();

        if(String.valueOf(nombre).length() > 20) {
            System.out.println("El nombre no puede tener más de 20 caracteres.");
            return;
        }
        System.out.print("Ingrese el precio del artículo: ");
        double precio = scanner.nextDouble();
        if (precio <= 0 || precio > 99999999) {
            System.out.println("El precio debe ser mayor a 0 y menor a 99999999.");
            return;
        }
        Articulo articulo = new Articulo(id, nombre, precio);
        lista.add(articulo);
        System.out.println("Artículo creado con éxito.");
        articulo.mostrar();
    }

    public static void listarArticulos() {
        System.out.println("### Listar Artículos ###");
        if (lista.isEmpty()) {
            System.out.println("No hay artículos registrados.");
        } else {
            for (Articulo articulo : lista) { 
                articulo.mostrar();
            }
        }
    }

    public static void modificarArticulo() {
        System.out.println("### Modificar Artículo ###");
        // Si la lista está vacía, no se puede modificar
        if(lista.isEmpty()) {
            System.out.println("No hay artículos disponibles para modificar.");
            return;
        }
        System.out.print("Ingrese el ID del artículo a modificar: ");
        int id = scanner.nextInt(); scanner.nextLine(); // Limpiar el buffer
        for (Articulo articulo : lista) {
            if (articulo.id == id) {
                System.out.print("Ingrese el nuevo nombre del artículo: ");
                articulo.nombre = scanner.nextLine();
                System.out.print("Ingrese el nuevo precio del artículo: ");
                articulo.precio = scanner.nextDouble();
                System.out.println("Artículo modificado con éxito.");
                return;
            }
        System.out.println("No se encontró un artículo con ese ID.");
        }
    }

    public static void eliminarArticulo() {
        System.out.println("### Eliminar Artículo ###");
        // Si la lista está vacía, no se puede eliminar
        if(lista.isEmpty()) {
            System.out.println("No hay artículos disponibles para eliminar.");
            return;
        }
        System.out.print("Ingrese el ID del artículo a eliminar: ");
        int id = scanner.nextInt(); scanner.nextLine(); // Limpiar el buffer
        for (Articulo articulo : lista) {
            if (articulo.id == id) {
                lista.remove(articulo);
                System.out.println("Artículo eliminado con éxito.");
                return;
            }
        }
        //Funcion lamda o flecha
        // lista.removeIf(articulo -> articulo.id == id);
        System.out.println("No se encontró un artículo con ese ID.");
    }

    private static void precargarArticulos() {
        // Implementación de cualquiera de los métodos anteriores
        lista.add(new Articulo(1, "Laptop", 1200.99));
        lista.add(new Articulo(2, "Teléfono", 599.50));
        lista.add(new Articulo(3, "Tablet", 350.75));
        lista.add(new Articulo(4, "Auriculares", 89.99));
        lista.add(new Articulo(5, "Teclado", 45.25));
    }

    public static boolean existeArticuloConId(int id) {
        for (Articulo articulo : lista) {
            if (articulo.id == id) {
                return true; // ID ya existe
            }
        }
        return false; // ID no existe
    }
}