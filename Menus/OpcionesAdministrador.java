package Menus;

import java.util.Scanner;

public class OpcionesAdministrador {

    private int opcionAdmin = 0;

    public void opcionesAdmin(Scanner teclado) {
        System.out.println("===== Elija una opcion de administrador: =====");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Actualizar Stock");
        System.out.println("3. Eliminar Producto");
        System.out.println("4. Listar Producto");
        System.out.println("5. Regresar");
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
