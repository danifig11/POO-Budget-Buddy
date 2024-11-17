package com.budget.buddy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class NewUserGUI extends JPanel {
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton createUserButton, backButton;
    private Gestionador gestionador;
    private MainGUI main;

    public NewUserGUI(MainGUI main) {
        this.main = main;
        ArrayList<Usuario> usuarios = GestorCSV.cargarUsuarios("usuarios.csv");
        this.gestionador = new Gestionador(usuarios, "usuarios.csv", new ArrayList<>(), new ArrayList<>());
        NewUserFrame();
    }

    public void NewUserFrame() {
        // Panel de fondo con degradado
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

        // Tarjeta flotante
        JPanel userCard = new JPanel(new GridBagLayout());
        userCard.setBackground(Color.WHITE);
        userCard.setPreferredSize(new Dimension(400, 300));
        userCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Etiqueta del título
        JLabel titleLabel = new JLabel("Crear Usuario");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(new Color(50, 50, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        userCard.add(titleLabel, gbc);

        // Etiqueta de nombre de usuario
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setForeground(new Color(80, 80, 80));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        userCard.add(userLabel, gbc);

        // Campo de texto para nombre de usuario
        userText = new JTextField(15);
        userText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userText.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        userCard.add(userText, gbc);

        // Etiqueta de contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordLabel.setForeground(new Color(80, 80, 80));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        userCard.add(passwordLabel, gbc);

        // Campo de texto para contraseña
        passwordText = new JPasswordField(15);
        passwordText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordText.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        userCard.add(passwordText, gbc);

        // Botón "Crear Usuario"
        createUserButton = new JButton("Crear Usuario");
        createUserButton.setBackground(new Color(100, 149, 237));
        createUserButton.setForeground(Color.WHITE);
        createUserButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        createUserButton.setFocusPainted(false);
        createUserButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addHoverEffect(createUserButton, new Color(80, 130, 220), new Color(100, 149, 237));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // El botón ocupa dos columnas
        gbc.anchor = GridBagConstraints.CENTER;
        userCard.add(createUserButton, gbc);

        // Botón "Regresar al Login"
        backButton = new JButton("Regresar al Login");
        backButton.setBackground(new Color(230, 230, 230));
        backButton.setForeground(new Color(100, 149, 237));
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addHoverEffect(backButton, new Color(210, 210, 210), new Color(230, 230, 230));
        gbc.gridy = 4;
        userCard.add(backButton, gbc);

        // Añadir tarjeta flotante al fondo
        backgroundPanel.add(userCard);

        // Añadir el fondo al JPanel principal
        this.setLayout(new BorderLayout());
        this.add(backgroundPanel, BorderLayout.CENTER);

        // Acción para crear usuario
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = userText.getText();
                String contra = String.valueOf(passwordText.getPassword());

                String resultado = gestionador.registrarUsuario(nombre, contra);

                if (resultado.equals("Usuario creado exitosamente.")) {
                    JOptionPane.showMessageDialog(NewUserGUI.this, resultado);
                    main.showLogIn();
                } else {
                    JOptionPane.showMessageDialog(NewUserGUI.this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para regresar al login
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.showLogIn();
            }
        });
    }

    // Método para agregar efecto hover
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
