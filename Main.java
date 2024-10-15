import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*UNIVERSIDAD DE VALLE DE GUATEMALA
 * CAMPUS CENTRAL
 * PROGRAMACION ORIENTADA A OBJETOS
 * ESTEBAN EMILIO CUMATZ QUINA, 24449
 * ADRIAN PENAGOS, 24914
 * DENIL PARADA, 24761
 * fIGUERA REYES, 24073
 * JOEL JOSUE NERIO ALONZO, 24253
 * PROYECTO PARTE 3
 */
public class Main {
    public static void main(String[] args){
        BufferedReader md = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Usuario> usuarios = GestorCSV.cargarUsuarios("usuarios.csv"); // Cargar usuarios desde CSV
        boolean logueado = false;
        String rutaArchivo = "usuarios.csv";

        Random random = new Random();

        // Lista de recomendaciones de ahorro
        List<String> recomendacionesAhorro = Arrays.asList(
            "Lleva un registro detallado de tus gastos para identificar áreas en las que puedas recortar.",
            "Establece un presupuesto mensual y apégate a él para evitar gastos innecesarios.",
            "Evita las compras impulsivas. Tómate un tiempo para pensar si realmente necesitas lo que estás por comprar.",
            "Revisa tus suscripciones mensuales y cancela las que no estés utilizando.",
            "Aprovecha las promociones y descuentos, pero solo si es algo que realmente necesitas.",
            "Usa efectivo en lugar de tarjetas para tener un mayor control sobre tus gastos diarios."
        );

        // Lista de alertas cuando se excede el límite de gasto
        List<String> alertasGasto = Arrays.asList(
            "¡Atención! Has sobrepasado tu límite de gasto. Considera recortar algunos gastos innecesarios.",
            "Tu nivel de gasto está por encima del límite establecido. Evita compras adicionales este mes.",
            "Estás gastando más de lo previsto. Intenta identificar áreas en las que puedas reducir gastos.",
            "¡Advertencia! Tu gasto está fuera de control. Considera reestructurar tu presupuesto.",
            "Cuidado, tus gastos están sobre el límite. Considera ahorrar más y gastar menos en el futuro."
        );
        while (true) { // Menú principal
            try {
                System.out.println("Ingrese una opción: \n1. Registrarse  \n2. Iniciar sesión \n3. Salir");
                int opcion = Integer.parseInt(md.readLine());
                
                if (opcion == 1) { // Crear un usuario
                    System.out.println("Ingrese su nombre de usuario:");
                    String nombre = md.readLine();
                    System.out.println("Ingrese su contraseña:");
                    String contra = md.readLine();
                    System.out.println("Ingrese su banco:");
                    String bancoNombre = md.readLine();
                    System.out.println("Ingrese la página web de su banco:");
                    String bancoPagina = md.readLine();
                    Banco banco = new Banco(bancoNombre, bancoPagina);
                    Usuario user = new Usuario(nombre, 0.0, contra, banco);  // Inicialmente con gasto 0
                    usuarios.add(user);
                    GestorCSV.guardarUsuarios(usuarios, rutaArchivo);  // Guardar al archivo CSV
                    System.out.println("Usuario creado y guardado exitosamente!");
                } 
                else if (opcion == 2) { // Iniciar sesión
                    System.out.println("Ingrese su nombre de usuario:");
                    String nombre = md.readLine();
                    System.out.println("Ingrese su contraseña:");
                    String contra = md.readLine();
                    
                    for (Usuario usuario : usuarios) { // Verificar usuario
                        if (usuario.getNombre().equals(nombre) && usuario.getContraseña().equals(contra)) {
                            System.out.println("Inicio de sesión exitoso!");
                            logueado = true;
                            break;
                        }
                    }

                    if (logueado) { // Menú después de iniciar sesión
                        boolean enMenu = true;
                        while (enMenu) {
                            System.out.println("\n------ Menú Principal ------");
                            System.out.println("1. Registrar ingresos y gastos");
                            System.out.println("2. Ver datos financieros");
                            System.out.println("3. Recomendación de ahorro");
                            System.out.println("4. Alertas de límite de gasto");
                            System.out.println("5. Cuestionario sobre finanzas");
                            System.out.println("6. Exportar y guardar datos");
                            System.out.println("7. Salir");
                            System.out.println("Elige una opción:");
                            int opcionMenu = Integer.parseInt(md.readLine());

                            switch (opcionMenu) {
                                case 1:
                                    System.out.println("Ingrese la cantidad de gastos:");
                                    double gasto = Double.parseDouble(md.readLine());
                                    System.out.println("Ingrese la cantidad de ahorro:");
                                    double ahorro = Double.parseDouble(md.readLine());
                                    for (Usuario usuario : usuarios) {
                                        if (usuario.getNombre().equals(nombre)) {
                                            usuario.setGasto(usuario.getGasto() + gasto);  // Sumar al gasto actual
                                            usuario.setAhorro(usuario.getAhorro() + ahorro);  // Sumar al ahorro actual
                                            break;
                                        }
                                    }
                                    GestorCSV.guardarUsuarios(usuarios, rutaArchivo);  // Guardar los cambios
                                    System.out.println("Gastos y ahorros registrados.");
                                    break;

                                case 2: // Ver datos financieros
                                    System.out.println("Reportes financieros:");
                                    for (Usuario usuario : usuarios) {
                                        if (usuario.getNombre().equals(nombre)) {
                                            System.out.println("Gasto total: " + usuario.getGasto());
                                            System.out.println("Ahorro total: " + usuario.getAhorro());
                                        }
                                    }
                                    break;

                                case 3: // Recomendación de ahorro
                                    String recomendacion = recomendacionesAhorro.get(random.nextInt(recomendacionesAhorro.size()));
                                    System.out.println("Recomendaciones de ahorro: " + recomendacion);
                                    break;
                                    
                                case 4: // Alertas de límite de gasto
                                    for (Usuario usuario : usuarios) {
                                        if (usuario.getNombre().equals(nombre)) {
                                            if (usuario.getGasto() > 500) {  // Ejemplo de límite
                                                String alerta = alertasGasto.get(random.nextInt(alertasGasto.size()));
                                                System.out.println(alerta);
                                            } else {
                                                System.out.println("Sus gastos están dentro del límite.");
                                            }
                                        }
                                    }
                                    break;
                                case 5: // Cuestionario sobre finanzas
                                    System.out.println("Bienvenido al cuestionario para verificar tus conocimientos sobre finanzas");
                                    CuestionarioFinanzas cuestionario = new CuestionarioFinanzas(); // se crea un objeto cuestionario
                                    String[][] preguntas = cuestionario.getPreguntas(); //se hace un array 2D para almacenar las preguntas
                                    int puntaje = 0; //el puntaje empieza siendo 0 de base
                                    for (int i = 0; i < preguntas.length; i++) { //se inicia un ciclo for 
                                        System.out.println("\nPregunta " + (i + 1) + ": " + preguntas[i][0]);
                                        //se imprimen todos las preguntas 
                                        System.out.println(preguntas[i][1]);
                                        System.out.println(preguntas[i][2]);
                                        System.out.println(preguntas[i][3]);
                            
                                        // Obtener la respuesta del usuario
                                        System.out.print("Elige la opción correcta (1-3): ");
                                        int respuesta = Integer.parseInt(md.readLine());
                                        // Verificar si la respuesta es correcta
                                        int respuestaCorrecta = Integer.parseInt(preguntas[i][4]);
                                        if (cuestionario.verificarRespuesta(respuesta, respuestaCorrecta)) {
                                            System.out.println("¡Correcto!");
                                            puntaje++;
                                        } else {
                                            System.out.println("Incorrecto.");
                                        }
                                    }
                                    // Mostrar el puntaje final
                                    System.out.println("\nTu puntaje final es: " + puntaje + "/" + preguntas.length);
                                    if (puntaje == preguntas.length) {
                                        System.out.println("¡Excelente! Eres un experto en finanzas.");
                                    } else if (puntaje >= preguntas.length / 2) {
                                        System.out.println("¡Bien hecho! Tienes buenos conocimientos en finanzas.");
                                    } else {
                                        System.out.println("Sigue aprendiendo sobre finanzas para mejorar tus habilidades.");
                                    }                                  
                                break;
                                
                                case 6: // Exportar y guardar datos
                                    System.out.println("Exportando y guardando sus datos financieros...");
                                    GestorCSV.guardarUsuarios(usuarios, rutaArchivo);  // Guardar los cambios
                                    System.out.println("Datos exportados correctamente.");
                                    break;
                                
                                case 7: // Salir y cerrar sesión
                                    System.out.println("Cerrando sesión...");
                                    enMenu = false;
                                    break;
                                    
                                default:
                                    System.out.println("Opción no válida, intenta de nuevo.");
                            }
                        }
                    } else {
                        System.out.println("Nombre de usuario o contraseña incorrectos.");
                    }
                } 
                else if (opcion == 3) { // Salir del programa
                    System.out.println("Saliendo...");
                    break;
                } else {
                    System.out.println("Opción no válida, intenta de nuevo.");
                }

            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
    }
}
