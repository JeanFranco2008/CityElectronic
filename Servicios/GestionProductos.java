package Servicios;

import java.util.Scanner;

import Model.Celular;
import Model.Laptop;
import Model.Producto;
import Model.Tablet;

public class GestionProductos {

    private Producto[] producto1;
    private Producto[] producto2;
    private Producto[] producto3;
    private int cantidadProductos1;
    private int cantidadProductos2;
    private int cantidadProductos3;

    public GestionProductos() {
        producto1 = new Celular[3];
        producto2 = new Tablet[3];
        producto3 = new Laptop[3];
        cantidadProductos1 = 0;
        cantidadProductos2 = 0;
        cantidadProductos3 = 0;

    }

    public void agregarCelular(Scanner teclado) {
        if (cantidadProductos1 >= producto1.length) {
            System.out.println("No hay espacio para más celulares.");
            return;
        }
        teclado.nextLine();
        System.out.println("Nombre del celular:");
        String nombre = teclado.nextLine();
        System.out.println("Precio del " + nombre + ": ");
        Double precio = teclado.nextDouble();
        System.out.println("Stock del " + nombre + ": ");
        int stock = teclado.nextInt();
        double[] interes = new double[4];
        for (int i = 0; i < 4; i++) {
            System.out.println("Ingrese el interes del mes " + ((i + 1) * 3) + ":");
            interes[i] = teclado.nextDouble();
        }
        System.out.println("Almacenamiento del " + nombre + ": ");
        int almacenamiento = teclado.nextInt();
        System.out.println("Ram del " + nombre + ": ");
        int ram = teclado.nextInt();
        System.out.println("Megapixeles del " + nombre + ": ");
        int megapixeles = teclado.nextInt();
        teclado.nextLine();
        producto1[cantidadProductos1] = new Celular(nombre, precio, stock, interes, almacenamiento, ram, megapixeles);
        cantidadProductos1++;
        System.out.println("Celular agregado exitosamente");
    }

    public void agregarTablet(Scanner teclado) {
        if (cantidadProductos2 >= producto2.length) {
            System.out.println("No hay espacio para más Tables.");
            return;
        }
        teclado.nextLine();
        System.out.println("Nombre de la Tablet");
        String nombre = teclado.nextLine();
        System.out.println("Precio del " + nombre + ": ");
        Double precio = teclado.nextDouble();
        System.out.println("Stock del " + nombre + ": ");
        int stock = teclado.nextInt();
        double[] interes = new double[4];
        for (int i = 0; i < 4; i++) {
            System.out.println("Ingrese el interes del mes " + ((i + 1) * 3) + ":");
            interes[i] = teclado.nextDouble();
        }
        System.out.println("Almacenamiento del " + nombre + ": ");
        int almacenamiento = teclado.nextInt();
        System.out.println("Ram del " + nombre + ": ");
        int ram = teclado.nextInt();
        teclado.nextLine();
        producto2[cantidadProductos2] = new Tablet(nombre, precio, stock, interes, almacenamiento, ram);
        cantidadProductos2++;
        System.out.println("Tablet agregado exitosamente");
    }

    public void agregarLaptop(Scanner teclado) {
        if (cantidadProductos3 >= producto3.length) {
            System.out.println("No hay espacio para más Laptops.");
            return;
        }
        teclado.nextLine();
        System.out.println("Nombre de la Tablet");
        String nombre = teclado.nextLine();
        System.out.println("Precio del " + nombre + ": ");
        Double precio = teclado.nextDouble();
        System.out.println("Stock del " + nombre + ": ");
        int stock = teclado.nextInt();
        double[] interes = new double[4];
        for (int i = 0; i < 4; i++) {
            System.out.println("Ingrese el interes del mes " + ((i + 1) * 3) + ":");
            interes[i] = teclado.nextDouble();
        }
        System.out.println("Almacenamiento del " + nombre + ": ");
        int almacenamiento = teclado.nextInt();
        System.out.println("Ram del " + nombre + ": ");
        int ram = teclado.nextInt();
        teclado.nextLine();
        System.out.println("CPU del " + nombre + ": ");
        String cpu = teclado.nextLine();
        System.out.println("Pulgadas del " + nombre + ": ");
        double pulgadas = teclado.nextDouble();
        teclado.nextLine();
        producto3[cantidadProductos3] = new Laptop(nombre, precio, stock, interes, almacenamiento, ram, cpu, pulgadas);
        cantidadProductos3++;
        System.out.println("Laptop agregado exitosamente");
    }

