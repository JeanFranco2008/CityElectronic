package Servicios;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import Model.CarritoCompras;
import Model.Producto;
import Model.Cliente;
import Interfaces.Pago;
import Configuracion.Conexion;

public class GestionVentas {

    public void pagarCompra(Cliente cliente, Scanner teclado) {
        CarritoCompras carrito = cliente.getCarrito();

        if (carrito.getCantidadProductos() == 0) {
            System.out.println("El carrito esta vacio.");
            return;
        }

        Producto[] productos = carrito.getProductos();
        int cantidad = carrito.getCantidadProductos();

        System.out.println("===== RESUMEN DE COMPRA =====");
        carrito.mostrarCarrito();

        // Verificar stock suficiente en el carrito
        for (int i = 0; i < cantidad; i++) {
            int unidadesSolicitadas = 0;

            for (int j = 0; j < cantidad; j++) {
                if (productos[i].getIdProducto() == productos[j].getIdProducto()) {
                    unidadesSolicitadas++;
                }
            }

            if (productos[i].getStock() < unidadesSolicitadas) {
                System.out.println("Stock insuficiente para: "
                        + productos[i].getNombre());
                System.out.println("No se puede completar la compra.");
                return;
            }
        }

        double subtotal = carrito.calcularTotal();
        double total = subtotal;
        double interesTotal = 0;
        int mesesElegidos = 0;
        int cuotas = 0;
        String tipoPago;
        Pago metodoPago = null;

        System.out.println();
        System.out.println("Subtotal: S/ "
                + String.format("%.2f", subtotal));
        System.out.println("===== FORMA DE PAGO =====");
        System.out.println("1. Al contado");
        System.out.println("2. A credito");

        int formaPago;

        do {
            System.out.print("Seleccione la forma de pago: ");
            formaPago = teclado.nextInt();

            if (formaPago != 1 && formaPago != 2) {
                System.out.println("Opcion incorrecta.");
            }
        } while (formaPago != 1 && formaPago != 2);

        if (formaPago == 2) {
            do {
                System.out.println("===== PLAZO DE CREDITO =====");
                System.out.println("1. 3 meses");
                System.out.println("2. 6 meses");
                System.out.println("3. 9 meses");
                System.out.println("4. 12 meses");
                System.out.print("Seleccione el plazo: ");

                mesesElegidos = teclado.nextInt();

                if (mesesElegidos < 1 || mesesElegidos > 4) {
                    System.out.println("Opcion incorrecta.");
                }
            } while (mesesElegidos < 1 || mesesElegidos > 4);

            int[] plazos = { 3, 6, 9, 12 };
            cuotas = plazos[mesesElegidos - 1];

            int indiceInteres = mesesElegidos - 1;

            for (int i = 0; i < cantidad; i++) {
                double porcentaje = productos[i].getInteres(indiceInteres);

                interesTotal += productos[i].getPrecio()
                        * porcentaje / 100;
            }

            total = subtotal + interesTotal;
            tipoPago = "Credito";

            System.out.println("Plazo: " + cuotas + " meses");
            System.out.println("Intereses: S/ "
                    + String.format("%.2f", interesTotal));
            System.out.println("Total a credito: S/ "
                    + String.format("%.2f", total));
            System.out.println("Cuota mensual: S/ "
                    + String.format("%.2f", total / cuotas));

        } else {
            System.out.println("Seleccione el metodo de pago:");
            System.out.println("1. Efectivo");
            System.out.println("2. Yape");

            int opcionPago;

            do {
                System.out.print("Ingrese una opcion: ");
                opcionPago = teclado.nextInt();

                if (opcionPago != 1 && opcionPago != 2) {
                    System.out.println("Opcion de pago no valida.");
                }
            } while (opcionPago != 1 && opcionPago != 2);

            if (opcionPago == 1) {
                metodoPago = new PagoEfectivo();
                tipoPago = "Efectivo";
            } else {
                metodoPago = new PagoYape();
                tipoPago = "Yape";
            }
        }

        System.out.println();
        System.out.println("¿Desea confirmar la compra?");
        System.out.println("1. Si");
        System.out.println("2. No");
        System.out.print("Seleccione una opcion: ");

        int confirmar = teclado.nextInt();

        if (confirmar != 1) {
            System.out.println(
                    "Compra cancelada. El carrito se conserva.");
            return;
        }

        // Guardar cliente, venta, detalles y stock en MySQL
        boolean guardado = registrarCompraBD(
                cliente, productos, cantidad, tipoPago,
                cuotas, subtotal, interesTotal, total);

        if (!guardado) {
            System.out.println(
                    "No se completo la compra. El carrito se conserva.");
            return;
        }

        // Actualizar stock en memoria después de guardar en MySQL
        for (int i = 0; i < cantidad; i++) {
            productos[i].setStock(productos[i].getStock() - 1);
        }

        if (metodoPago != null) {
            metodoPago.facturar();
        }

        carrito.vaciarCarrito();

        System.out.println("¡Compra realizada correctamente!");
        System.out.println("Total: S/ "
                + String.format("%.2f", total));
    }

