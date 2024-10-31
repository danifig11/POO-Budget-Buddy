package com.budget.buddy;

public class Articulo {
    private String nombre; 
    private double precio; 
    private String lugar; 
    private String fecha;
    
    public Articulo(String nombre, double precio, String lugar, String fecha) {
        this.nombre = nombre;
        this.precio = precio;
        this.lugar = lugar;
        this.fecha = fecha;
    }
    
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
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}