    public void actualizarStockCelular(Scanner teclado) {
        int aumentar;
        int desminuir;
        int buscar;
        int opcion;
        int stock;

        // Buscar el celular
        System.out.println("===== CELULARES REGISTRADOS =====");
        for (int i = 0; i < cantidadProductos1; i++) {
            System.out.println(
                    (i + 1) + ". Celular: " + producto1[i].getNombre() + " ===== Stock: " + producto1[i].getStock());

        }
        do {
            System.out.println("Ingrese la opcion de la tablet: ");
            buscar = teclado.nextInt();
            if (buscar == 0 || buscar > cantidadProductos1) {
                System.out.println("Opcion incorrecta, vuelve a intentarlo");
            }
        } while (buscar == 0 || buscar > cantidadProductos1);

        do {
            System.out.println("Actulizar stock:");
            System.out.println("1. Aumentar");
            System.out.println("2. Disminuir");
            System.out.println("Elige una opcion:");
            opcion = teclado.nextInt();
        } while (opcion != 1 && opcion != 2);
        stock = producto1[buscar - 1].getStock();
        if (opcion == 1) {
            do {
                System.out.println("Ingrese la cantidad a aumentar:");
                aumentar = teclado.nextInt();
                if (aumentar == 0) {
                    System.out.println("Datos incoherentes, vuelve a intentarlo");
                } else {

                    producto1[buscar - 1].setStock(stock + aumentar);
                }

            } while (aumentar == 0);

        } else if (opcion == 2) {
            do {
                System.out.println("Ingrese la cantidad a desminuir:");
                desminuir = teclado.nextInt();
                if (desminuir > stock || desminuir <= 0) {
                    System.out.println("Datos incoherentes, vuelve a intentarlo");
                } else {
                    producto1[buscar - 1].setStock(stock - desminuir);
                }
            } while (desminuir <= 0 || desminuir > stock);

        }
    }

    public void actualizarStockLaptop(Scanner teclado) {
        int aumentar;
        int desminuir;
        int buscar;
        int opcion;
        int stock;

        // Buscar el celular
        System.out.println("===== LAPTOPS REGISTRADAS =====");
        for (int i = 0; i < cantidadProductos3; i++) {
            System.out.println(
                    (i + 1) + ". Celular: " + producto3[i].getNombre() + " ===== Stock: " + producto3[i].getStock());

        }
        do {
            System.out.println("Ingrese la opcion de la Laptop: ");
            buscar = teclado.nextInt();
            if (buscar == 0 || buscar > cantidadProductos3) {
                System.out.println("Opcion incorrecta, vuelve a intentarlo");
            }
        } while (buscar == 0 || buscar > cantidadProductos3);

        do {
            System.out.println("Actulizar stock:");
            System.out.println("1. Aumentar");
            System.out.println("2. Disminuir");
            System.out.println("Elige una opcion:");
            opcion = teclado.nextInt();
        } while (opcion != 1 && opcion != 2);
        stock = producto3[buscar - 1].getStock();
        if (opcion == 1) {
            do {
                System.out.println("Ingrese la cantidad a aumentar:");
                aumentar = teclado.nextInt();
                if (aumentar == 0) {
                    System.out.println("Datos incoherentes, vuelve a intentarlo");
                } else {

                    producto3[buscar - 1].setStock(stock + aumentar);
                }

            } while (aumentar == 0);

        } else if (opcion == 2) {
            do {
                System.out.println("Ingrese la cantidad a desminuir:");
                desminuir = teclado.nextInt();
                if (desminuir > stock || desminuir <= 0) {
                    System.out.println("Datos incoherentes, vuelve a intentarlo");
                } else {
                    producto3[buscar - 1].setStock(stock - desminuir);
                }
            } while (desminuir <= 0 || desminuir > stock);

        }
    }

