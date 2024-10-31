package com.budget.buddy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setLayout(new GridBagLayout());
        setBackground(new Color(60, 63, 65));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel userLabel = new JLabel("Ingrese su nombre de Usuario:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(userLabel, gbc);

        userText = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(userText, gbc);

        JLabel passwordLabel = new JLabel("Ingrese una contraseña segura:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(passwordLabel, gbc);

        passwordText = new JPasswordField(15);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(passwordText, gbc);

        createUserButton = new JButton("Crear Usuario");
        createUserButton.setBackground(new Color(75, 110, 175));
        createUserButton.setForeground(Color.WHITE);
        createUserButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(createUserButton, gbc);

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

                String resultado = gestionador.registrarUsuario(nombre, contra);
                
                if (resultado.equals("Usuario creado exitosamente.")) {
                    JOptionPane.showMessageDialog(NewUserGUI.this, resultado);
                    main.showLogIn();
                } else {
                    JOptionPane.showMessageDialog(NewUserGUI.this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.showLogIn();
            }
        });
    }
}