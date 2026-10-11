
package Servicios;

import java.util.Scanner;
import Model.CarritoCompras;
import Model.Producto;
import Interfaces.Pago;

public class GestionVentas {

    public void pagarCompra(CarritoCompras carrito, Scanner teclado) {

        if (carrito.getCantidadProductos() == 0) {
            System.out.println("El carrito esta vacio.");
            return;
        }

        Producto[] productos = carrito.getProductos();
        int cantidad = carrito.getCantidadProductos();

        System.out.println("===== RESUMEN DE COMPRA =====");
        carrito.mostrarCarrito();

        // Verificar que exista stock suficiente
        for (int i = 0; i < cantidad; i++) {
            int unidadesSolicitadas = 0;

            for (int j = 0; j < cantidad; j++) {
                if (productos[i] == productos[j]) {
                    unidadesSolicitadas++;
                }
            }

            if (productos[i].getStock() < unidadesSolicitadas) {
                System.out.println(
                        "Stock insuficiente para: " + productos[i].getNombre());
                System.out.println("No se puede completar la compra.");
                return;
            }
        }

        double subtotal = carrito.calcularTotal();
        double total = subtotal;
        Pago metodoPago = null;

        System.out.println();
        System.out.println("Subtotal: S/ " + String.format("%.2f", subtotal));
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

        int meses = 0;
        double interesTotal = 0;

        if (formaPago == 2) {

            do {
                System.out.println("===== PLAZO DE CREDITO =====");
                System.out.println("1. 3 meses");
                System.out.println("2. 6 meses");
                System.out.println("3. 9 meses");
                System.out.println("4. 12 meses");
                System.out.print("Seleccione el plazo: ");

                meses = teclado.nextInt();

                if (meses < 1 || meses > 4) {
                    System.out.println("Opcion incorrecta.");
                }
            } while (meses < 1 || meses > 4);

            int indiceInteres = meses - 1;
            interesTotal = 0;

            for (int i = 0; i < cantidad; i++) {
                double porcentaje = productos[i].getInteres(indiceInteres);

                interesTotal += productos[i].getPrecio()
                        * porcentaje / 100;
            }

            total = subtotal + interesTotal;

            int[] plazos = { 3, 6, 9, 12 };
            int mesesElegidos = plazos[meses - 1];

            System.out.println("Plazo: " + mesesElegidos + " meses");
            System.out.println("Intereses: S/ "
                    + String.format("%.2f", interesTotal));
            System.out.println("Total a credito: S/ "
                    + String.format("%.2f", total));
            System.out.println("Cuota mensual: S/ "
                    + String.format("%.2f", total / mesesElegidos));

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
            } else {
                metodoPago = new PagoYape();
            }
        }

        System.out.println();
        System.out.println("¿Desea confirmar la compra?");
        System.out.println("1. Si");
        System.out.println("2. No");
        System.out.print("Seleccione una opcion: ");

        int confirmar = teclado.nextInt();

        if (confirmar != 1) {
            System.out.println("Compra cancelada. El carrito se conserva.");
            return;
        }
        if (formaPago == 1 && metodoPago != null) {
            metodoPago.facturar();
        }

        // Descontar una unidad por cada entrada del carrito
        for (int i = 0; i < cantidad; i++) {
            productos[i].setStock(productos[i].getStock() - 1);
        }

        carrito.vaciarCarrito();

        System.out.println("¡Compra realizada correctamente!");
        System.out.println("Total pagado o financiado: S/ "
                + String.format("%.2f", total));
    }
}
