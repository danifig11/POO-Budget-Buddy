import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Hacer que el botón ocupe 2 columnas
        panel.add(loginButton, gbc);

        // Botón para creación de usuario y contraseña
        JButton newUserButton = new JButton("¿No tiene usuario y contraseña?");
        newUserButton.setBackground(new Color(75, 110, 175)); // Color de fondo del botón
        newUserButton.setForeground(Color.WHITE); // Color del texto del botón
        newUserButton.setFocusPainted(false); // Quitar el borde de selección
        newUserButton.setOpaque(false);
        newUserButton.setContentAreaFilled(false);
        newUserButton.setBorderPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Hacer que el botón ocupe 2 columnas
        panel.add(newUserButton, gbc);

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

        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewUserPanel(); // Mostrar el panel de nuevo usuario
            }
        });

        

        frame.getContentPane().add(panel);
        frame.setVisible(true); 
    }

    // Método para mostrar el panel principal después del login
    private void NewUserPanel() {
        // Crear JFrame para el registro de nuevo usuario
        JFrame newUserFrame = new JFrame("Nuevo Usuario");
        newUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newUserFrame.setSize(400, 300);
        newUserFrame.setLocationRelativeTo(null); // Centrar la ventana
    
        // Crear panel con GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(60, 63, 65)); // Fondo oscuro
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Añadir márgenes para espaciado
    
        // Etiqueta de usuario
        JLabel userLabel = new JLabel("Ingrese su nombre de Usuario:");
        userLabel.setForeground(Color.WHITE); // Color del texto
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
        gbc.fill = GridBagConstraints.HORIZONTAL; // Hacer que ocupe todo el ancho
        panel.add(userLabel, gbc);
    
        // Campo de texto para usuario
        JTextField userText = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(userText, gbc);
    
        // Etiqueta de contraseña
        JLabel passwordLabel = new JLabel("Ingrese una contraseña segura:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordLabel, gbc);
    
        // Campo de contraseña
        JPasswordField passwordText = new JPasswordField(15);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordText, gbc);
    
        // Botón de creación de usuario
        JButton createUserButton = new JButton("Crear Usuario");
        createUserButton.setBackground(new Color(75, 110, 175));
        createUserButton.setForeground(Color.WHITE);
        createUserButton.setFocusPainted(false); // Quitar borde de enfoque
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(createUserButton, gbc);
    
        // Botón para regresar al login
        JButton backButton = new JButton("Regresar al Login");
        backButton.setBackground(new Color(75, 110, 175));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(backButton, gbc);
    
        // Acción del botón de regresar al login
        backButton.addActionListener(e -> {
            newUserFrame.dispose(); // Cerrar la ventana actual
            crearLoginFrame(); // Regresar a la ventana de login
        });

        // Acción del botón de registrar el usuario
        createUserButton.addActionListener(e -> {
            String nombre = userText.getText();
            String contra = String.valueOf(passwordText.getPassword());
            Usuario user = new Usuario(nombre, 0.0, contra);  // Inicialmente con gasto 0
            usuarios.add(user);
            GestorCSV.guardarUsuarios(usuarios, rutaArchivo);  // Guardar al archivo CSV
        });
    
        // Añadir el panel al marco y hacerlo visible
        newUserFrame.getContentPane().add(panel);
        newUserFrame.setVisible(true);
    }
    
}