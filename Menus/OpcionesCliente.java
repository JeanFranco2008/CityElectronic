package Menus;

import java.util.Scanner;

public class OpcionesCliente {
    private int opcionCliente;

    public int getOpcionCliente() {
        return opcionCliente;
    }

    public void opcionesCliente(Scanner teclado) {
        System.out.println("Elija uno opcion de cliente: ");
        System.out.println("1. Ver informacion de productos");
        System.out.println("2. Agregar un producto al carrito");
        System.out.println("3. Eliminar un producto del carrito ");
        System.out.println("4. Pagar compra");
        System.out.println("5. Cerrar sesion como cliente ");
        opcionCliente = teclado.nextInt();
    }

    public void seleccionProducto(Scanner teclado) {
        System.out.println("=== TIPO DE PRODUCTO ===");
        System.out.println("1. Celular");
        System.out.println("2. Laptop");
        System.out.println("3. Tablet");
        System.out.println("4. Regresar");
        opcionCliente = teclado.nextInt();
    }
}
