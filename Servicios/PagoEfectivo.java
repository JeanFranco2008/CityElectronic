package Servicios;

import Interfaces.Pago;

public class PagoEfectivo implements Pago {

    @Override
    public void facturar() {
        System.out.println("Factura generada para pago con efectivo.");
    }
}