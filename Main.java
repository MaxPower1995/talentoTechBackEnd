import java.util.Scanner;
import java.util.ArrayList;

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
            

            opcion = leerIntValido(scanner);
            if(opcion == -1) {
                System.out.println("Error: Opción inválida.");
                opcion = 5; // Salir del programa
            }
            
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
        int id = leerIntValido(scanner); 
        if(id == -1) {
            System.out.println("Error: ID inválido.");
            return;
        }
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
        double precio = leerDoubleValidado(scanner);
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
        int id = leerIntValido(scanner); // Limpiar el buffer
        if(id == -1) {
            System.out.println("Error: ID inválido.");
            return;
        }
        if(!existeArticuloConId(id)) {
            System.out.println("El ID no existe. Por favor, ingrese un ID válido.");
            return;
        }
        for (Articulo articulo : lista) {
            if (articulo.id == id) {
                System.out.print("Ingrese el nuevo nombre del artículo: ");
                articulo.nombre = scanner.nextLine();
                if(String.valueOf(articulo.nombre).length() > 20) {
                    System.out.println("El nombre no puede tener más de 20 caracteres.");
                    return;
                }
                System.out.print("Ingrese el nuevo precio del artículo: ");
                articulo.precio = leerDoubleValidado(scanner);
                System.out.println("Artículo modificado con éxito.");
                return;
            }
        }
        System.out.println("No se encontró un artículo con ese ID.");
    }

    public static void eliminarArticulo() {
        System.out.println("### Eliminar Artículo ###");
        // Si la lista está vacía, no se puede eliminar
        if(lista.isEmpty()) {
            System.out.println("No hay artículos disponibles para eliminar.");
            return;
        }
        System.out.print("Ingrese el ID del artículo a eliminar: ");
        int id = leerIntValido(scanner); // Limpiar el buffer
        if(id == -1) {
            System.out.println("Error: ID inválido.");
            return;
        }
        if(!existeArticuloConId(id)) {
            System.out.println("El ID no existe. Por favor, ingrese un ID válido.");
            return;
        }
        for (Articulo articulo : lista) {
            if (articulo.id == id) {
                lista.remove(articulo);
                articulo.mostrar();
                System.out.println("Artículo eliminado con éxito.");
                return;
            }
        }
        //Funcion lamda o flecha
        // lista.removeIf(articulo -> articulo.id == id);

        
    }

    private static void precargarArticulos() {
        // Implementación de cualquiera de los métodos anteriores
        lista.add(new Articulo(1, "Laptop", 1200.99));
        lista.add(new Articulo(2, "Teléfono", 599.50));
        lista.add(new Articulo(3, "Tablet", 350.75));
        lista.add(new Articulo(4, "Auriculares", 89.99));
        lista.add(new Articulo(5, "Teclado", 45.25));
        lista.add(new Articulo(6, "Ratón", 25.50));
        lista.add(new Articulo(7, "Monitor", 299.99));
        lista.add(new Articulo(8, "Impresora", 199.99));
        lista.add(new Articulo(9, "Cámara", 499.99));
        lista.add(new Articulo(10, "Proyector", 799.99));
    }

    public static boolean existeArticuloConId(int id) {
        for (Articulo articulo : lista) {
            if (articulo.id == id) {
                return true; // ID ya existe
            }
        }
        return false; // ID no existe
    }

    public static int leerIntValido(Scanner scanner) {
        int opcion = -1; // Inicializa la opción
        String input = scanner.nextLine();  // Siempre lee como String
        try {
            opcion = Integer.parseInt(input);  // Intenta convertir a int
            return opcion;  // Si la conversión funciona, retorna el valor
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero. Intente nuevamente.");
        }
        return opcion;  // Si se alcanzan los intentos, retorna -1 para salir
    }

    public static double leerDoubleValidado(Scanner scanner) {
        double valor = -1; // Inicializa el valor
        String input = scanner.nextLine();
        try {
            valor = Double.parseDouble(input); // Solo acepta punto decimal
            // Resto de la validación igual...
        } catch (NumberFormatException e) {
            System.out.println("Error: Use punto decimal (ej: 19.95)");
        }
        return valor; // Retorna el valor validado
    }
}