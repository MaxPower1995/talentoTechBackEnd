public class Articulo {
    // Atributos del objeto
    int id;
    String nombre;
    double precio;

    // Constructor
    // La palabra reservada this se refiere al objeto actual
    // Se usa para diferenciar entre el atributo y el parámetro
    public Articulo(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    void mostrar() {
        System.out.print("ID: " + id);
        System.out.print("| Nombre: " + nombre);
        System.out.println("| Precio: " + precio);
    }
    
}