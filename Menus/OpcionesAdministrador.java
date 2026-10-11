package Menus;

import java.util.Scanner;

public class OpcionesAdministrador {

    private int opcionAdmin = 0;

    public void opcionesAdmin(Scanner teclado) {
        System.out.println("===== MENU ADMINISTRADOR =====");
        System.out.println("1. Agregar producto");
        System.out.println("2. Actualizar stock");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Buscar producto");
        System.out.println("5. Historial de ventas");
        System.out.println("6. Buscar venta por codigo");
        System.out.println("7. Regresar");
        opcionAdmin = teclado.nextInt();
    }

    public void opcionUnoAdmin(Scanner teclado) {
        System.out.println("=== TIPO DE PRODUCTO ===");
        System.out.println("1. Celular");
        System.out.println("2. Laptop");
        System.out.println("3. Tablet");
        System.out.println("4. Regresar");
        opcionAdmin = teclado.nextInt();
    }

    public int getOpcionAdmin() {
        return opcionAdmin;
    }
}
