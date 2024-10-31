package com.budget.buddy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            return "La contraseña debe tener al menos 8 caracteres, incluir una letra mayúscula y al menos un número.";
        }

        Usuario user = new Usuario(nombre, 0.0, contra);
        usuarios.add(user);
        GestorCSV.guardarUsuarios(usuarios, rutaArchivo);
        return "Usuario creado y guardado exitosamente!";
    }

    // Método para validar la contraseña
    private boolean validarContraseña(String contraseña) {
        // Expresión regular para al menos 8 caracteres, una mayúscula y un número
        String regex = "^(?=.[A-Z])(?=.\\d).{8,}$";
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
                GestorCSV.guardarUsuarios(usuarios, rutaArchivo);
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
        Random random = new Random();
        String recomendacion = recomendacionesAhorro.get(random.nextInt(recomendacionesAhorro.size()));
        return "Recomendación de ahorro: " + recomendacion;
    }

    public String verificarAlertasGasto(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                if (usuario.getGasto() > 500) {
                    if (alertasGasto.isEmpty()) {
                        return "No hay alertas de gasto disponibles.";
                    }
                    Random random = new Random();
                    String alerta = alertasGasto.get(random.nextInt(alertasGasto.size()));
                    return alerta;
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

    public String realizarCuestionario() {
        CuestionarioFinanzas cuestionario = new CuestionarioFinanzas();
        StringBuilder resultados = new StringBuilder();
        String[][] preguntas = cuestionario.getPreguntas();
        int puntaje = 0;

        for (int i = 0; i < preguntas.length; i++) {
            resultados.append("\nPregunta ").append(i + 1).append(": ").append(preguntas[i][0]).append("\n");
            resultados.append("Opciones:\n");
            resultados.append("1. ").append(preguntas[i][1]).append("\n");
            resultados.append("2. ").append(preguntas[i][2]).append("\n");
            resultados.append("3. ").append(preguntas[i][3]).append("\n");
            int respuestaCorrecta = Integer.parseInt(preguntas[i][4]);

            // Simulación de respuesta correcta para el ejemplo
            int respuestaSimulada = respuestaCorrecta; // Ajusta según sea necesario
            if (respuestaSimulada == respuestaCorrecta) {
                resultados.append("Respuesta correcta: ").append(respuestaSimulada).append("\n");
                puntaje++;
            } else {
                resultados.append("Respuesta incorrecta.\n");
            }
        }
        resultados.append("\nTu puntaje final es: ").append(puntaje).append("/").append(preguntas.length).append("\n");
        return resultados.toString();
    }
}

