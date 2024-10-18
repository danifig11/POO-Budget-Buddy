import java.util.ArrayList;
import java.util.List;


public class Gestionador {
    private ArrayList<Usuario> usuarios;
    private String rutaArchivo;
    public Gestionador(ArrayList<Usuario> usuarios, String rutaArchivo, List<String> recomendacionesAhorro, List<String> alertasGasto) {
        this.usuarios = usuarios;
        this.rutaArchivo = rutaArchivo;
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


}
