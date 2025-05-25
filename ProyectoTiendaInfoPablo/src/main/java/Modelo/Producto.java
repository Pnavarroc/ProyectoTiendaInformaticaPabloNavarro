package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Producto {
    private int id;
    private String nombre;
    private String marca;
    private double precio;

    public Producto() {
    }

    public Producto(int id, String nombre, String marca, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
    }


    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }







}
