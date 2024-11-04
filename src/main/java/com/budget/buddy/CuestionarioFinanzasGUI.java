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

        lblPregunta = new JLabel();
        lblPregunta.setHorizontalAlignment(SwingConstants.CENTER);
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblPregunta, BorderLayout.NORTH);

        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new GridLayout(3, 1));
        opciones = new JRadioButton[3];
        grupoOpciones = new ButtonGroup();

        for (int i = 0; i < opciones.length; i++) {
            opciones[i] = new JRadioButton();
            grupoOpciones.add(opciones[i]);
            panelOpciones.add(opciones[i]);
        }
        add(panelOpciones, BorderLayout.CENTER);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarRespuesta();
            }
        });
        add(btnSiguiente, BorderLayout.SOUTH);

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
}

