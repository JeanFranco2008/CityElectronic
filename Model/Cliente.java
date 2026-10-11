package Model;

public class Cliente {
    private String nombre;
    private int edad;
    private CarritoCompras carrito;

    public Cliente(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.carrito = new CarritoCompras();
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public CarritoCompras getCarrito() {
        return carrito;
    }

    public void mostrarDatoscliente() {
        System.out.println("==== CLIENTE ====");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
    }
}
