import java.sql.ClientInfoStatus;
import java.util.Scanner;

import Model.Administrador;
import Model.Cliente;
import Menus.OpcionInicio;
import Menus.OpcionesAdministrador;
import Menus.OpcionesCliente;
import Servicios.GestionProductos;
import Servicios.GestionClientes;

public class Mainaa4 {
    public static void main(String[] args) {
        GestionClientes cliente = new GestionClientes();
        OpcionesCliente opcionC = new OpcionesCliente();
        OpcionInicio opcionOrigen = new OpcionInicio();
        OpcionesAdministrador opcion = new OpcionesAdministrador();
        GestionProductos agregar = new GestionProductos();
        Administrador admin = new Administrador();
        Scanner teclado = new Scanner(System.in);
        System.err.println("===== BIENVENIDO A CITYELECTRONIC =====");
        do {
            opcionOrigen.opcionesInicio(teclado);
            if (opcionOrigen.getOpcion() == 1) {
                admin.validacionAdmin(teclado);

                do {

                    // Se le muestra al administrador las 5 opciones que tiene
                    opcion.opcionesAdmin(teclado);
                    // Este es el proceso de la opción 1 "Agregar Producto"
                    if (opcion.getOpcionAdmin() == 1) {
                        do {
                            System.out.println("==== OPCION 1: AGREGAR PRODUCTO ====");
                            opcion.opcionUnoAdmin(teclado);
                            if (opcion.getOpcionAdmin() == 1) {
                                agregar.agregarCelular(teclado);
                            } else if (opcion.getOpcionAdmin() == 2) {
                                agregar.agregarLaptop(teclado);
                            } else if (opcion.getOpcionAdmin() == 3) {
                                agregar.agregarTablet(teclado);
                            }
                        } while (opcion.getOpcionAdmin() != 4);
                        // Este el proceso de la opcion 2 "Actualizar stock"
                    } else if ((opcion.getOpcionAdmin() == 2)) {
                        do {
                            System.out.println("==== OPCION 2: ACTUALIZAR PRODUCTO ====");
                            opcion.opcionUnoAdmin(teclado);
                            if (opcion.getOpcionAdmin() == 1) {
                                agregar.actualizarStockCelular(teclado);
                            } else if (opcion.getOpcionAdmin() == 2) {
                                agregar.actualizarStockLaptop(teclado);
                            } else if (opcion.getOpcionAdmin() == 3) {
                                agregar.actualizarStockTablet(teclado);
                            }
                        } while (opcion.getOpcionAdmin() != 4);

                    } else if (opcion.getOpcionAdmin() == 3) {
                        do {
                            System.out.println("==== OPCION 3: ELIMINAR PRODUCTO ====");
                            opcion.opcionUnoAdmin(teclado);
                            if (opcion.getOpcionAdmin() == 1) {
                                agregar.eliminarCelular(teclado);
                            } else if (opcion.getOpcionAdmin() == 2) {
                                agregar.eliminarLaptop(teclado);
                            } else if (opcion.getOpcionAdmin() == 3) {
                                agregar.eliminarTablet(teclado);
                            }
                        } while (opcion.getOpcionAdmin() == 4);
                    } else if (opcion.getOpcionAdmin() == 4) {
                        do {
                            System.out.println("==== OPCION 4: LISTAR PRODUCTO ====");
                            opcion.opcionUnoAdmin(teclado);
                            if (opcion.getOpcionAdmin() == 1) {
                                agregar.mostrarInformacionCelular(teclado);
                            } else if (opcion.getOpcionAdmin() == 2) {
                                agregar.mostrarInformacionLaptop(teclado);
                            } else if (opcion.getOpcionAdmin() == 3) {
                                agregar.mostrarInformacionTablet(teclado);
                            }

                        } while (opcion.getOpcionAdmin() == 4);

                    }
                } while (opcion.getOpcionAdmin() != 5);

            } else if (opcionOrigen.getOpcion() == 2) {
                do {
                    cliente.validacionCliente(teclado);
                    if (cliente.getPosicionCliente() < 5) {

                        opcionC.opcionesCliente(teclado);
                        if (opcionC.getOpcionCliente() == 1) {
                            do {
                                System.out.println("==== OPCION 1: VER INFORMACION DE PRODUCTOS ====");
                                opcionC.seleccionProducto(teclado);
                                opcionC.opcionesCliente(teclado);
                                if (opcionC.getOpcionCliente() == 1) {
                                    agregar.mostrarInformacionCelular(teclado);
                                } else if (opcionC.getOpcionCliente() == 2) {
                                    agregar.mostrarInformacionLaptop(teclado);
                                } else if (opcionC.getOpcionCliente() == 3) {
                                    agregar.mostrarInformacionTablet(teclado);
                                }
                            } while (opcionC.getOpcionCliente() != 4);

                        } else if (opcionC.getOpcionCliente() == 2) {
                            System.out.println("==== OPCION 2: HACER UNA COMPRA ====");

                        } else if (opcionC.getOpcionCliente() == 3) {
                            System.out.println("==== OPCION 3: ELIMINAR UNA COMPRA ====");

                        } else if (opcionC.getOpcionCliente() == 4) {
                            System.out.println("==== OPCION 4: PAGAR COMPRA ====");

                        }
                    } else {
                        System.out.println("Ya alcanzamos el limites de clientes");
                    }

                } while (opcionC.getOpcionCliente() != 5);
            }
        } while (opcionOrigen.getOpcion() != 4);

    }
}