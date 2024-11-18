package com.budget.buddy;

import java.util.ArrayList;

/**
 * Clase Usuario
 * Representa a un usuario en el sistema Budget Buddy.
 * Cada usuario tiene un ID único, un nombre, un gasto, un ahorro, una contraseña
 * y una lista de artículos asociados.
 */
public class Usuario {
    private int id; // ID único del usuario
    private String nombre; // Nombre del usuario
    private double gasto; // Gasto total del usuario
    private double ahorro; // Ahorro total del usuario
    private String contraseña; // Contraseña del usuario
    private ArrayList<Articulo> articulos; // Lista de artículos asociados al usuario

    /**
     * Constructor de Usuario.
     * 
     * @param nombre Nombre del usuario.
     * @param gasto Gasto inicial del usuario.
     * @param contraseña Contraseña del usuario.
     */
    public Usuario(String nombre, double gasto, String contraseña) {
        this.nombre = nombre;
        this.gasto = gasto;
        this.contraseña = contraseña;
        this.ahorro = 0.0;
        this.articulos = new ArrayList<>(); // Inicializar la lista de artículos
    }

    // Getters y Setters
    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
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