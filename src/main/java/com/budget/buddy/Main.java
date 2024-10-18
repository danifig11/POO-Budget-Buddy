package com.budget.buddy;
import java.io.BufferedReader;   //se importan las librerias que se utilizaran
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/* UNIVERSIDAD DE VALLE DE GUATEMALA
 * CAMPUS CENTRAL
 * PROGRAMACION ORIENTADA A OBJETOS
 * ESTEBAN EMILIO CUMATZ QUINA, 24449
 * ADRIAN PENAGOS, 24914
 * DENIL PARADA, 24761
 * FIGUERA REYES, 24073
 * JOEL JOSUE NERIO ALONZO, 24253
 * PROYECTO PARTE 3
 */
public class Main {

    private static BufferedReader md = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Usuario> usuarios = GestorCSV.cargarUsuarios("usuarios.csv");// Cargar usuarios desde CSV
    private static String rutaArchivo = "usuarios.csv";
    private static Random random = new Random();

    // Listas de recomendaciones de ahorro
    private static List<String> recomendacionesAhorro = Arrays.asList(
        "Lleva un registro detallado de tus gastos para identificar áreas en las que puedas recortar.",
        "Establece un presupuesto mensual y apégate a él para evitar gastos innecesarios.",
        "Evita las compras impulsivas. Tómate un tiempo para pensar si realmente necesitas lo que estás por comprar.",
        "Revisa tus suscripciones mensuales y cancela las que no estés utilizando.",
        "Aprovecha las promociones y descuentos, pero solo si es algo que realmente necesitas.",
        "Usa efectivo en lugar de tarjetas para tener un mayor control sobre tus gastos diarios."
    );
    // Lista de alertas cuando se excede el límite de gasto
    private static List<String> alertasGasto = Arrays.asList(
        "¡Atención! Has sobrepasado tu límite de gasto. Considera recortar algunos gastos innecesarios.",
        "Tu nivel de gasto está por encima del límite establecido. Evita compras adicionales este mes.",
        "Estás gastando más de lo previsto. Intenta identificar áreas en las que puedas reducir gastos.",
        "¡Advertencia! Tu gasto está fuera de control. Considera reestructurar tu presupuesto.",
        "Cuidado, tus gastos están sobre el límite. Considera ahorrar más y gastar menos en el futuro."
    );

    public static void main(String[] args) {
        ejecutarMenuPrincipal();  //manda a llamar metodo
    }

    private static void ejecutarMenuPrincipal() {
        boolean continuar = true;
        while (continuar) {   
            try {
                mostrarOpcionesMenuPrincipal();  //se manda a llamar funcion
                int opcion = Integer.parseInt(md.readLine());  //lee la linea
                switch (opcion) {
                    case 1:
                        registrarUsuario();//se manda a llamar funcion
                        break;
                    case 2:
                        iniciarSesion();//se manda a llamar funcion
                        break;
                    case 3: //si elige que quiere salir
                        continuar = false; // Salir del programa
                        System.out.println("Saliendo...");
                        break;
                    default: //por si no elige algo
                        System.out.println("Opción no válida, intenta de nuevo.");
                        break;
                }
            } catch (Exception e) {  //uso de errores predefinidos
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
    }

    private static void mostrarOpcionesMenuPrincipal() {
        System.out.println("Ingrese una opción: ");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar sesión");
        System.out.println("3. Salir");
    }

    private static void registrarUsuario() throws Exception {
        System.out.println("Ingrese su nombre de usuario:");
        String nombre = md.readLine();
        System.out.println("Ingrese su contraseña:");
        String contra = md.readLine();
        System.out.println("Ingrese su banco:");
        String bancoNombre = md.readLine();
        System.out.println("Ingrese la página web de su banco:");
        String bancoPagina = md.readLine();
        Banco banco = new Banco(bancoNombre, bancoPagina);
        Usuario user = new Usuario(nombre, 0.0, contra);  // Inicialmente con gasto 0
        usuarios.add(user);
        GestorCSV.guardarUsuarios(usuarios, rutaArchivo);  // Guardar al archivo CSV
        System.out.println("Usuario creado y guardado exitosamente!");
    }

    private static void iniciarSesion() throws Exception {
        System.out.println("Ingrese su nombre de usuario:");
        String nombre = md.readLine();
        System.out.println("Ingrese su contraseña:");
        String contra = md.readLine();

        Usuario usuarioLogueado = validarCredenciales(nombre, contra);
        if (usuarioLogueado != null) {
            ejecutarMenuUsuario(usuarioLogueado);
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }

    private static Usuario validarCredenciales(String nombre, String contra) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getContraseña().equals(contra)) {
                System.out.println("Inicio de sesión exitoso!");
                return usuario;
            }
        }
        return null;
    }

