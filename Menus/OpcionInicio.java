package Menus;

import java.util.Scanner;

public class OpcionInicio {
    private int opcion = 0;

    public int getOpcion() {
        return opcion;
    }

    public void opcionesInicio(Scanner teclado) {
        System.out.println("===== Si usted es admnistrador o cliente, elija una opción: =====");
        System.out.println("1. Administrador");
        System.out.println("2. Cliente");
        System.out.println("3. Vendedor");
        System.out.println("4. Salir");
        opcion = teclado.nextInt();
    }

}
