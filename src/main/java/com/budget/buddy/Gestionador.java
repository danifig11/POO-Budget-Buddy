package com.budget.buddy;

import java.util.ArrayList;
import java.util.List;

public class Gestionador {
    private ArrayList<Usuario> usuarios;
    private String rutaArchivo;
    private List<String> recomendacionesAhorro;
    private List<String> alertasGasto;

    public Gestionador(ArrayList<Usuario> usuarios, String rutaArchivo, List<String> recomendacionesAhorro, List<String> alertasGasto) {
        this.usuarios = usuarios;
        this.rutaArchivo = rutaArchivo;
        this.recomendacionesAhorro = recomendacionesAhorro;
        this.alertasGasto = alertasGasto;
    }
    
    public String registrarUsuario(String nombre, String contra) {
        if (!validarContraseña(contra)) {
            return "Error: La contraseña debe tener al menos 8 caracteres y una letra mayúscula.";
        }
    
        Usuario user = new Usuario(nombre, 0.0, contra);
        
        // Add the new user to the in-memory list and save to CSV
        usuarios.add(user);
        
        if (GestorCSV.guardarUsuarios(usuarios, rutaArchivo)) {
            return "Usuario creado exitosamente.";
        } else {
            return "Error: No se pudo crear el usuario.";
        }
    }

    private boolean validarContraseña(String contraseña) {
        String regex = "^(?=.*[A-Z]).{8,}$";
        return contraseña.matches(regex);
    }

    public String iniciarSesion(String nombre, String contra) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getContraseña().equals(contra)) {
                return "Inicio de sesión exitoso!";
            }
        }
        return "Nombre de usuario o contraseña incorrectos.";
    }

    public String registrarIngresosGastos(String nombre, double gasto, double ahorro) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                usuario.setGasto(usuario.getGasto() + gasto);
                usuario.setAhorro(usuario.getAhorro() + ahorro);
                GestorCSV.guardarUsuarios(usuarios, rutaArchivo);  // Save only the current list
                return "Gastos y ahorros registrados.";
            }
        }
        return "Usuario no encontrado.";
    }

    public String verDatosFinancieros(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return "Gasto total: " + usuario.getGasto() + ", Ahorro total: " + usuario.getAhorro();
            }
        }
        return "Usuario no encontrado.";
    }

    public String obtenerRecomendacionAhorro() {
        if (recomendacionesAhorro.isEmpty()) {
            return "No hay recomendaciones de ahorro disponibles.";
        }
        return "Recomendación de ahorro: " + recomendacionesAhorro.get(0);
    }

    public String verificarAlertasGasto(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                if (usuario.getGasto() > 500) {
                    if (alertasGasto.isEmpty()) {
                        return "No hay alertas de gasto disponibles.";
                    }
                    return alertasGasto.get(0);
                } else {
                    return "Sus gastos están dentro del límite.";
                }
            }
        }
        return "Usuario no encontrado.";
    }

    public String exportarGuardarDatos() {
        GestorCSV.guardarUsuarios(usuarios, rutaArchivo);
        return "Datos exportados correctamente.";
    }

    public String registrarUsuarioConId(int id, String nombre, String contra) {
        if (!validarContraseña(contra)) {
            return "Error: La contraseña debe tener al menos 8 caracteres y una letra mayúscula.";
        }
    
        Usuario user = new Usuario(nombre, 0.0, contra);
        user.setId(id); // Asignar el ID único al usuario
    
        // Agregar el nuevo usuario a la lista en memoria y guardar en el CSV
        usuarios.add(user);
    
        if (GestorCSV.guardarUsuarios(usuarios, rutaArchivo)) {
            return "Usuario creado exitosamente.";
        } else {
            return "Error: No se pudo crear el usuario.";
        }
    }
}