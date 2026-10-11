package Servicios;

import java.util.Scanner;

import Model.Cliente;

public class GestionClientes {
    private Cliente[] clientesRegistro = new Cliente[5];
    private int posicionCliente = 0;

    public Cliente[] getClientesRegistro() {
        return clientesRegistro;
    }

    public int getPosicionCliente() {
        return posicionCliente;
    }

    public void validacionCliente(Scanner teclado) {
        if (posicionCliente >= clientesRegistro.length) {
            System.out.println("Ya alcanzamos el limite de clientes.");
            return;
        }

        // Limpiar el salto de linea pendiente
        teclado.nextLine();

        System.out.println("==================");
        System.out.println("= INICIAR SESION =");
        System.out.println("==================");
        System.out.print("Ingrese su nombre: ");
        String nombre = teclado.nextLine();

        int edad;

        do {
            System.out.print("Ingrese su edad: ");
            edad = teclado.nextInt();

            if (edad < 18) {
                System.out.println(
                        "Lo sentimos " + nombre +
                                ", solo atendemos mayores de edad.");
            }
        } while (edad < 18);

        clientesRegistro[posicionCliente] = new Cliente(nombre, edad);

        posicionCliente++;

        System.out.println("Bienvenido " + nombre);
    }

}