    public void actualizarStockTablet(Scanner teclado) {
        int aumentar;
        int desminuir;
        int buscar;
        int opcion;
        int stock;

        // Buscar el celular
        System.out.println("===== TABLETS REGISTRADAS =====");
        for (int i = 0; i < cantidadProductos2; i++) {
            System.out.println(
                    (i + 1) + ". Celular: " + producto2[i].getNombre() + " ===== Stock: " + producto2[i].getStock());

        }
        do {
            System.out.println("Ingrese la opcion de la tablet: ");
            buscar = teclado.nextInt();
            if (buscar == 0 || buscar > cantidadProductos2) {
                System.out.println("Opcion incorrecta, vuelve a intentarlo");
            }
        } while (buscar == 0 || buscar > cantidadProductos2);

        do {
            System.out.println("Actulizar stock:");
            System.out.println("1. Aumentar");
            System.out.println("2. Disminuir");
            System.out.println("Elige una opcion:");
            opcion = teclado.nextInt();
        } while (opcion != 1 && opcion != 2);
        stock = producto2[buscar - 1].getStock();
        if (opcion == 1) {
            do {
                System.out.println("Ingrese la cantidad a aumentar:");
                aumentar = teclado.nextInt();
                if (aumentar == 0) {
                    System.out.println("Datos incoherentes, vuelve a intentarlo");
                } else {

                    producto2[buscar - 1].setStock(stock + aumentar);
                }

            } while (aumentar == 0);

        } else if (opcion == 2) {
            do {
                System.out.println("Ingrese la cantidad a desminuir:");
                desminuir = teclado.nextInt();
                if (desminuir > stock || desminuir <= 0) {
                    System.out.println("Datos incoherentes, vuelve a intentarlo");
                } else {
                    producto2[buscar - 1].setStock(stock - desminuir);
                }
            } while (desminuir <= 0 || desminuir > stock);

        }
    }

    public void mostrarInformacionCelular(Scanner teclado) {
        int buscar;
        System.out.println("===== CELULARES REGISTRADOS =====");
        for (int i = 0; i < cantidadProductos1; i++) {
            System.out.println((i + 1) + ". Celular: " + producto1[i].getNombre());

        }
        do {
            System.out.println("Ingrese la opcion del celular: ");
            buscar = teclado.nextInt();
            if (buscar == 0 || buscar > cantidadProductos1) {
                System.out.println("Opcion incorrecta, vuelve a intentarlo");
            }
        } while (buscar == 0 || buscar > cantidadProductos1);
        producto1[buscar - 1].mostrarInformacion();

    }

    public void mostrarInformacionLaptop(Scanner teclado) {
        int buscar;
        System.out.println("===== LAPTOPS REGISTRADAS =====");
        for (int i = 0; i < cantidadProductos3; i++) {
            System.out.println((i + 1) + ". Celular: " + producto3[i].getNombre());
        }
        do {
            System.out.println("Ingrese la opcion de la Laptop: ");
            buscar = teclado.nextInt();
            if (buscar == 0 || buscar > cantidadProductos3) {
                System.out.println("Opcion incorrecta, vuelve a intentarlo");
            }
        } while (buscar == 0 || buscar > cantidadProductos1);
        producto3[buscar - 1].mostrarInformacion();

    }

    public void mostrarInformacionTablet(Scanner teclado) {
        int buscar;
        System.out.println("===== TABLETS REGISTRADAS =====");
        for (int i = 0; i < cantidadProductos2; i++) {
            System.out.println((i + 1) + ". Celular: " + producto2[i].getNombre());
        }
        do {
            System.out.println("Ingrese la opcion de la tablet: ");
            buscar = teclado.nextInt();
            if (buscar == 0 || buscar > cantidadProductos2) {
                System.out.println("Opcion incorrecta, vuelve a intentarlo");
            }
        } while (buscar == 0 || buscar > cantidadProductos2);
        producto2[buscar - 1].mostrarInformacion();

    }

