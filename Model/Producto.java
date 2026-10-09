package Model;

public abstract class Producto {
    private String nombre;
    private double precio;
    private int stock;
    private double[] interes = new double[4];
    private int almacenamiento;
    private int ram;

    public Producto() {
        super();
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Producto(String nombre, double precio, int stock, double[] interes, int almacenamiento, int ram) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.interes = interes;
        this.almacenamiento = almacenamiento;
        this.ram = ram;
    }

    public int getAlmacenamiento() {
        return almacenamiento;
    }

    public int getRam() {
        return ram;
    }

    public double[] getInteres() {
        return interes;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public abstract void mostrarInformacion();

}
