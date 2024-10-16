import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LogInGUI extends JPanel {

    BufferedReader md = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Usuario> usuarios = GestorCSV.cargarUsuarios("usuarios.csv"); // Cargar usuarios desde CSV
    boolean logueado = false;
    String rutaArchivo = "usuarios.csv";

    private JTextField userText;
    private JPasswordField passwordText;
    private JButton loginButton, newUserButton;
    private MainGUI main;

    public LogInGUI(MainGUI main) {
        this.main = main;
        LogInPane();
    }

    private void LogInPane() {

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
        userText = new JTextField(15);
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
        passwordText = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordText, gbc);

        // Botón de login
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(75, 110, 175)); // Color de fondo del botón
        loginButton.setForeground(Color.WHITE); // Color del texto del botón
        loginButton.setFocusPainted(false); // Quitar el borde de selección
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Hacer que el botón ocupe 2 columnas
        panel.add(loginButton, gbc);

        // Botón para creación de usuario y contraseña
        newUserButton = new JButton("¿No tiene usuario y contraseña?");
        newUserButton.setBackground(new Color(75, 110, 175)); // Color de fondo del botón
        newUserButton.setForeground(Color.WHITE); // Color del texto del botón
        newUserButton.setFocusPainted(false); // Quitar el borde de selección
        newUserButton.setOpaque(false); // Quitar la opacidad del botón
        newUserButton.setContentAreaFilled(false); // Quitar el color que rodea al botón
        newUserButton.setBorderPainted(false); // Quitar el borde del botón
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Hacer que el botón ocupe 2 columnas
        panel.add(newUserButton, gbc);

        // Añadir el panel de login a este JPanel
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);

        // botón de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verify();
            }
        });

        // Acción del botón de nuevo usuario
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.showNewUserPanel(); // Cambiar al panel de crear usuario
            }
        });
    }

    private void verify() {
        String nombre = userText.getText();
        String contra = String.valueOf(passwordText.getPassword());

        // Validar usuario y contraseña
        boolean usuarioValido = false;
        for (Usuario usuario : usuarios) { // Verificar usuario
            if (usuario.getNombre().equals(nombre) && usuario.getContraseña().equals(contra)) {
                logueado = true;
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
                main.mostrarMenu();
                usuarioValido = true;
                break; // Romper el ciclo si se encuentra el usuario
            }
        }

        if (!usuarioValido) {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}