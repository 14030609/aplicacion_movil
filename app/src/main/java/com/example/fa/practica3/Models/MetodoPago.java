package com.example.fa.practica3.Models;

/**
 * Created by susa on 10/12/17.
 */

public class MetodoPago {
    int id_metodpago;
    String nombre, decripcion;

    public MetodoPago(int id_metodpago, String nombre, String decripcion) {
        this.id_metodpago = id_metodpago;
        this.nombre = nombre;
        this.decripcion = decripcion;
    }

    public int getId_metodpago() {
        return id_metodpago;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDecripcion() {
        return decripcion;
    }
}
