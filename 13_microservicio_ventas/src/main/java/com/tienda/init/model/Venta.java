package com.tienda.init.model;

public class Venta {

    private int codigo;
    private double facturacion;

    public Venta() {
    }

    public Venta(int codigo, double facturacion) {
        this.codigo = codigo;
        this.facturacion = facturacion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getFacturacion() {
        return facturacion;
    }

    public void setFacturacion(double facturacion) {
        this.facturacion = facturacion;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "codigo=" + codigo +
                ", facturacion=" + facturacion +
                '}';
    }
}
