package Model;

public class Celular extends Producto {

    private int megapixeles;

    public Celular() {
    }

    public Celular(String nombre, double precio, int stock, double[] interes, int almacenamiento, int ram,
            int megapixeles) {
        super(nombre, precio, stock, interes, almacenamiento, ram);
        this.megapixeles = megapixeles;
    }

    public int getMegapixeles() {
        return megapixeles;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("==== PRODUCTO: CELULAR ====");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Stock: " + getStock());
        System.out.println("Almacenamiento: " + getAlmacenamiento());
        System.out.println("Ram: " + getRam());
        System.out.println("Megapixeles: " + getMegapixeles());

    }
}