    public void mostrarHistorialVentas() {

        String sql = "SELECT v.id_venta, c.nombre AS cliente, "
                + "v.fecha, v.tipo_pago, v.cuotas, "
                + "v.subtotal, v.interes_total, v.total, "
                + "p.nombre AS producto, dv.cantidad, "
                + "dv.precio_unitario "
                + "FROM ventas v "
                + "INNER JOIN clientes c ON v.id_cliente = c.id_cliente "
                + "INNER JOIN detalle_venta dv ON v.id_venta = dv.id_venta "
                + "INNER JOIN productos p ON dv.id_producto = p.id_producto "
                + "ORDER BY v.id_venta DESC, p.nombre";

        System.out.println();
        System.out.println("=================================");
        System.out.println("       HISTORIAL DE VENTAS       ");
        System.out.println("=================================");

        boolean hayVentas = false;
        int ultimaVenta = -1;

        try (Connection conexion = Conexion.conectar();
                PreparedStatement consulta = conexion.prepareStatement(sql);
                ResultSet resultado = consulta.executeQuery()) {

            while (resultado.next()) {

                hayVentas = true;

                int idVenta = resultado.getInt("id_venta");

                // Mostrar los datos generales una sola vez por venta
                if (idVenta != ultimaVenta) {

                    ultimaVenta = idVenta;

                    System.out.println();
                    System.out.println("----- VENTA N.° " + idVenta + " -----");
                    System.out.println("Cliente: "
                            + resultado.getString("cliente"));
                    System.out.println("Fecha: "
                            + resultado.getTimestamp("fecha"));
                    System.out.println("Tipo de pago: "
                            + resultado.getString("tipo_pago"));

                    int cuotas = resultado.getInt("cuotas");

                    if (cuotas > 0) {
                        System.out.println("Cuotas: " + cuotas + " meses");
                    }

                    System.out.printf("Subtotal: S/ %.2f%n",
                            resultado.getDouble("subtotal"));

                    System.out.printf("Intereses: S/ %.2f%n",
                            resultado.getDouble("interes_total"));

                    System.out.printf("Total pagado: S/ %.2f%n",
                            resultado.getDouble("total"));

                    System.out.println("Productos:");
                }

                // Mostrar cada producto de la venta
                String nombreProducto = resultado.getString("producto");
                int cantidad = resultado.getInt("cantidad");
                double precio = resultado.getDouble("precio_unitario");

                System.out.printf(
                        " - %s | Cantidad: %d | Precio unitario: S/ %.2f%n",
                        nombreProducto, cantidad, precio);
            }

            if (!hayVentas) {
                System.out.println("Todavia no hay ventas registradas.");
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar el historial: "
                    + e.getMessage());
        }

        System.out.println();
    }

