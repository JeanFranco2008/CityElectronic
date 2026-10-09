package Model;

public class Tablet extends Producto {

    public Tablet(String nombre, double precio, int stock, double[] interes, int almacenamiento, int ram) {
        super(nombre, precio, stock, interes, almacenamiento, ram);
    }

    public Tablet() {
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("==== PRODUCTO: TABLET ====");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Stock: " + getStock());
        System.out.println("Almacenamiento: " + getAlmacenamiento());
        System.out.println("Ram: " + getRam());

    }
}
