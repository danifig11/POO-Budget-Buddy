import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewUserGUI extends JPanel {
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton createUserButton, backButton;

    public NewUserGUI() {
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
    }

    public String getUsername() {
        return userText.getText();
    }

    public String getPassword() {
        return new String(passwordText.getPassword());
    }

    public void addCreateUserListener(ActionListener listener) {
        createUserButton.addActionListener(listener);
    }

    public void addBackListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}

