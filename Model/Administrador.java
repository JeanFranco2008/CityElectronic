package Model;

import java.util.Scanner;

public class Administrador {
    private String correo = "admin@gmail.com";
    private String contraseña = "admin123";
    private String correoT;
    private String contraT;

    public void validacionAdmin(Scanner teclado) {
        System.out.println("==================");
        System.out.println("= INICIAR SESION =");
        System.out.println("==================");
        do {
            System.out.println("Ingrese su correo:");
            correoT = teclado.next();
            System.out.println("Ingrese su contraseña:");
            contraT = teclado.next();
            if (correo.equals(correoT) && contraseña.equals(contraT)) {
                System.out.println("Bienvenido administrador");
                System.out.println("");
            } else {
                System.out.println("Correo o contraseña incorrecta, vuelve a intentarlo");
            }
        } while (!correoT.equals(correo) || !contraT.equals(contraseña));
    }
}
