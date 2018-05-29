package com.example.fa.practica3.Models;

/**
 * Created by susa on 10/12/17.
 */

public class ordenCompra
{
    int id;
    String fecha;
    double total;

    public ordenCompra(int id, String fecha, double total) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
