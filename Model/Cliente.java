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

    public void mostrarDatoscliente() {
        System.out.println("==== CLIENTE ====");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
    }

}