    private boolean registrarCompraBD(
            Cliente cliente,
            Producto[] productos,
            int cantidad,
            String tipoPago,
            int cuotas,
            double subtotal,
            double interesTotal,
            double total) {

        String sqlCliente = "INSERT INTO clientes (nombre, edad) VALUES (?, ?)";

        String sqlVenta = "INSERT INTO ventas "
                + "(id_cliente, tipo_pago, cuotas, subtotal, "
                + "interes_total, total) VALUES (?, ?, ?, ?, ?, ?)";

        String sqlDetalle = "INSERT INTO detalle_venta "
                + "(id_venta, id_producto, cantidad, precio_unitario) "
                + "VALUES (?, ?, ?, ?)";

        String sqlStock = "UPDATE productos SET stock = stock - ? "
                + "WHERE id_producto = ? AND stock >= ?";

        try (Connection conexion = Conexion.conectar()) {

            conexion.setAutoCommit(false);

            try {
                // 1. Registrar cliente
                int idCliente;

                try (PreparedStatement consultaCliente = conexion.prepareStatement(
                        sqlCliente,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

                    consultaCliente.setString(1, cliente.getNombre());
                    consultaCliente.setInt(2, cliente.getEdad());
                    consultaCliente.executeUpdate();

                    try (ResultSet resultado = consultaCliente.getGeneratedKeys()) {

                        if (!resultado.next()) {
                            throw new SQLException(
                                    "No se obtuvo el ID del cliente.");
                        }

                        idCliente = resultado.getInt(1);
                    }
                }

                cliente.setIdCliente(idCliente);

                // 2. Registrar venta
                int idVenta;

                try (PreparedStatement consultaVenta = conexion.prepareStatement(
                        sqlVenta,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

                    consultaVenta.setInt(1, idCliente);
                    consultaVenta.setString(2, tipoPago);
                    consultaVenta.setInt(3, cuotas);
                    consultaVenta.setDouble(4, subtotal);
                    consultaVenta.setDouble(5, interesTotal);
                    consultaVenta.setDouble(6, total);

                    consultaVenta.executeUpdate();

                    try (ResultSet resultado = consultaVenta.getGeneratedKeys()) {

                        if (!resultado.next()) {
                            throw new SQLException(
                                    "No se obtuvo el ID de la venta.");
                        }

                        idVenta = resultado.getInt(1);
                    }
                }

                // 3. Agrupar unidades del mismo producto
                boolean[] procesado = new boolean[cantidad];

                for (int i = 0; i < cantidad; i++) {
                    if (procesado[i]) {
                        continue;
                    }

                    int idProducto = productos[i].getIdProducto();

                    if (idProducto <= 0) {
                        throw new SQLException(
                                "El producto no tiene un ID valido: "
                                        + productos[i].getNombre());
                    }

                    int unidades = 0;

                    for (int j = i; j < cantidad; j++) {
                        if (productos[j].getIdProducto() == idProducto) {
                            unidades++;
                            procesado[j] = true;
                        }
                    }

                    // 4. Descontar stock en MySQL
                    try (PreparedStatement consultaStock = conexion.prepareStatement(sqlStock)) {

                        consultaStock.setInt(1, unidades);
                        consultaStock.setInt(2, idProducto);
                        consultaStock.setInt(3, unidades);

                        int filas = consultaStock.executeUpdate();

                        if (filas == 0) {
                            throw new SQLException(
                                    "Stock insuficiente para: "
                                            + productos[i].getNombre());
                        }
                    }

                    // 5. Registrar detalle de venta
                    try (PreparedStatement consultaDetalle = conexion.prepareStatement(sqlDetalle)) {

                        consultaDetalle.setInt(1, idVenta);
                        consultaDetalle.setInt(2, idProducto);
                        consultaDetalle.setInt(3, unidades);
                        consultaDetalle.setDouble(
                                4, productos[i].getPrecio());

                        consultaDetalle.executeUpdate();
                    }
                }

                // Confirmar todos los cambios juntos
                conexion.commit();
                return true;

            } catch (SQLException e) {
                conexion.rollback();
                System.out.println(
                        "Error al guardar la compra: " + e.getMessage());
                return false;

            } finally {
                conexion.setAutoCommit(true);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error de conexion con MySQL: " + e.getMessage());
            return false;
        }
    }

    public void buscarVentaPorCodigo(Scanner teclado) {

        System.out.print("Ingrese el codigo de la venta: ");
        int codigo = teclado.nextInt();

        String sql = "SELECT v.id_venta, c.nombre AS cliente, "
                + "v.fecha, v.tipo_pago, v.cuotas, "
                + "v.subtotal, v.interes_total, v.total, "
                + "p.nombre AS producto, dv.cantidad, "
                + "dv.precio_unitario "
                + "FROM ventas v "
                + "INNER JOIN clientes c ON v.id_cliente = c.id_cliente "
                + "INNER JOIN detalle_venta dv ON v.id_venta = dv.id_venta "
                + "INNER JOIN productos p ON dv.id_producto = p.id_producto "
                + "WHERE v.id_venta = ?";

        boolean encontrada = false;

        try (Connection conexion = Conexion.conectar();
                PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setInt(1, codigo);

            try (ResultSet resultado = consulta.executeQuery()) {

                while (resultado.next()) {

                    if (!encontrada) {
                        encontrada = true;

                        System.out.println();
                        System.out.println("===== DETALLE DE LA VENTA =====");
                        System.out.println("Codigo: "
                                + resultado.getInt("id_venta"));
                        System.out.println("Cliente: "
                                + resultado.getString("cliente"));
                        System.out.println("Fecha: "
                                + resultado.getTimestamp("fecha"));
                        System.out.println("Tipo de pago: "
                                + resultado.getString("tipo_pago"));

                        int cuotas = resultado.getInt("cuotas");
                        if (cuotas > 0) {
                            System.out.println("Cuotas: " + cuotas + " meses");
                        }

                        System.out.printf("Subtotal: S/ %.2f%n",
                                resultado.getDouble("subtotal"));
                        System.out.printf("Intereses: S/ %.2f%n",
                                resultado.getDouble("interes_total"));
                        System.out.printf("Total: S/ %.2f%n",
                                resultado.getDouble("total"));

                        System.out.println("===== PRODUCTOS =====");
                    }

                    System.out.println(
                            "Producto: " + resultado.getString("producto")
                                    + " | Cantidad: " + resultado.getInt("cantidad")
                                    + " | Precio unitario: S/ "
                                    + String.format("%.2f",
                                            resultado.getDouble("precio_unitario")));
                }
            }

            if (!encontrada) {
                System.out.println("No se encontro una venta con el codigo "
                        + codigo + ".");
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la venta: " + e.getMessage());
        }
    }

}
