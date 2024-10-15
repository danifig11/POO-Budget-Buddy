import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class LogInGUI {
    private HashMap<String, String> users = new HashMap<>();

    public LoginApp() {
        cargarUsuarios(); // Carga el archivo CSV con los usuarios y los datos
        crearLoginFrame(); // Crea la interfaz del Login
    }
    
    BufferedReader md = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Usuario> usuarios = GestorCSV.cargarUsuarios("usuarios.csv"); // Cargar usuarios desde CSV
    boolean logueado = false;
    String rutaArchivo = "usuarios.csv"; 

    

}