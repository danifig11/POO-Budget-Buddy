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

    public LogInGUI() {
        crearLoginFrame(); // Crea la interfaz del Login
    }
    
    BufferedReader md = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Usuario> usuarios = GestorCSV.cargarUsuarios("usuarios.csv"); // Cargar usuarios desde CSV
    boolean logueado = false;
    String rutaArchivo = "usuarios.csv"; 

    private void crearLoginFrame() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Tamaño de la ventana
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear panel para el login con GridBagLayout para un diseño flexible
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(60, 63, 65)); // Fondo oscuro

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado

        // Etiqueta del título
        JLabel titleLabel = new JLabel("Inicio de Sesión");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE); // Color del texto
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Usar 2 columnas para centrar
        panel.add(titleLabel, gbc);

        // Etiqueta de usuario
        JLabel userLabel = new JLabel("Usuario: ");
        userLabel.setForeground(Color.WHITE); // Color del texto
        gbc.gridwidth = 1; // Restablecer el ancho de la columna
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(userLabel, gbc);

        // Campo de texto para usuario
        JTextField userText = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(userText, gbc);

        // Etiqueta de contraseña
        JLabel passwordLabel = new JLabel("Contraseña: ");
        passwordLabel.setForeground(Color.WHITE); // Color del texto
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        // Campo de contraseña
        JPasswordField passwordText = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordText, gbc);

        // Botón de login
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(75, 110, 175)); // Color de fondo del botón
        loginButton.setForeground(Color.WHITE); // Color del texto del botón
        loginButton.setFocusPainted(false); // Quitar el borde de selección
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Hacer que el botón ocupe 2 columnas
        panel.add(loginButton, gbc);

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
    }
}