package com.budget.buddy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CuestionarioFinanzasGUI extends JPanel {
    private CuestionarioFinanzas cuestionario;
    private int preguntaActual = 0;

    private JLabel lblPregunta;
    private JRadioButton[] opciones;
    private ButtonGroup grupoOpciones;
    private JButton btnSiguiente;

    public CuestionarioFinanzasGUI() {
        cuestionario = new CuestionarioFinanzas();
        
        setLayout(new BorderLayout());

        // Panel de fondo con degradado
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(245, 245, 245), getWidth(), getHeight(), new Color(225, 225, 235));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(backgroundPanel);

        lblPregunta = new JLabel();
        lblPregunta.setHorizontalAlignment(SwingConstants.CENTER);
        lblPregunta.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPregunta.setForeground(new Color(50, 50, 50));
        backgroundPanel.add(lblPregunta, BorderLayout.NORTH);

        JPanel panelOpciones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelOpciones.setOpaque(false);
        opciones = new JRadioButton[3];
        grupoOpciones = new ButtonGroup();

        for (int i = 0; i < opciones.length; i++) {
            opciones[i] = new JRadioButton();
            opciones[i].setFont(new Font("Segoe UI", Font.PLAIN, 14));
            opciones[i].setForeground(new Color(50, 50, 50));
            opciones[i].setOpaque(false);
            grupoOpciones.add(opciones[i]);
            panelOpciones.add(opciones[i]);
        }
        backgroundPanel.add(panelOpciones, BorderLayout.CENTER);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSiguiente.setForeground(Color.WHITE);
        btnSiguiente.setBackground(new Color(100, 149, 237));
        btnSiguiente.setFocusPainted(false);
        btnSiguiente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSiguiente.setPreferredSize(new Dimension(200, 35));
        addHoverEffect(btnSiguiente, new Color(80, 130, 220), new Color(100, 149, 237));
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarRespuesta();
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnSiguiente);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        mostrarPregunta();
    }

    private void mostrarPregunta() {
        if (preguntaActual < cuestionario.preguntas.length) {
            lblPregunta.setText("Pregunta " + (preguntaActual + 1) + ": " + cuestionario.preguntas[preguntaActual][0]);
            for (int i = 0; i < opciones.length; i++) {
                opciones[i].setText(cuestionario.preguntas[preguntaActual][i + 1]);
                opciones[i].setSelected(false);
            }
        } else {
            mostrarResultado();
        }
    }
    

    private void verificarRespuesta() {
        int respuestaCorrecta = Integer.parseInt(cuestionario.preguntas[preguntaActual][4]);
        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].isSelected() && i + 1 == respuestaCorrecta) {
                cuestionario.puntaje++;
                JOptionPane.showMessageDialog(this, "¡Correcto!");
                break;
            } else if (opciones[i].isSelected()) {
                JOptionPane.showMessageDialog(this, "Incorrecto.");
                break;
            }
        }
        preguntaActual++;
        mostrarPregunta();
    }

    private void mostrarResultado() {
        String mensaje = "Tu puntaje final es: " + cuestionario.puntaje + "/" + cuestionario.preguntas.length + "\n";
        if (cuestionario.puntaje == cuestionario.preguntas.length) {
            mensaje += "¡Excelente! Eres un experto en finanzas.";
        } else if (cuestionario.puntaje >= cuestionario.preguntas.length / 2) {
            mensaje += "¡Bien hecho! Tienes buenos conocimientos en finanzas.";
        } else {
            mensaje += "Sigue aprendiendo sobre finanzas para mejorar tus habilidades.";
        }
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void addHoverEffect(JButton button, Color hoverColor, Color normalColor) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(normalColor);
            }
        });
    }
}
