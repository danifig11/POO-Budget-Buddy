package com.budget.buddy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase Articulo
 * Representa un artículo o gasto asociado a un usuario en el sistema Budget Buddy.
 */
public class Articulo {
    private String nombre; // Nombre del artículo
    private double precio; // Precio del artículo
    private String lugar; // Lugar de compra
    private String fecha; // Fecha del gasto

    /**
     * Constructor de Articulo.
     * Asigna automáticamente la fecha actual.
     *
     * @param nombre Nombre del artículo.
     * @param precio Precio del artículo.
     * @param lugar Lugar de compra del artículo.
     */
    public Articulo(String nombre, double precio, String lugar) {
        this.nombre = nombre;
        this.precio = precio;
        this.lugar = lugar;
        this.fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Fecha actual
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public String getLugar() {
        return lugar;
    }
    
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    
    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", lugar='" + lugar + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
