package com.budget.buddy;

import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private double gasto;
    private double ahorro;
    private String contraseña;
    private ArrayList<Articulo> articulos; // Lista de artículos

    public Usuario(String nombre, double gasto, String contraseña) {
        this.nombre = nombre;
        this.gasto = gasto;
        this.contraseña = contraseña;
        this.ahorro = 0.0;
        this.articulos = new ArrayList<>(); // Inicializar la lista de artículos
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getGasto() {
        return gasto;
    }

    public void setGasto(double gasto) {
        this.gasto = gasto;
    }

    public double getAhorro() {
        return ahorro;
    }

    public void setAhorro(double ahorro) {
        this.ahorro = ahorro;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public void agregarArticulo(Articulo articulo) {
        this.articulos.add(articulo);
    }
}