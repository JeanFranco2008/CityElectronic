package Model;

import java.util.Scanner;

public class Cliente {
    private String nombre;
    private int edad;

    public Cliente(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void validacionCliente(Scanner teclado) {
        System.out.println("==================");
        System.out.println("= INICIAR SESION =");
        System.out.println("==================");
        System.out.print("Ingrese su nombre: ");
        nombre = teclado.nextLine();
        teclado.nextLine();

        do {

            System.out.print("Ingrese su edad: ");
            edad = teclado.nextInt();
            if (edad < 18) {
                System.out.println("Lo sentimos " + nombre + " solo atendemos mayores de edad.");
            } else {
                System.out.println("Bienvenido " + nombre);
            }
        } while (edad < 18);

    }
}