    private static void ejecutarMenuUsuario(Usuario usuario) throws Exception {
        boolean enMenu = true;
        while (enMenu) {
            mostrarOpcionesMenuUsuario();
            int opcionMenu = Integer.parseInt(md.readLine());
            switch (opcionMenu) {
                case 1:
                    registrarIngresosYGastos(usuario);
                    break;
                case 2:
                    verDatosFinancieros(usuario);
                    break;
                case 3:
                    mostrarRecomendacionAhorro();
                    break;
                case 4:
                    mostrarAlertasGasto(usuario);
                    break;
                case 5:
                    sincronizarCuentasBancarias();
                    break;
                case 6:
                    exportarYGuardarDatos();
                    break;
                case 7:
                    enMenu = false;
                    System.out.println("Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        }
    }

    private static void mostrarOpcionesMenuUsuario() {
        System.out.println("\n------ Menú Principal ------");
        System.out.println("1. Registrar ingresos y gastos");
        System.out.println("2. Ver datos financieros");
        System.out.println("3. Recomendación de ahorro");
        System.out.println("4. Alertas de límite de gasto");
        System.out.println("5. Sincronización con cuentas bancarias");
        System.out.println("6. Exportar y guardar datos");
        System.out.println("7. Salir");
        System.out.println("Elige una opción:");
    }

    private static void registrarIngresosYGastos(Usuario usuario) throws Exception {
        System.out.println("Ingrese la cantidad de gastos:");
        double gasto = Double.parseDouble(md.readLine());
        System.out.println("Ingrese la cantidad de ahorro:");
        double ahorro = Double.parseDouble(md.readLine());
        usuario.setGasto(usuario.getGasto() + gasto);  // Sumar al gasto actual
        usuario.setAhorro(usuario.getAhorro() + ahorro);  // Sumar al ahorro actual
        GestorCSV.guardarUsuarios(usuarios, rutaArchivo);  // Guardar los cambios
        System.out.println("Gastos y ahorros registrados.");
    }

    private static void verDatosFinancieros(Usuario usuario) {
        System.out.println("Reportes financieros:");
        System.out.println("Gasto total: " + usuario.getGasto());
        System.out.println("Ahorro total: " + usuario.getAhorro());
    }

    private static void mostrarRecomendacionAhorro() {
        String recomendacion = recomendacionesAhorro.get(random.nextInt(recomendacionesAhorro.size()));
        System.out.println("Recomendaciones de ahorro: " + recomendacion);
    }

    private static void mostrarAlertasGasto(Usuario usuario) {
        if (usuario.getGasto() > 500) {  // Ejemplo de límite
            String alerta = alertasGasto.get(random.nextInt(alertasGasto.size()));
            System.out.println(alerta);
        } else {
            System.out.println("Sus gastos están dentro del límite.");
        }
    }

    private static void sincronizarCuentasBancarias() {
        System.out.println("Sincronizando con cuentas bancarias...");
        // agregar la lógica para sincronizar transacciones bancarias
        System.out.println("Funcionalidad no disponible aún.");
    }

    private static void exportarYGuardarDatos() {
        System.out.println("Exportando y guardando sus datos financieros...");
        GestorCSV.guardarUsuarios(usuarios, rutaArchivo);  // Guardar los cambios
        System.out.println("Datos exportados correctamente.");
    }
}
