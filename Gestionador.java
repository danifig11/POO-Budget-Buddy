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
        Usuario user = new Usuario(nombre, 0.0, contra);
        usuarios.add(user);
        GestorCSV.guardarUsuarios(usuarios, rutaArchivo);
        return "Usuario creado y guardado exitosamente!";
        // Ejemplo de retorno: "Usuario creado y guardado exitosamente!"
    }

    public String iniciarSesion(String nombre, String contra) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getContraseña().equals(contra)) {
                return "Inicio de sesión exitoso!";
            }
        }
        return "Nombre de usuario o contraseña incorrectos.";
        // Ejemplo de retorno: "Inicio de sesión exitoso!" o "Nombre de usuario o contraseña incorrectos."
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
        // Ejemplo de retorno: "Gastos y ahorros registrados."
    }

    public String verDatosFinancieros(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return "Gasto total: " + usuario.getGasto() + ", Ahorro total: " + usuario.getAhorro();
            }
        }
        return "Usuario no encontrado.";
        // Ejemplo de retorno: "Gasto total: 1000.0, Ahorro total: 500.0"
    }

    public String obtenerRecomendacionAhorro() {
        Random random = new Random();
        String recomendacion = recomendacionesAhorro.get(random.nextInt(recomendacionesAhorro.size()));
        return "Recomendaciones de ahorro: " + recomendacion;
        // Ejemplo de retorno: "Recomendaciones de ahorro: Lleva un registro detallado de tus gastos para identificar áreas en las que puedas recortar."
    }

    public String verificarAlertasGasto(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getGasto() > 500) {
                Random random = new Random();
                String alerta = alertasGasto.get(random.nextInt(alertasGasto.size()));
                return alerta;
            } else if (usuario.getNombre().equals(nombre)) {
                return "Sus gastos están dentro del límite.";
            }
        }
        return "Usuario no encontrado.";
        // Ejemplo de retorno: "¡Atención! Has sobrepasado tu límite de gasto. Considera recortar algunos gastos innecesarios."
    }

    public String exportarGuardarDatos() {
        GestorCSV.guardarUsuarios(usuarios, rutaArchivo);
        return "Datos exportados correctamente.";
        // Ejemplo de retorno: "Datos exportados correctamente."
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
            if (respuestaCorrecta == 1) { // Simulando que la respuesta correcta es siempre la primera opción
                resultados.append("Respuesta correcta: 1\n");
                puntaje++;
            } else {
                resultados.append("Respuesta incorrecta.\n");
            }
        }
        resultados.append("\nTu puntaje final es: ").append(puntaje).append("/").append(preguntas.length).append("\n");
        return resultados.toString();
        // Ejemplo de retorno: "Tu puntaje final es: 3/5"
    }
}
