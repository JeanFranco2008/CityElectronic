
import java.util.Scanner;

import Model.Administrador;

import Menus.OpcionInicio;
import Menus.OpcionesAdministrador;
import Menus.OpcionesCliente;
import Servicios.GestionProductos;
import Servicios.GestionClientes;
import Model.CarritoCompras;
import Model.Cliente;
import Model.Producto;
import Servicios.GestionVentas;

public class Mainaa4 {
    public static void main(String[] args) {

        GestionClientes cliente = new GestionClientes();
        OpcionesCliente opcionC = new OpcionesCliente();
        OpcionInicio opcionOrigen = new OpcionInicio();
        OpcionesAdministrador opcion = new OpcionesAdministrador();
        GestionProductos agregar = new GestionProductos();
        GestionVentas ventas = new GestionVentas();
        Administrador admin = new Administrador();
        Scanner teclado = new Scanner(System.in);
        System.err.println("===== BIENVENIDO A CITYELECTRONIC =====");
        do {
            // ADMINISTRADOR
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
                        } while (opcion.getOpcionAdmin() != 4);
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

                        } while (opcion.getOpcionAdmin() != 4);

                    } else if (opcion.getOpcionAdmin() == 5) {

                        System.out.println("==== OPCION 5: HISTORIAL DE VENTAS ====");
                        ventas.mostrarHistorialVentas();

                    } else if (opcion.getOpcionAdmin() == 6) {

                        System.out.println("==== OPCION 6: BUSCAR VENTA POR CODIGO ====");
                        ventas.buscarVentaPorCodigo(teclado);

                    }
                } while (opcion.getOpcionAdmin() != 7);

            } else if (opcionOrigen.getOpcion() == 2) {
                // CLIENTE

                cliente.validacionCliente(teclado);

                // Verificar si se registro un cliente
                if (cliente.getPosicionCliente() == 0) {
                    continue;
                }

                int posicionActual = cliente.getPosicionCliente() - 1;

                Cliente clienteActual = cliente.getClientesRegistro()[posicionActual];

                CarritoCompras carrito = clienteActual.getCarrito();
                do {
                    if (cliente.getPosicionCliente() < 5) {
                        opcionC.opcionesCliente(teclado);
                        if (opcionC.getOpcionCliente() == 1) {
                            do {
                                System.out.println("==== OPCION 1: VER INFORMACION DE PRODUCTOS ====");
                                opcionC.seleccionProducto(teclado);
                                if (opcionC.getOpcionProducto() == 1) {
                                    agregar.mostrarInformacionCelular(teclado);
                                } else if (opcionC.getOpcionProducto() == 2) {
                                    agregar.mostrarInformacionLaptop(teclado);
                                } else if (opcionC.getOpcionProducto() == 3) {
                                    agregar.mostrarInformacionTablet(teclado);
                                }
                            } while (opcionC.getOpcionProducto() != 4);

                        } else if (opcionC.getOpcionCliente() == 2) {

                            do {
                                System.out.println("==== OPCION 2: AGREGAR PRODUCTO AL CARRITO ====");
                                opcionC.seleccionProducto(teclado);

                                Producto seleccionado = null;

                                if (opcionC.getOpcionProducto() == 1) {
                                    seleccionado = agregar.seleccionarCelular(teclado);
                                } else if (opcionC.getOpcionProducto() == 2) {
                                    seleccionado = agregar.seleccionarLaptop(teclado);
                                } else if (opcionC.getOpcionProducto() == 3) {
                                    seleccionado = agregar.seleccionarTablet(teclado);
                                } else if (opcionC.getOpcionProducto() != 4) {
                                    System.out.println("Opcion incorrecta.");
                                }

                                if (seleccionado != null) {
                                    if (seleccionado.getStock() > 0) {
                                        carrito.agregarProducto(seleccionado);
                                    } else {
                                        System.out.println("Producto sin stock disponible.");
                                    }
                                }

                            } while (opcionC.getOpcionProducto() != 4);

                        } else if (opcionC.getOpcionCliente() == 3) {

                            System.out.println("==== OPCION 3: ELIMINAR PRODUCTO DEL CARRITO ====");

                            carrito.mostrarCarrito();

                            if (carrito.getCantidadProductos() > 0) {
                                System.out.print("Ingrese el numero del producto que desea eliminar: ");
                                int posicion = teclado.nextInt();

                                carrito.eliminarProducto(posicion - 1);
                            }
                        } else if (opcionC.getOpcionCliente() == 4) {
                            System.out.println("==== OPCION 4: PAGAR COMPRA ====");
                            ventas.pagarCompra(clienteActual, teclado);
                        }
                    } else {
                        System.out.println("Ya alcanzamos el limites de clientes");
                    }

                } while (opcionC.getOpcionCliente() != 5);
            }
        } while (opcionOrigen.getOpcion() != 3);

    }
}