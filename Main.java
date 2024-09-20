import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        boolean b = true;
        BufferedReader md = new BufferedReader(new InputStreamReader(System.in));
        ArrayList <Usuario> usuarios = new ArrayList<>();
        boolean salir = false; 

        while (b = true){//comienzo del while 
            try {
                System.out.println("Ingrese una opción: \n1. Registrarse  \n2. Iniciar sesión \n3. Salir");
                int a = Integer.parseInt(md.readLine());
                if(a == 1){ //Crear un usuario
                    System.out.println("Ingrese su nombre de usuario");
                    String nombre = md.readLine();
                    System.out.println("Ingrese su contraseña");
                    String contra = md.readLine();
                    Usuario user = new Usuario(nombre, contra);
                    usuarios.add(user);
                    System.out.println("Usuario creado exitosamente!");
                }
                else if (a == 2){ /* Iniciar sesion*/
                    System.out.println("Ingrese su nombre de usuario");
                    String nombre = md.readLine();
                    System.out.println("Ingrese su contraseña");
                    String contra = md.readLine();
                    for (Usuario usuario : usuarios) { // for 
                        if(usuario.getNombre().equals(nombre) && usuario.getContraseña().equals(contra)) {//verificador 
                            salir = true; 
                        }                  
                    } // fin del for 
                    while (!salir) {
                        System.out.println("1. ------Menú Principal--------- \n ");    /* para crear segundo menu ya luego de iniciar sesion*/
                        System.out.println("1. Registrar ingresos y gastos \n2. Reportes financieros  \n 3.Salir ");
                        System.out.println("Elige una opcion");
                        

                    }
                }
                else if (a == 3){ // salir 
                    System.out.println("Saliendo...");
                    b = false;
                }
            
                
            } catch (Exception e) {
                // TODO: handle exception
            }

     }// fin del while 
    }
}