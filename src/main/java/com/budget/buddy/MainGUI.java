
/*UNIVERSIDAD DE VALLE DE GUATEMALA
 * CAMPUS CENTRAL
 * PROGRAMACION ORIENTADA A OBJETOS
 * ESTEBAN EMILIO CUMATZ QUINA, 24449
 * ADRIAN PENAGOS, 24914
 * DENIL PARADA, 24761
 * fIGUERA REYES, 24073
 * JOEL JOSUE NERIO ALONZO, 24253
 * PROYECTO PARTE 3
 */

package com.budget.buddy;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    private JTabbedPane pane;
    private JFrame frame;

    // Agregar campos para almacenar el presupuesto y gastos mensuales
    private double gastosMensuales = 0;
    private double presupuestoMensual = 0;

    // Campos de texto del formulario
    private JTextField gastosField;
    private JTextField presupuestoField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI main = new MainGUI();
            main.showLogIn();
        });
    }

    public MainGUI() {
        frame = new JFrame("Budget_Buddy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // Aumentar el tamaño de la ventana para la gráfica
        frame.setLocationRelativeTo(null); // Centrar la ventana
    }

    public void showLogIn() {
        // JPanel de LogIn se añade al JFrame
        LogInGUI loginPanel = new LogInGUI(this);
        frame.setContentPane(loginPanel); // Cambiar el contenido a LogInGUI
        frame.setVisible(true);
    }

    public void showNewUserPanel() {
        // Cambiar al panel de creación de usuario
        NewUserGUI newUserPanel = new NewUserGUI(this); // Pasar la instancia de MainGUI
        frame.setContentPane(newUserPanel); // Cambiar el contenido a NewUserGUI
        frame.revalidate();
        frame.repaint();
    }

    public void mostrarMenu() {
        pane = new JTabbedPane(); // Inicializar el pane antes de usarlo

        // Pestaña 1: Mostrar gráfica
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout()); // Usamos BorderLayout para que la gráfica ocupe todo el espacio
        panel1.setBackground(new Color(45, 45, 48)); // Fondo oscuro

        // Crear la gráfica inicial vacía
        ChartPanel chartPanel = crearPanelGrafica();
        panel1.add(chartPanel, BorderLayout.CENTER); // Añadir gráfica al panel

        // Añadir la pestaña de la gráfica
        pane.addTab("Gráfica", panel1);

        // Pestaña 2: Formulario para ingresar datos
        JPanel panel2 = new JPanel(new BorderLayout(10, 10));
        panel2.setBackground(new Color(45, 45, 48)); // Fondo oscuro

        // Panel para el formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(60, 63, 65)); // Fondo del formulario

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Que las componentes se expandan horizontalmente
        gbc.anchor = GridBagConstraints.WEST;

        // Etiqueta de gastos mensuales
        JLabel gastosLabel = new JLabel("Ingrese sus gastos mensuales (Q): ");
        gastosLabel.setForeground(Color.WHITE);
        gastosLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(gastosLabel, gbc);

        // Campo de texto para gastos mensuales
        gastosField = new JTextField(20); // Más grande
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(gastosField, gbc);

        // Etiqueta de presupuesto mensual
        JLabel presupuestoLabel = new JLabel("Ingrese su presupuesto mensual (Q): ");
        presupuestoLabel.setForeground(Color.WHITE);
        presupuestoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(presupuestoLabel, gbc);

        // Campo de texto para presupuesto mensual
        presupuestoField = new JTextField(20); // Más grande
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(presupuestoField, gbc);

        // Añadir el formulario al panel principal
        panel2.add(formPanel, BorderLayout.CENTER);

        // Panel para el botón de cálculo
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(45, 45, 48));

        JButton calcularButton = new JButton("Calcular y Mostrar Gráfica");
        calcularButton.setBackground(new Color(75, 110, 175));
        calcularButton.setForeground(Color.WHITE);
        calcularButton.setFocusPainted(false); // Quitar el borde de enfoque
        calcularButton.setFont(new Font("Arial", Font.BOLD, 14));
        calcularButton.setPreferredSize(new Dimension(200, 40));
        buttonPanel.add(calcularButton);

        // Añadir el botón en la parte inferior del panel principal
        panel2.add(buttonPanel, BorderLayout.SOUTH);

        // Acción al hacer clic en el botón calcular
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Leer los valores ingresados por el usuario
                    gastosMensuales = Double.parseDouble(gastosField.getText());
                    presupuestoMensual = Double.parseDouble(presupuestoField.getText());

                    // Actualizar la gráfica en la Pestaña 1 con los nuevos valores
                    ChartPanel newChartPanel = crearPanelGrafica();
                    panel1.removeAll(); // Limpiar la pestaña antes de añadir la nueva gráfica
                    panel1.add(newChartPanel, BorderLayout.CENTER); // Añadir nueva gráfica
                    panel1.revalidate(); // Volver a validar el panel
                    panel1.repaint(); // Refrescar la gráfica

                    // Mostrar mensaje de cálculo
                    double restante = presupuestoMensual - gastosMensuales;
                    JOptionPane.showMessageDialog(panel2, "Te queda Q" + restante + " este mes.");

                    if (gastosMensuales >= presupuestoMensual * 0.7) {
                        JOptionPane.showMessageDialog(panel2, "Advertencia: Cuidado con tus gastos, ya has usado más del 70% de tu presupuesto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // Si el usuario ingresa valores no válidos, mostrar un mensaje de error
                    JOptionPane.showMessageDialog(panel2, "Por favor ingresa números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Añadir la pestaña del formulario
        pane.addTab("Formulario", panel2);

        frame.setContentPane(pane); // Cambiar el contenido a las pestañas
        frame.revalidate();
        frame.repaint();
    }

    // Método para crear el panel de la gráfica
    private ChartPanel crearPanelGrafica() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Si los datos son válidos, añadir los valores al dataset
        if (gastosMensuales > 0 && presupuestoMensual > 0) {
            dataset.addValue(presupuestoMensual, "Ingresos", "Mes Actual");
            dataset.addValue(gastosMensuales, "Egresos", "Mes Actual");
        }

        // Crear la gráfica con los datos actualizados
        JFreeChart barChart = ChartFactory.createBarChart(
            "Ingresos vs Egresos",  // Título del gráfico
            "Mes",                  // Etiqueta del eje X
            "Monto (Q)",            // Etiqueta del eje Y
            dataset,                // Datos (dataset)
            PlotOrientation.VERTICAL, // Orientación vertical
            true, true, false        // Leyenda, tooltips, URLs
        );

        return new ChartPanel(barChart);
    }
}
