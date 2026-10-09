package Model;

public class Laptop extends Producto {
    private String cpu;
    private double pulgadas;

    public Laptop() {
    }

    public Laptop(String nombre, double precio, int stock, double[] interes, int almacenamiento, int ram, String cpu,
            double pulgadas) {
        super(nombre, precio, stock, interes, almacenamiento, ram);
        this.cpu = cpu;
        this.pulgadas = pulgadas;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("==== PRODUCTO: CELULAR ====");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Stock: " + getStock());
        System.out.println("Almacenamiento: " + getAlmacenamiento());
        System.out.println("Ram: " + getRam());
        System.out.println("CPU: " + getCpu());
        System.out.println("Pulgadas: " + getPulgadas());
    }

}
