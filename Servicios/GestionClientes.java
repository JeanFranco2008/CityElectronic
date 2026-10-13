package Servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Configuracion.Conexion;
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

    public int registrarClienteBD(Cliente cliente) {

        String sql = "INSERT INTO clientes (nombre, edad) VALUES (?, ?)";

        try (Connection conexion = Conexion.conectar();
                PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setString(1, cliente.getNombre());
            consulta.setInt(2, cliente.getEdad());

            consulta.executeUpdate();

            // Obtener el ID generado por SQLite
            String sqlId = "SELECT last_insert_rowid()";

            try (PreparedStatement consultaId = conexion.prepareStatement(sqlId);
                    ResultSet resultado = consultaId.executeQuery()) {

                if (resultado.next()) {

                    int idCliente = resultado.getInt(1);

                    cliente.setIdCliente(idCliente);

                    System.out.println("Cliente registrado en SQLite.");

                    return idCliente;
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al registrar cliente: " + e.getMessage());
        }

        return -1;
    }

}