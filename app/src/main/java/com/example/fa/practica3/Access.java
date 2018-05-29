package com.example.fa.practica3;

import com.example.fa.practica3.Models.Cupones;
import com.example.fa.practica3.Models.MetodoPago;

/**
 * Created by susa on 9/12/17.
 */

final public class Access {

    static String idCompra, idCompraInProducts;
    static Cupones cuponSelect;
    static  boolean comprar;
    static String token;
    static String idCliente;
    static String direccion = "192.168.100.106:8000";
    public Access(String idCliente)
    {
        this.idCliente = idCliente;
    }
    public Access(String token, String idCliente)
    {
        this.token = token;
        this.idCliente = idCliente;
    }

    public static String getIdCompraInProducts() {
        return idCompraInProducts;
    }

    public static String getToken() {
        return token;
    }

    public static String getIdCliente() {
        return idCliente;
    }

    public static String getDireccion() {
        return direccion;
    }

    public static void setToken(String token) {
        Access.token = token;
    }

    public static void setIdCliente(String idCliente) {
        Access.idCliente = idCliente;
    }

    public static void setDireccion(String direccion) {
        Access.direccion = direccion;
    }

    public static boolean isComprar() {
        return comprar;
    }

    public static void setComprar(boolean comprar) {
        Access.comprar = comprar;
    }

    public static Cupones getCuponSelect() {
        return cuponSelect;
    }

    public static void setCuponSelect(Cupones cuponSelect) {
        Access.cuponSelect = cuponSelect;
    }

    public static String getIdCompra() {
        return idCompra;
    }

    public static void setIdCompra(String idCompra) {
        Access.idCompra = idCompra;
    }

    public static void setIdCompraInProducts(String idCompraInProducts) {
        Access.idCompraInProducts = idCompraInProducts;
    }

}
