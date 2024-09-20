import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        BufferedReader md = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Usuario> usuarios = GestorCSV.cargarUsuarios("usuarios.csv"); // Cargar usuarios desde CSV
        boolean logueado = false;
        String rutaArchivo = "usuarios.csv";

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
                            System.out.println("2. Reportes financieros");
                            System.out.println("3. Salir");
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
                                            usuario.setGasto(gasto);
                                            usuario.setAhorro(ahorro);
                                            break;
                                        }
                                    }
                                    GestorCSV.guardarUsuarios(usuarios, rutaArchivo);  // Guardar los cambios
                                    System.out.println("Gastos y ahorros registrados.");
                                    break;
                                case 2:
                                    System.out.println("Reportes financieros:");
                                    for (Usuario usuario : usuarios) {
                                        if (usuario.getNombre().equals(nombre)) {
                                            System.out.println("Gasto: " + usuario.getGasto());
                                            System.out.println("Ahorro: " + usuario.getAhorro());
                                        }
                                    }
                                    break;
                                case 3:
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
