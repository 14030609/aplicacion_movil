package com.example.fa.practica3.Models;

import android.renderscript.ScriptIntrinsicYuvToRGB;

/**
 * Created by adrian on 14/11/17.
 */

public class Producto {

    private int id;
    private String name;
    private double price;
    private String image;

    /**
     * No args constructor for use in serialization
     *
     */
    public Producto() {
    }

    /**
     *
     * @param id
     * @param price
     * @param name
     * @param image
     */
    public Producto(int id, String name, double price, String image) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
