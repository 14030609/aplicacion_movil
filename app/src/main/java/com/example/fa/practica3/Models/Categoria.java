package com.example.fa.practica3.Models;

/**
 * Created by adrian on 14/11/17.
 */

public class Categoria {
    int id_categoria;
    String nombre;

    public Categoria(int id_categoria, String nombre) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
    }

    public int getId() {
        return id_categoria;
    }

    public void setId(int id) {
        this.id_categoria = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
