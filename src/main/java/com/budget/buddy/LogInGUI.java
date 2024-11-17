package com.budget.buddy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        // Crear panel de fondo con un degradado sutil
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(245, 245, 245), getWidth(), getHeight(), new Color(225, 225, 235));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Panel para el contenido central
        JPanel loginCard = new JPanel(new GridBagLayout());
        loginCard.setBackground(Color.WHITE);
        loginCard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        loginCard.setPreferredSize(new Dimension(400, 300));
        loginCard.setLayout(new GridBagLayout());

        // Sombra para dar sensación de flotación
        loginCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Etiqueta del título
        JLabel titleLabel = new JLabel("Inicio de Sesión");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(new Color(50, 50, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginCard.add(titleLabel, gbc);

        // Etiqueta de usuario
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setForeground(new Color(80, 80, 80));
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginCard.add(userLabel, gbc);

        // Campo de texto para usuario
        userText = new JTextField(15);
        userText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userText.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        userText.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginCard.add(userText, gbc);

        // Etiqueta de contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordLabel.setForeground(new Color(80, 80, 80));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        loginCard.add(passwordLabel, gbc);

        // Campo de contraseña
        passwordText = new JPasswordField(15);
        passwordText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordText.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        passwordText.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginCard.add(passwordText, gbc);

        // Botón de login
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setPreferredSize(new Dimension(200, 35));
        addHoverEffect(loginButton, new Color(80, 130, 220), new Color(100, 149, 237));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginCard.add(loginButton, gbc);

        // Botón para creación de usuario
        newUserButton = new JButton("Crear usuario");
        newUserButton.setBackground(new Color(230, 230, 230));
        newUserButton.setForeground(new Color(100, 149, 237));
        newUserButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        newUserButton.setFocusPainted(false);
        newUserButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        newUserButton.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237)));
        newUserButton.setPreferredSize(new Dimension(200, 35));
        addHoverEffect(newUserButton, new Color(210, 210, 210), new Color(230, 230, 230));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        loginCard.add(newUserButton, gbc);

        // Añadir el panel central al fondo
        backgroundPanel.add(loginCard);

        // Añadir el panel de fondo al JPanel principal
        this.setLayout(new BorderLayout());
        this.add(backgroundPanel, BorderLayout.CENTER);

        // Acción del botón de login
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
                main.showNewUserPanel();
            }
        });
    }

    private void verify() {
        String nombre = userText.getText();
        String contra = String.valueOf(passwordText.getPassword());

        // Validar usuario y contraseña
        boolean usuarioValido = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getContraseña().equals(contra)) {
                logueado = true;
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
                main.mostrarMenu();
                usuarioValido = true;
                break;
            }
        }

        if (!usuarioValido) {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addHoverEffect(JButton button, Color hoverColor, Color normalColor) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(normalColor);
            }
        });
    }
}
