package Model;

public class CarritoCompras {

    // Cada carrito puede guardar hasta 20 productos
    private Producto[] productos = new Producto[20];
    private int cantidadProductos = 0;

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void agregarProducto(Producto producto) {
        if (cantidadProductos < productos.length) {
            productos[cantidadProductos] = producto;
            cantidadProductos++;

            System.out.println("Producto agregado al carrito.");
        } else {
            System.out.println("El carrito esta lleno.");
        }
    }

    public void mostrarCarrito() {
        if (cantidadProductos == 0) {
            System.out.println("El carrito esta vacio.");
            return;
        }

        for (int i = 0; i < cantidadProductos; i++) {
            System.out.println(
                    (i + 1) + ". " +
                            productos[i].getNombre() +
                            " - S/ " + productos[i].getPrecio());
        }
    }

    public void eliminarProducto(int posicion) {
        if (posicion < 0 || posicion >= cantidadProductos) {
            System.out.println("Posicion incorrecta.");
            return;
        }

        for (int i = posicion; i < cantidadProductos - 1; i++) {
            productos[i] = productos[i + 1];
        }

        productos[cantidadProductos - 1] = null;
        cantidadProductos--;

        System.out.println("Producto eliminado del carrito.");
    }

    public double calcularTotal() {
        double total = 0;

        for (int i = 0; i < cantidadProductos; i++) {
            total += productos[i].getPrecio();
        }

        return total;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void vaciarCarrito() {
        for (int i = 0; i < cantidadProductos; i++) {
            productos[i] = null;
        }

        cantidadProductos = 0;
    }
}
