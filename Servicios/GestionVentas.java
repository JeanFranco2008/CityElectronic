package Servicios;

import java.util.Scanner;

import Interfaces.Pago;

public class GestionVentas {
    int buscar = 0;
    int opcion = 0;
    int opcionMetodo = 0;
    int stock;
    int posicion;

    GestionProductos productos;
    GestionClientes clientes;

    public GestionVentas(GestionProductos productos,
            GestionClientes clientes) {
        this.productos = productos;
        this.clientes = clientes;
    }
}
