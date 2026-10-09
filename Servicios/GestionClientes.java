package Servicios;

import java.util.Scanner;

import Model.Cliente;

public class GestionClientes {
    private Cliente[] clientesRegistro = new Cliente[2];
    private int posicionCliente = 0;

    public int getPosicionCliente() {
        return posicionCliente;
    }

    public void validacionCliente(Scanner teclado) {
        if (posicionCliente < 2) {
            int edad;
            System.out.println("==================");
            System.out.println("= INICIAR SESION =");
            System.out.println("==================");
            System.out.print("Ingrese su nombre: ");

            String nombre = teclado.nextLine();
            teclado.nextLine();

            do {

                System.out.print("Ingrese su edad: ");
                edad = teclado.nextInt();
                if (edad < 18) {
                    System.out.println("Lo sentimos " + nombre + " solo atendemos mayores de edad.");
                } else {
                    System.out.println("Bienvenido " + nombre);
                    if (posicionCliente < 2) {
                        clientesRegistro[posicionCliente] = new Cliente(nombre, edad);
                        posicionCliente++;
                    }

                }
            } while (edad < 18);
        }

    }

    public void MostrarCliente() {

    }

}
