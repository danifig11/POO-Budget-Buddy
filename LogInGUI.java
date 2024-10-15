import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogInGUI extends JPanel{

    private JTextField userText;
    private JPasswordField passwordText;
    private JButton loginButton, newUserButton;

    public LogInGUI() {
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
        newUserButton.setOpaque(false);// Quitar la opacidad del botón
        newUserButton.setContentAreaFilled(false); //Quitar el color que rodea al botón
        newUserButton.setBorderPainted(false); // Quitar el borde del botón
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Hacer que el botón ocupe 2 columnas
        panel.add(newUserButton, gbc);
    }
    
    public String getUsername() {
        return userText.getText();
    }

    public String getPassword() {
        return new String(passwordText.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addNewUserListener(ActionListener listener) {
        newUserButton.addActionListener(listener);
    }

    
}