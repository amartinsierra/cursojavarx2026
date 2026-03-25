package com.tienda.init.model;

public class Producto {

    private int codigo;
    private double precio;
    private int stock;

    public Producto() {
    }

    public Producto(int codigo, double precio, int stock) {
        this.codigo = codigo;
        this.precio = precio;
        this.stock = stock;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
