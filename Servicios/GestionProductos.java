package Servicios;

import java.util.Scanner;

import Model.Celular;
import Model.Laptop;
import Model.Producto;
import Model.Tablet;
import Configuracion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionProductos {

    private Producto[] producto1;
    private Producto[] producto2;

    public Producto[] getProducto1() {
        return producto1;
    }

    public Producto[] getProducto2() {
        return producto2;
    }

    public Producto[] getProducto3() {
        return producto3;
    }

    private Producto[] producto3;
    private int cantidadProductos1;
    private int cantidadProductos2;
    private int cantidadProductos3;

    public int getCantidadProductos1() {
        return cantidadProductos1;
    }

    public int getCantidadProductos2() {
        return cantidadProductos2;
    }

    public int getCantidadProductos3() {
        return cantidadProductos3;
    }

    public GestionProductos() {
        producto1 = new Celular[3];
        producto2 = new Tablet[3];
        producto3 = new Laptop[3];
        cantidadProductos1 = 0;
        cantidadProductos2 = 0;
        cantidadProductos3 = 0;

    }

    public void agregarCelular(Scanner teclado) {
        if (cantidadProductos1 >= producto1.length) {
            System.out.println("No hay espacio para más celulares.");
            return;
        }
        teclado.nextLine();
        System.out.println("Nombre del celular:");
        String nombre = teclado.nextLine();
        System.out.println("Precio del " + nombre + ": ");
        Double precio = teclado.nextDouble();
        System.out.println("Stock del " + nombre + ": ");
        int stock = teclado.nextInt();
        double[] interes = new double[4];
        for (int i = 0; i < 4; i++) {
            System.out.println("Ingrese el interes del mes " + ((i + 1) * 3) + ":");
            interes[i] = teclado.nextDouble();
        }
        System.out.println("Almacenamiento del " + nombre + ": ");
        int almacenamiento = teclado.nextInt();
        System.out.println("Ram del " + nombre + ": ");
        int ram = teclado.nextInt();
        System.out.println("Megapixeles del " + nombre + ": ");
        int megapixeles = teclado.nextInt();
        teclado.nextLine();

        if (guardarCelularBD(
                nombre, precio, stock, interes,
                almacenamiento, ram, megapixeles)) {

            producto1[cantidadProductos1] = new Celular(
                    nombre, precio, stock, interes,
                    almacenamiento, ram, megapixeles);

            cantidadProductos1++;

            System.out.println("Celular agregado exitosamente");
        }

    }

    public void agregarTablet(Scanner teclado) {
        if (cantidadProductos2 >= producto2.length) {
            System.out.println("No hay espacio para más Tables.");
            return;
        }
        teclado.nextLine();
        System.out.println("Nombre de la Tablet");
        String nombre = teclado.nextLine();
        System.out.println("Precio del " + nombre + ": ");
        Double precio = teclado.nextDouble();
        System.out.println("Stock del " + nombre + ": ");
        int stock = teclado.nextInt();
        double[] interes = new double[4];
        for (int i = 0; i < 4; i++) {
            System.out.println("Ingrese el interes del mes " + ((i + 1) * 3) + ":");
            interes[i] = teclado.nextDouble();
        }
        System.out.println("Almacenamiento del " + nombre + ": ");
        int almacenamiento = teclado.nextInt();
        System.out.println("Ram del " + nombre + ": ");
        int ram = teclado.nextInt();
        teclado.nextLine();

        if (guardarTabletBD(
                nombre, precio, stock, interes, almacenamiento, ram)) {
            producto2[cantidadProductos2] = new Tablet(
                    nombre, precio, stock, interes,
                    almacenamiento, ram);

            cantidadProductos2++;
            System.out.println("Tablet agregada exitosamente");
        }

    }

    public void agregarLaptop(Scanner teclado) {
        if (cantidadProductos3 >= producto3.length) {
            System.out.println("No hay espacio para más Laptops.");
            return;
        }
        teclado.nextLine();
        System.out.println("Nombre de la Laptop");
        String nombre = teclado.nextLine();
        System.out.println("Precio del " + nombre + ": ");
        Double precio = teclado.nextDouble();
        System.out.println("Stock del " + nombre + ": ");
        int stock = teclado.nextInt();
        double[] interes = new double[4];
        for (int i = 0; i < 4; i++) {
            System.out.println("Ingrese el interes del mes " + ((i + 1) * 3) + ":");
            interes[i] = teclado.nextDouble();
        }
        System.out.println("Almacenamiento del " + nombre + ": ");
        int almacenamiento = teclado.nextInt();
        System.out.println("Ram del " + nombre + ": ");
        int ram = teclado.nextInt();
        teclado.nextLine();
        System.out.println("CPU del " + nombre + ": ");
        String cpu = teclado.nextLine();
        System.out.println("Pulgadas del " + nombre + ": ");
        double pulgadas = teclado.nextDouble();
        teclado.nextLine();

        if (guardarLaptopBD(
                nombre, precio, stock, interes,
                almacenamiento, ram, cpu, pulgadas)) {
            producto3[cantidadProductos3] = new Laptop(
                    nombre, precio, stock, interes,
                    almacenamiento, ram, cpu, pulgadas);

            cantidadProductos3++;
            System.out.println("Laptop agregada exitosamente");
        }

    }

    public void actualizarStockCelular(Scanner teclado) {

        cargarCelularesBD();

        if (cantidadProductos1 == 0) {
            System.out.println("No hay celulares registrados.");
            return;
        }

        int buscar;
        int opcion;
        int cantidad;
        int stock;
        int nuevoStock;

        System.out.println("===== CELULARES REGISTRADOS =====");

        for (int i = 0; i < cantidadProductos1; i++) {
            System.out.println((i + 1) + ". "
                    + producto1[i].getNombre()
                    + " ===== Stock: " + producto1[i].getStock());
        }

        do {
            System.out.println("Ingrese la opcion del celular:");
            buscar = teclado.nextInt();

            if (buscar < 1 || buscar > cantidadProductos1) {
                System.out.println("Opcion incorrecta.");
            }
        } while (buscar < 1 || buscar > cantidadProductos1);

        stock = producto1[buscar - 1].getStock();

        do {
            System.out.println("1. Aumentar");
            System.out.println("2. Disminuir");
            System.out.println("Elige una opcion:");
            opcion = teclado.nextInt();

            if (opcion != 1 && opcion != 2) {
                System.out.println("Opcion incorrecta.");
            }
        } while (opcion != 1 && opcion != 2);

        do {
            System.out.println("Ingrese la cantidad:");
            cantidad = teclado.nextInt();

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor que cero.");
            } else if (opcion == 2 && cantidad > stock) {
                System.out.println("No hay suficiente stock.");
            }
        } while (cantidad <= 0 || (opcion == 2 && cantidad > stock));

        if (opcion == 1) {
            nuevoStock = stock + cantidad;
        } else {
            nuevoStock = stock - cantidad;
        }

        String nombre = producto1[buscar - 1].getNombre();

        if (actualizarStockBD(nombre, "Celular", nuevoStock)) {
            producto1[buscar - 1].setStock(nuevoStock);
            System.out.println("Stock actualizado correctamente.");
            System.out.println("Nuevo stock: " + nuevoStock);
        }
    }

    public void actualizarStockLaptop(Scanner teclado) {

        cargarLaptopsBD();

        if (cantidadProductos3 == 0) {
            System.out.println("No hay laptops registradas.");
            return;
        }

        int buscar;
        int opcion;
        int cantidad;
        int stock;
        int nuevoStock;

        System.out.println("===== LAPTOPS REGISTRADAS =====");

        for (int i = 0; i < cantidadProductos3; i++) {
            System.out.println((i + 1) + ". "
                    + producto3[i].getNombre()
                    + " ===== Stock: " + producto3[i].getStock());
        }

        do {
            System.out.println("Ingrese la opcion de la laptop:");
            buscar = teclado.nextInt();

            if (buscar < 1 || buscar > cantidadProductos3) {
                System.out.println("Opcion incorrecta.");
            }
        } while (buscar < 1 || buscar > cantidadProductos3);

        stock = producto3[buscar - 1].getStock();

        do {
            System.out.println("1. Aumentar");
            System.out.println("2. Disminuir");
            System.out.println("Elige una opcion:");
            opcion = teclado.nextInt();

            if (opcion != 1 && opcion != 2) {
                System.out.println("Opcion incorrecta.");
            }
        } while (opcion != 1 && opcion != 2);

        do {
            System.out.println("Ingrese la cantidad:");
            cantidad = teclado.nextInt();

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor que cero.");
            } else if (opcion == 2 && cantidad > stock) {
                System.out.println("No hay suficiente stock.");
            }
        } while (cantidad <= 0 || (opcion == 2 && cantidad > stock));

        if (opcion == 1) {
            nuevoStock = stock + cantidad;
        } else {
            nuevoStock = stock - cantidad;
        }

        String nombre = producto3[buscar - 1].getNombre();

        if (actualizarStockBD(nombre, "Laptop", nuevoStock)) {
            producto3[buscar - 1].setStock(nuevoStock);
            System.out.println("Stock actualizado correctamente.");
            System.out.println("Nuevo stock: " + nuevoStock);
        }
    }

    public void actualizarStockTablet(Scanner teclado) {

        cargarTabletsBD();

        if (cantidadProductos2 == 0) {
            System.out.println("No hay tablets registradas.");
            return;
        }

        int buscar;
        int opcion;
        int cantidad;
        int stock;
        int nuevoStock;

        System.out.println("===== TABLETS REGISTRADAS =====");

        for (int i = 0; i < cantidadProductos2; i++) {
            System.out.println((i + 1) + ". "
                    + producto2[i].getNombre()
                    + " ===== Stock: " + producto2[i].getStock());
        }

        do {
            System.out.println("Ingrese la opcion de la tablet:");
            buscar = teclado.nextInt();

            if (buscar < 1 || buscar > cantidadProductos2) {
                System.out.println("Opcion incorrecta.");
            }
        } while (buscar < 1 || buscar > cantidadProductos2);

        stock = producto2[buscar - 1].getStock();

        do {
            System.out.println("1. Aumentar");
            System.out.println("2. Disminuir");
            System.out.println("Elige una opcion:");
            opcion = teclado.nextInt();

            if (opcion != 1 && opcion != 2) {
                System.out.println("Opcion incorrecta.");
            }
        } while (opcion != 1 && opcion != 2);

        do {
            System.out.println("Ingrese la cantidad:");
            cantidad = teclado.nextInt();

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor que cero.");
            } else if (opcion == 2 && cantidad > stock) {
                System.out.println("No hay suficiente stock.");
            }
        } while (cantidad <= 0 || (opcion == 2 && cantidad > stock));

        if (opcion == 1) {
            nuevoStock = stock + cantidad;
        } else {
            nuevoStock = stock - cantidad;
        }

        String nombre = producto2[buscar - 1].getNombre();

        if (actualizarStockBD(nombre, "Tablet", nuevoStock)) {
            producto2[buscar - 1].setStock(nuevoStock);
            System.out.println("Stock actualizado correctamente.");
            System.out.println("Nuevo stock: " + nuevoStock);
        }
    }

    public void mostrarInformacionCelular(Scanner teclado) {
        cargarCelularesBD();

        int buscar;

        System.out.println("===== CELULARES REGISTRADOS =====");

        if (cantidadProductos1 == 0) {
            System.out.println("No hay celulares registrados.");
            return;
        }

        System.out.println("===== CELULARES REGISTRADOS =====");
        for (int i = 0; i < cantidadProductos1; i++) {
            System.out.println((i + 1) + ". Celular: " + producto1[i].getNombre());

        }
        do {
            System.out.println("Ingrese la opcion del celular: ");
            buscar = teclado.nextInt();
            if (buscar == 0 || buscar > cantidadProductos1) {
                System.out.println("Opcion incorrecta, vuelve a intentarlo");
            }
        } while (buscar == 0 || buscar > cantidadProductos1);
        producto1[buscar - 1].mostrarInformacion();

    }

    public void mostrarInformacionLaptop(Scanner teclado) {
        cargarLaptopsBD();
        int buscar;
        if (cantidadProductos3 == 0) {
            System.out.println("No hay celulares registrados.");
            return;
        }
        System.out.println("===== LAPTOPS REGISTRADAS =====");
        for (int i = 0; i < cantidadProductos3; i++) {
            System.out.println((i + 1) + ". Celular: " + producto3[i].getNombre());
        }
        do {
            System.out.println("Ingrese la opcion de la Laptop: ");
            buscar = teclado.nextInt();
            if (buscar == 0 || buscar > cantidadProductos3) {
                System.out.println("Opcion incorrecta, vuelve a intentarlo");
            }
        } while (buscar == 0 || buscar > cantidadProductos3);
        producto3[buscar - 1].mostrarInformacion();

    }

    public void mostrarInformacionTablet(Scanner teclado) {
        cargarTabletsBD();
        int buscar;
        if (cantidadProductos2 == 0) {
            System.out.println("No hay celulares registrados.");
            return;
        }
        System.out.println("===== TABLETS REGISTRADAS =====");
        for (int i = 0; i < cantidadProductos2; i++) {
            System.out.println((i + 1) + ". Celular: " + producto2[i].getNombre());
        }
        do {
            System.out.println("Ingrese la opcion de la tablet: ");
            buscar = teclado.nextInt();
            if (buscar == 0 || buscar > cantidadProductos2) {
                System.out.println("Opcion incorrecta, vuelve a intentarlo");
            }
        } while (buscar == 0 || buscar > cantidadProductos2);
        producto2[buscar - 1].mostrarInformacion();

    }

    public void eliminarCelular(Scanner teclado) {

        cargarCelularesBD();

        if (cantidadProductos1 == 0) {
            System.out.println("No hay celulares registrados.");
            return;
        }

        System.out.println("===== ELIMINAR CELULAR =====");

        for (int i = 0; i < cantidadProductos1; i++) {
            System.out.println((i + 1) + ". "
                    + producto1[i].getNombre());
        }

        int buscar;
        do {
            System.out.println("Ingrese la opcion del celular a eliminar:");
            buscar = teclado.nextInt();

            if (buscar < 1 || buscar > cantidadProductos1) {
                System.out.println("Opcion incorrecta.");
            }
        } while (buscar < 1 || buscar > cantidadProductos1);

        int indice = buscar - 1;
        String nombre = producto1[indice].getNombre();

        System.out.println("¿Desea eliminar " + nombre + "?");
        System.out.println("1. Si");
        System.out.println("2. No");

        int opcion = teclado.nextInt();

        if (opcion != 1) {
            System.out.println("Eliminacion cancelada.");
            return;
        }

        if (eliminarProductoBD(nombre, "Celular")) {

            for (int i = indice; i < cantidadProductos1 - 1; i++) {
                producto1[i] = producto1[i + 1];
            }

            producto1[--cantidadProductos1] = null;

            System.out.println("Celular eliminado correctamente.");
        }
    }

    public void eliminarLaptop(Scanner teclado) {

        cargarLaptopsBD();

        if (cantidadProductos3 == 0) {
            System.out.println("No hay laptops registradas.");
            return;
        }

        System.out.println("===== ELIMINAR LAPTOP =====");

        for (int i = 0; i < cantidadProductos3; i++) {
            System.out.println((i + 1) + ". "
                    + producto3[i].getNombre());
        }

        int buscar;
        do {
            System.out.println("Ingrese la opcion de la laptop a eliminar:");
            buscar = teclado.nextInt();

            if (buscar < 1 || buscar > cantidadProductos3) {
                System.out.println("Opcion incorrecta.");
            }
        } while (buscar < 1 || buscar > cantidadProductos3);

        int indice = buscar - 1;
        String nombre = producto3[indice].getNombre();

        System.out.println("¿Desea eliminar " + nombre + "?");
        System.out.println("1. Si");
        System.out.println("2. No");

        int opcion = teclado.nextInt();

        if (opcion != 1) {
            System.out.println("Eliminacion cancelada.");
            return;
        }

        if (eliminarProductoBD(nombre, "Laptop")) {

            for (int i = indice; i < cantidadProductos3 - 1; i++) {
                producto3[i] = producto3[i + 1];
            }

            producto3[--cantidadProductos3] = null;

            System.out.println("Laptop eliminada correctamente.");
        }
    }

    public void eliminarTablet(Scanner teclado) {

        cargarTabletsBD();

        if (cantidadProductos2 == 0) {
            System.out.println("No hay tablets registradas.");
            return;
        }

        System.out.println("===== ELIMINAR TABLET =====");

        for (int i = 0; i < cantidadProductos2; i++) {
            System.out.println((i + 1) + ". "
                    + producto2[i].getNombre());
        }

        int buscar;
        do {
            System.out.println("Ingrese la opcion de la tablet a eliminar:");
            buscar = teclado.nextInt();

            if (buscar < 1 || buscar > cantidadProductos2) {
                System.out.println("Opcion incorrecta.");
            }
        } while (buscar < 1 || buscar > cantidadProductos2);

        int indice = buscar - 1;
        String nombre = producto2[indice].getNombre();

        System.out.println("¿Desea eliminar " + nombre + "?");
        System.out.println("1. Si");
        System.out.println("2. No");

        int opcion = teclado.nextInt();

        if (opcion != 1) {
            System.out.println("Eliminacion cancelada.");
            return;
        }

        if (eliminarProductoBD(nombre, "Tablet")) {

            for (int i = indice; i < cantidadProductos2 - 1; i++) {
                producto2[i] = producto2[i + 1];
            }

            producto2[--cantidadProductos2] = null;

            System.out.println("Tablet eliminada correctamente.");
        }
    }

    public Producto seleccionarCelular(Scanner teclado) {
        cargarCelularesBD();

        if (cantidadProductos1 == 0) {
            System.out.println("No hay celulares registrados.");
            return null;
        }

        System.out.println("===== CELULARES DISPONIBLES =====");
        for (int i = 0; i < cantidadProductos1; i++) {
            System.out.println((i + 1) + ". " +
                    producto1[i].getNombre() +
                    " - S/ " + producto1[i].getPrecio() +
                    " - Stock: " + producto1[i].getStock());
        }

        int buscar;
        do {
            System.out.print("Elija el celular: ");
            buscar = teclado.nextInt();

            if (buscar < 1 || buscar > cantidadProductos1) {
                System.out.println("Opcion incorrecta.");
            }
        } while (buscar < 1 || buscar > cantidadProductos1);

        return producto1[buscar - 1];
    }

    public Producto seleccionarLaptop(Scanner teclado) {
        cargarLaptopsBD();
        if (cantidadProductos3 == 0) {
            System.out.println("No hay laptops registradas.");
            return null;
        }

        System.out.println("===== LAPTOPS DISPONIBLES =====");
        for (int i = 0; i < cantidadProductos3; i++) {
            System.out.println((i + 1) + ". " +
                    producto3[i].getNombre() +
                    " - S/ " + producto3[i].getPrecio() +
                    " - Stock: " + producto3[i].getStock());
        }

        int buscar;
        do {
            System.out.print("Elija la laptop: ");
            buscar = teclado.nextInt();

            if (buscar < 1 || buscar > cantidadProductos3) {
                System.out.println("Opcion incorrecta.");
            }
        } while (buscar < 1 || buscar > cantidadProductos3);

        return producto3[buscar - 1];
    }

    public Producto seleccionarTablet(Scanner teclado) {
        cargarTabletsBD();
        if (cantidadProductos2 == 0) {
            System.out.println("No hay tablets registradas.");
            return null;
        }

        System.out.println("===== TABLETS DISPONIBLES =====");
        for (int i = 0; i < cantidadProductos2; i++) {
            System.out.println((i + 1) + ". " +
                    producto2[i].getNombre() +
                    " - S/ " + producto2[i].getPrecio() +
                    " - Stock: " + producto2[i].getStock());
        }

        int buscar;
        do {
            System.out.print("Elija la tablet: ");
            buscar = teclado.nextInt();

            if (buscar < 1 || buscar > cantidadProductos2) {
                System.out.println("Opcion incorrecta.");
            }
        } while (buscar < 1 || buscar > cantidadProductos2);

        return producto2[buscar - 1];
    }

    private boolean guardarProductoBD(String nombre, String tipo, double precio, int stock, double[] interes) {

        String sql = """
                INSERT INTO productos
                (nombre, tipo, precio, stock,
                 interes_3, interes_6, interes_9, interes_12)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conexion = Conexion.conectar();
                PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setString(1, nombre);
            consulta.setString(2, tipo);
            consulta.setDouble(3, precio);
            consulta.setInt(4, stock);

            for (int i = 0; i < 4; i++) {
                consulta.setDouble(5 + i, interes[i]);
            }

            consulta.executeUpdate();

            System.out.println("Producto guardado en MySQL.");
            return true;

        } catch (SQLException e) {
            System.out.println(
                    "Error al guardar el producto: " + e.getMessage());
            return false;
        }
    }

    private boolean guardarCelularBD(
            String nombre,
            double precio,
            int stock,
            double[] interes,
            int almacenamiento,
            int ram,
            int megapixeles) {

        String sqlProducto = "INSERT INTO productos " +
                "(nombre, tipo, precio, stock, " +
                "interes_3, interes_6, interes_9, interes_12) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlCelular = "INSERT INTO celulares " +
                "(id_producto, almacenamiento, ram, megapixeles) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conexion = Conexion.conectar()) {

            // Iniciar una transaccion
            conexion.setAutoCommit(false);

            try {
                int idProducto;

                // 1. Guardar los datos generales
                try (PreparedStatement consulta = conexion.prepareStatement(
                        sqlProducto,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

                    consulta.setString(1, nombre);
                    consulta.setString(2, "Celular");
                    consulta.setDouble(3, precio);
                    consulta.setInt(4, stock);

                    for (int i = 0; i < 4; i++) {
                        consulta.setDouble(5 + i, interes[i]);
                    }

                    consulta.executeUpdate();

                    // Obtener el ID generado por MySQL
                    try (ResultSet resultado = consulta.getGeneratedKeys()) {

                        if (!resultado.next()) {
                            throw new SQLException(
                                    "No se pudo obtener el ID del celular.");
                        }

                        idProducto = resultado.getInt(1);
                    }
                }

                // 2. Guardar las caracteristicas del celular
                try (PreparedStatement consulta = conexion.prepareStatement(sqlCelular)) {

                    consulta.setInt(1, idProducto);
                    consulta.setInt(2, almacenamiento);
                    consulta.setInt(3, ram);
                    consulta.setInt(4, megapixeles);

                    consulta.executeUpdate();
                }

                // Confirmar ambas inserciones
                conexion.commit();

                System.out.println(
                        "Celular y caracteristicas guardados en MySQL.");

                return true;

            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al guardar el celular: " + e.getMessage());

            return false;
        }
    }

    private boolean guardarLaptopBD(
            String nombre, double precio, int stock,
            double[] interes, int almacenamiento, int ram,
            String cpu, double pulgadas) {

        String sqlProducto = "INSERT INTO productos "
                + "(nombre, tipo, precio, stock, interes_3, interes_6, interes_9, interes_12) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlLaptop = "INSERT INTO laptops "
                + "(id_producto, procesador, ram, almacenamiento, pulgadas) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.conectar()) {
            conexion.setAutoCommit(false);

            try {
                int idProducto;

                // 1. Guardar los datos generales del producto
                try (PreparedStatement consulta = conexion.prepareStatement(
                        sqlProducto, java.sql.Statement.RETURN_GENERATED_KEYS)) {

                    consulta.setString(1, nombre);
                    consulta.setString(2, "Laptop");
                    consulta.setDouble(3, precio);
                    consulta.setInt(4, stock);

                    for (int i = 0; i < 4; i++) {
                        consulta.setDouble(5 + i, interes[i]);
                    }

                    consulta.executeUpdate();

                    try (ResultSet resultado = consulta.getGeneratedKeys()) {
                        if (!resultado.next()) {
                            throw new SQLException(
                                    "No se obtuvo el ID de la laptop.");
                        }

                        idProducto = resultado.getInt(1);
                    }
                }

                // 2. Guardar las características de la laptop
                try (PreparedStatement consulta = conexion.prepareStatement(sqlLaptop)) {
                    consulta.setInt(1, idProducto);
                    consulta.setString(2, cpu);
                    consulta.setInt(3, ram);
                    consulta.setInt(4, almacenamiento);
                    consulta.setDouble(5, pulgadas);

                    consulta.executeUpdate();
                }

                conexion.commit();
                System.out.println("Laptop y características guardadas en MySQL.");
                return true;

            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            } finally {
                conexion.setAutoCommit(true);
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar la laptop: " + e.getMessage());
            return false;
        }
    }

    private boolean guardarTabletBD(
            String nombre, double precio, int stock,
            double[] interes, int almacenamiento, int ram) {

        String sqlProducto = "INSERT INTO productos "
                + "(nombre, tipo, precio, stock, interes_3, interes_6, interes_9, interes_12) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlTablet = "INSERT INTO tablets "
                + "(id_producto, almacenamiento, ram) VALUES (?, ?, ?)";

        try (Connection conexion = Conexion.conectar()) {
            conexion.setAutoCommit(false);

            try {
                int idProducto;

                try (PreparedStatement consulta = conexion.prepareStatement(
                        sqlProducto, java.sql.Statement.RETURN_GENERATED_KEYS)) {

                    consulta.setString(1, nombre);
                    consulta.setString(2, "Tablet");
                    consulta.setDouble(3, precio);
                    consulta.setInt(4, stock);

                    for (int i = 0; i < 4; i++) {
                        consulta.setDouble(5 + i, interes[i]);
                    }

                    consulta.executeUpdate();

                    try (ResultSet resultado = consulta.getGeneratedKeys()) {
                        if (!resultado.next()) {
                            throw new SQLException("No se obtuvo el ID de la tablet.");
                        }

                        idProducto = resultado.getInt(1);
                    }
                }

                try (PreparedStatement consulta = conexion.prepareStatement(sqlTablet)) {
                    consulta.setInt(1, idProducto);
                    consulta.setInt(2, almacenamiento);
                    consulta.setInt(3, ram);
                    consulta.executeUpdate();
                }

                conexion.commit();
                System.out.println("Tablet y caracteristicas guardadas en MySQL.");
                return true;

            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar la tablet: " + e.getMessage());
            return false;
        }
    }

    public void cargarCelularesBD() {

        String sql = "SELECT p.id_producto, p.nombre, p.precio, p.stock, "
                + "p.interes_3, p.interes_6, p.interes_9, p.interes_12, "
                + "c.almacenamiento, c.ram, c.megapixeles "
                + "FROM productos p "
                + "INNER JOIN celulares c ON p.id_producto = c.id_producto "
                + "WHERE p.tipo = 'Celular'";

        try (Connection conexion = Conexion.conectar();
                PreparedStatement consulta = conexion.prepareStatement(sql);
                ResultSet resultado = consulta.executeQuery()) {

            cantidadProductos1 = 0;

            while (resultado.next()) {

                // Ampliar el arreglo si se supera su capacidad
                if (cantidadProductos1 >= producto1.length) {
                    producto1 = java.util.Arrays.copyOf(
                            producto1, producto1.length + 3);
                }

                double[] interes = new double[4];

                interes[0] = resultado.getDouble("interes_3");
                interes[1] = resultado.getDouble("interes_6");
                interes[2] = resultado.getDouble("interes_9");
                interes[3] = resultado.getDouble("interes_12");

                String nombre = resultado.getString("nombre");
                double precio = resultado.getDouble("precio");
                int stock = resultado.getInt("stock");
                int almacenamiento = Integer.parseInt(
                        resultado.getString("almacenamiento"));
                int ram = Integer.parseInt(
                        resultado.getString("ram"));
                int megapixeles = Integer.parseInt(
                        resultado.getString("megapixeles"));

                Celular celular = new Celular(
                        nombre, precio, stock, interes,
                        almacenamiento, ram, megapixeles);

                celular.setIdProducto(resultado.getInt("id_producto"));

                producto1[cantidadProductos1] = celular;
                cantidadProductos1++;
            }

            System.out.println("Celulares cargados desde MySQL: "
                    + cantidadProductos1);

        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error al cargar celulares: "
                    + e.getMessage());
        }
    }

    public void cargarTabletsBD() {
        String sql = "SELECT p.id_producto, p.nombre, p.precio, p.stock, "
                + "p.interes_3, p.interes_6, p.interes_9, p.interes_12, "
                + "t.almacenamiento, t.ram "
                + "FROM productos p "
                + "INNER JOIN tablets t ON p.id_producto = t.id_producto "
                + "WHERE p.tipo = 'Tablet'";

        try (Connection conexion = Conexion.conectar();
                PreparedStatement consulta = conexion.prepareStatement(sql);
                ResultSet resultado = consulta.executeQuery()) {

            cantidadProductos2 = 0;

            while (resultado.next()) {

                if (cantidadProductos2 >= producto2.length) {
                    producto2 = java.util.Arrays.copyOf(
                            producto2, producto2.length + 3);
                }

                double[] interes = new double[4];

                interes[0] = resultado.getDouble("interes_3");
                interes[1] = resultado.getDouble("interes_6");
                interes[2] = resultado.getDouble("interes_9");
                interes[3] = resultado.getDouble("interes_12");

                String nombre = resultado.getString("nombre");
                double precio = resultado.getDouble("precio");
                int stock = resultado.getInt("stock");

                int almacenamiento = Integer.parseInt(
                        resultado.getString("almacenamiento"));
                int ram = Integer.parseInt(
                        resultado.getString("ram"));

                producto2[cantidadProductos2] = new Tablet(
                        nombre, precio, stock, interes,
                        almacenamiento, ram);

                cantidadProductos2++;
            }

            System.out.println("Tablets cargadas desde MySQL: "
                    + cantidadProductos2);

        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error al cargar tablets: "
                    + e.getMessage());
        }
    }

    public void cargarLaptopsBD() {

        String sql = "SELECT p.id_producto, p.nombre, p.precio, p.stock, "
                + "p.interes_3, p.interes_6, p.interes_9, p.interes_12, "
                + "l.procesador, l.ram, l.almacenamiento, l.pulgadas "
                + "FROM productos p "
                + "INNER JOIN laptops l ON p.id_producto = l.id_producto "
                + "WHERE p.tipo = 'Laptop'";

        try (Connection conexion = Conexion.conectar();
                PreparedStatement consulta = conexion.prepareStatement(sql);
                ResultSet resultado = consulta.executeQuery()) {

            cantidadProductos3 = 0;

            while (resultado.next()) {

                if (cantidadProductos3 >= producto3.length) {
                    producto3 = java.util.Arrays.copyOf(
                            producto3, producto3.length + 3);
                }

                double[] interes = new double[4];

                interes[0] = resultado.getDouble("interes_3");
                interes[1] = resultado.getDouble("interes_6");
                interes[2] = resultado.getDouble("interes_9");
                interes[3] = resultado.getDouble("interes_12");

                String nombre = resultado.getString("nombre");
                double precio = resultado.getDouble("precio");
                int stock = resultado.getInt("stock");
                String cpu = resultado.getString("procesador");

                int ram = Integer.parseInt(
                        resultado.getString("ram"));
                int almacenamiento = Integer.parseInt(
                        resultado.getString("almacenamiento"));

                double pulgadas = resultado.getDouble("pulgadas");

                Laptop laptop = new Laptop(
                        nombre, precio, stock, interes,
                        almacenamiento, ram, cpu, pulgadas);

                laptop.setIdProducto(resultado.getInt("id_producto"));

                producto3[cantidadProductos3] = laptop;
                cantidadProductos3++;
            }

            System.out.println("Laptops cargadas desde MySQL: "
                    + cantidadProductos3);

        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error al cargar laptops: "
                    + e.getMessage());
        }
    }

    private boolean actualizarStockBD(
            String nombre, String tipo, int nuevoStock) {

        String sql = "UPDATE productos "
                + "SET stock = ? "
                + "WHERE nombre = ? AND tipo = ?";

        try (Connection conexion = Conexion.conectar();
                PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setInt(1, nuevoStock);
            consulta.setString(2, nombre);
            consulta.setString(3, tipo);

            int filasActualizadas = consulta.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Stock actualizado en MySQL.");
                return true;
            } else {
                System.out.println("No se encontro el producto en MySQL.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar stock: "
                    + e.getMessage());
            return false;
        }
    }

    private boolean eliminarProductoBD(String nombre, String tipo) {

        String sqlBuscar = "SELECT id_producto FROM productos "
                + "WHERE nombre = ? AND tipo = ?";

        String sqlEliminarCaracteristicas;

        if (tipo.equals("Celular")) {
            sqlEliminarCaracteristicas = "DELETE FROM celulares WHERE id_producto = ?";
        } else if (tipo.equals("Tablet")) {
            sqlEliminarCaracteristicas = "DELETE FROM tablets WHERE id_producto = ?";
        } else {
            sqlEliminarCaracteristicas = "DELETE FROM laptops WHERE id_producto = ?";
        }

        String sqlEliminarProducto = "DELETE FROM productos WHERE id_producto = ?";

        try (Connection conexion = Conexion.conectar()) {

            conexion.setAutoCommit(false);

            try {
                int idProducto;

                try (PreparedStatement consulta = conexion.prepareStatement(sqlBuscar)) {

                    consulta.setString(1, nombre);
                    consulta.setString(2, tipo);

                    try (ResultSet resultado = consulta.executeQuery()) {

                        if (!resultado.next()) {
                            System.out.println(
                                    "No se encontro el producto en MySQL.");
                            conexion.rollback();
                            return false;
                        }

                        idProducto = resultado.getInt("id_producto");

                        if (resultado.next()) {
                            System.out.println(
                                    "Hay productos con el mismo nombre. "
                                            + "No se elimino ninguno.");
                            conexion.rollback();
                            return false;
                        }
                    }
                }

                try (PreparedStatement consulta = conexion.prepareStatement(sqlEliminarCaracteristicas)) {

                    consulta.setInt(1, idProducto);
                    consulta.executeUpdate();
                }

                try (PreparedStatement consulta = conexion.prepareStatement(sqlEliminarProducto)) {

                    consulta.setInt(1, idProducto);

                    if (consulta.executeUpdate() != 1) {
                        throw new SQLException(
                                "No se pudo eliminar el producto.");
                    }
                }

                conexion.commit();
                System.out.println("Producto eliminado de MySQL.");
                return true;

            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }

}