    public void eliminarCelular(Scanner teclado) {
        int buscar;
        int opcion;
        if (cantidadProductos1 == 0) {
            System.out.println("No hay celulares registrados.");
            return;
        }

        System.out.println("===== ELIMINAR CELULAR =====");

        for (int i = 0; i < cantidadProductos1; i++) {
            System.out.println((i + 1) + ". "
                    + producto1[i].getNombre());
        }

        do {
            System.out.println("Ingrese la opcion del celular a eliminar:");
            buscar = teclado.nextInt();

            if (buscar < 1 || buscar > cantidadProductos1) {
                System.out.println("Opcion incorrecta.");
            }

        } while (buscar < 1 || buscar > cantidadProductos1);

        teclado.nextLine();

        System.out.println("¿Desea eliminar "
                + producto1[buscar - 1].getNombre() + "?");
        System.out.println("Seleccione una opcion para confirmar:");
        System.out.println("1. Si");
        System.out.println("2. No");
        opcion = teclado.nextInt();

        if (opcion == 2) {
            System.out.println("Eliminacion cancelada.");
            return;
        } else if (opcion == 1) {
            for (int i = buscar - 1; i < cantidadProductos1 - 1; i++) {
                producto1[i] = producto1[i + 1];
            }
            producto1[cantidadProductos1 - 1] = null;

            cantidadProductos1--;

            System.out.println("Celular eliminado correctamente.");
        }

    }

    public void eliminarLaptop(Scanner teclado) {
        int buscar;
        int opcion;
        if (cantidadProductos3 == 0) {
            System.out.println("No hay laptops registradas.");
            return;
        }

        System.out.println("===== ELIMINAR LAPTOP =====");

        for (int i = 0; i < cantidadProductos3; i++) {
            System.out.println((i + 1) + ". "
                    + producto3[i].getNombre());
        }

        do {
            System.out.println("Ingrese la opcion de la Laptop a eliminar:");
            buscar = teclado.nextInt();

            if (buscar < 1 || buscar > cantidadProductos3) {
                System.out.println("Opcion incorrecta.");
            }

        } while (buscar < 1 || buscar > cantidadProductos3);

        teclado.nextLine();

        System.out.println("¿Desea eliminar "
                + producto3[buscar - 1].getNombre() + "?");
        System.out.println("Seleccione una opcion para confirmar:");
        System.out.println("1. Si");
        System.out.println("2. No");
        opcion = teclado.nextInt();

        if (opcion == 2) {
            System.out.println("Eliminacion cancelada.");
            return;
        } else if (opcion == 1) {
            for (int i = buscar - 1; i < cantidadProductos3 - 1; i++) {
                producto3[i] = producto3[i + 1];
            }
            producto3[cantidadProductos3 - 1] = null;

            cantidadProductos3--;

            System.out.println("Celular eliminado correctamente.");
        }

    }

    public void eliminarTablet(Scanner teclado) {
        int buscar;
        int opcion;
        if (cantidadProductos2 == 0) {
            System.out.println("No hay tablets registradas.");
            return;
        }

        System.out.println("===== ELIMINAR LAPTOP =====");

        for (int i = 0; i < cantidadProductos2; i++) {
            System.out.println((i + 1) + ". "
                    + producto2[i].getNombre());
        }

        do {
            System.out.println("Ingrese la opcion de la Laptop a eliminar:");
            buscar = teclado.nextInt();

            if (buscar < 1 || buscar > cantidadProductos2) {
                System.out.println("Opcion incorrecta.");
            }

        } while (buscar < 1 || buscar > cantidadProductos2);

        teclado.nextLine();

        System.out.println("¿Desea eliminar "
                + producto2[buscar - 1].getNombre() + "?");
        System.out.println("Seleccione una opcion para confirmar:");
        System.out.println("1. Si");
        System.out.println("2. No");
        opcion = teclado.nextInt();

        if (opcion == 2) {
            System.out.println("Eliminacion cancelada.");
            return;
        } else if (opcion == 1) {
            for (int i = buscar - 1; i < cantidadProductos2 - 1; i++) {
                producto2[i] = producto2[i + 1];
            }
            producto2[cantidadProductos2 - 1] = null;

            cantidadProductos2--;

            System.out.println("Celular eliminado correctamente.");
        }

    }
}
