import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NewUserGUI extends JPanel {
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton createUserButton, backButton;

    BufferedReader md = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Usuario> usuarios = GestorCSV.cargarUsuarios("usuarios.csv"); // Cargar usuarios desde CSV
    boolean logueado = false;
    String rutaArchivo = "usuarios.csv"; 

    public NewUserGUI() {
        NewUserFrame();
    }
    
    public void NewUserFrame(){
        JFrame frame = new JFrame("Nuevo Usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Tamaño de la ventana
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear panel para el login con GridBagLayout para un diseño flexible
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(60, 63, 65)); // Fondo oscuro

        setLayout(new GridBagLayout());
        setBackground(new Color(60, 63, 65));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Etiqueta de usuario
        JLabel userLabel = new JLabel("Ingrese su nombre de Usuario:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
        gbc.fill = GridBagConstraints.HORIZONTAL; // Hacer que ocupe todo el ancho
        add(userLabel, gbc);

        // Campo de texto para usuario
        userText = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(userText, gbc);

        // Etiqueta de contraseña
        JLabel passwordLabel = new JLabel("Ingrese una contraseña segura:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(passwordLabel, gbc);

        // Campo de contraseña
        passwordText = new JPasswordField(15);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(passwordText, gbc);

        // Botón de crear usuario
        createUserButton = new JButton("Crear Usuario");
        createUserButton.setBackground(new Color(75, 110, 175));
        createUserButton.setForeground(Color.WHITE);
        createUserButton.setFocusPainted(false); // Quitar borde de enfoque
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(createUserButton, gbc);

        // Botón para regresar al login
        backButton = new JButton("Regresar al Login");
        backButton.setBackground(new Color(75, 110, 175));
        backButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(backButton, gbc);

        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = userText.getText();
                String contra = String.valueOf(passwordText.getPassword());
                Usuario user = new Usuario(nombre, 0.0, contra);  // Inicialmente con gasto 0
                usuarios.add(user);
                GestorCSV.guardarUsuarios(usuarios, rutaArchivo);  // Guardar al archivo CSV
            }
        });

        // Acción del botón de regresar al login
        backButton.addActionListener(e -> {
            frame.dispose(); // Cerrar la ventana actual
            // Regresar a la ventana de login
        });

        // Añadir el panel al marco y hacerlo visible
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
     
    
}

