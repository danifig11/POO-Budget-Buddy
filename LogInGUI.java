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

    private void crearLoginFrame() {
        JFrame frame = new JFrame("Login"); // Crear el marco principal
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200); // Tamaño de la ventana

        // Crear panel para el login
        JPanel panel = new JPanel(new GridLayout(3, 2)); 

        JLabel userLabel = new JLabel("Usuario: ");
        JTextField userText = new JTextField();
        JLabel passwordLabel = new JLabel("Contraseña: ");
        JPasswordField passwordText = new JPasswordField();

        JButton loginButton = new JButton("Login");

        //componentes del  panel
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(loginButton);

        // botón de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = userText.getText();
                String contra = String.valueOf(passwordText.getPassword());

                // Validar usuario y contraseña
                for (Usuario usuario : usuarios) { // Verificar usuario
                    if (usuario.getNombre().equals(nombre) && usuario.getContraseña().equals(contra)) {
                        System.out.println("Inicio de sesión exitoso!");
                        logueado = true;
                        break;
                    }
                }
            }
        });
        frame.getContentPane().add(panel);
        frame.setVisible(true); 

        public static void main(String[] args) {
            new LoginApp(); // Crear la aplicación de login
        }
}