
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
import java.util.ArrayList;

public class MainGUI {
    private JTabbedPane pane;
    private JFrame frame;
    private Usuario usuario;
    private ArrayList<Usuario> usuarios;

    private double gastosMensuales = 0;
    private double presupuestoMensual = 0;

    private JTextField nombreArticuloField;
    private JTextField gastoArticuloField;
    private JTextField ingresoField;

    private DefaultListModel<String> listaArticulosModel;
    private JLabel totalGastosLabel;
    private JLabel totalIngresosLabel;
    private String rutaArchivoUsuarios = "usuarios.csv";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI main = new MainGUI();
            main.showLogIn();
        });
    }

    public MainGUI() {
        frame = new JFrame("Budget_Buddy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        // Cargar usuarios desde el archivo
        usuarios = GestorCSV.cargarUsuarios(rutaArchivoUsuarios);
        
        // Seleccionar un usuario de ejemplo (puedes cambiar esta lógica para seleccionar el usuario adecuado)
        if (!usuarios.isEmpty()) {
            usuario = usuarios.get(0); // Usuario de ejemplo
        }
    }

    public void showLogIn() {
        LogInGUI loginPanel = new LogInGUI(this);
        frame.setContentPane(loginPanel);
        frame.setVisible(true);
    }

    public void showNewUserPanel() {
        NewUserGUI newUserPanel = new NewUserGUI(this);
        frame.setContentPane(newUserPanel);
        frame.revalidate();
        frame.repaint();
    }

    public void mostrarMenu() {
        pane = new JTabbedPane();

        // Pestaña 1: Gráfica
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(new Color(45, 45, 48));
        ChartPanel chartPanel = crearPanelGrafica();
        panel1.add(chartPanel, BorderLayout.CENTER);
        pane.addTab("Gráfica", panel1);

        // Pestaña 2: Formulario y lista de artículos
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setBackground(new Color(45, 45, 48));

        // Crear el formulario en el lado izquierdo
        JPanel formPanel = crearPanelFormulario();
        
        // Crear el panel de lista en el lado derecho
        JPanel listaPanel = crearPanelLista();

        // Dividir la pestaña en dos partes
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, listaPanel);
        splitPane.setDividerLocation(300);
        panel2.add(splitPane, BorderLayout.CENTER);

        pane.addTab("Formulario y Gastos", panel2);
        frame.setContentPane(pane);
        frame.revalidate();
        frame.repaint();
    }

    private JPanel crearPanelFormulario() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(60, 63, 65));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta para nombre del artículo
        JLabel nombreArticuloLabel = new JLabel("Nombre del artículo:");
        nombreArticuloLabel.setForeground(Color.WHITE);
        nombreArticuloLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nombreArticuloLabel, gbc);

        // Campo de texto para el nombre del artículo
        nombreArticuloField = new JTextField(20);
        gbc.gridy = 1;
        formPanel.add(nombreArticuloField, gbc);

        // Etiqueta para gasto del artículo
        JLabel gastoArticuloLabel = new JLabel("Monto del gasto (Q):");
        gastoArticuloLabel.setForeground(Color.WHITE);
        gastoArticuloLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        formPanel.add(gastoArticuloLabel, gbc);

        // Campo de texto para el gasto del artículo
        gastoArticuloField = new JTextField(20);
        gbc.gridy = 3;
        formPanel.add(gastoArticuloField, gbc);

        // Etiqueta para ingresos
        JLabel ingresoLabel = new JLabel("Ingrese su ingreso (Q):");
        ingresoLabel.setForeground(Color.WHITE);
        ingresoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 4;
        formPanel.add(ingresoLabel, gbc);

        // Campo de texto para ingreso mensual
        ingresoField = new JTextField(20);
        gbc.gridy = 5;
        formPanel.add(ingresoField, gbc);

        // Botón para agregar ingreso mensual
        JButton ingresoButton = new JButton("Agregar Ingreso Mensual");
        ingresoButton.setBackground(new Color(75, 110, 175));
        ingresoButton.setForeground(Color.WHITE);
        ingresoButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 6;
        formPanel.add(ingresoButton, gbc);

        // Botón para agregar gasto como artículo
        JButton agregarGastoButton = new JButton("Agregar Gasto");
        agregarGastoButton.setBackground(new Color(75, 110, 175));
        agregarGastoButton.setForeground(Color.WHITE);
        agregarGastoButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 7;
        formPanel.add(agregarGastoButton, gbc);

        // Acción para botón de ingreso
        ingresoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double ingreso = Double.parseDouble(ingresoField.getText());
                    usuario.setAhorro(usuario.getAhorro() + ingreso); // Actualizar ahorro del usuario
                    presupuestoMensual = ingreso;

                    // Guardar los cambios de ingreso en el archivo CSV
                    GestorCSV.guardarUsuarios(usuarios, rutaArchivoUsuarios);
                    
                    actualizarTotales();
                    ingresoField.setText("");
                    JOptionPane.showMessageDialog(formPanel, "Ingreso mensual agregado exitosamente.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formPanel, "Por favor, ingrese un valor numérico válido para el ingreso.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para botón de agregar gasto
        agregarGastoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombreArticulo = nombreArticuloField.getText();
                    double gastoArticulo = Double.parseDouble(gastoArticuloField.getText());

                    // Crear el artículo y agregarlo al usuario
                    Articulo articulo = new Articulo(nombreArticulo, gastoArticulo, "Lugar", "Fecha");
                    usuario.agregarArticulo(articulo);
                    usuario.setGasto(usuario.getGasto() + gastoArticulo);

                    // Actualizar el archivo de usuarios con el nuevo gasto
                    GestorCSV.guardarUsuarios(usuarios, rutaArchivoUsuarios);

                    // Añadir el artículo a la lista visual
                    listaArticulosModel.addElement(nombreArticulo + " - Q" + gastoArticulo);
                    actualizarTotales();

                    nombreArticuloField.setText("");
                    gastoArticuloField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formPanel, "Por favor, ingrese un valor numérico válido para el gasto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return formPanel;
    }

    private JPanel crearPanelLista() {
        JPanel listaPanel = new JPanel(new BorderLayout());
        listaPanel.setBackground(new Color(60, 63, 65));

        listaArticulosModel = new DefaultListModel<>();
        JList<String> listaArticulos = new JList<>(listaArticulosModel);
        listaArticulos.setBackground(new Color(45, 45, 48));
        listaArticulos.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(listaArticulos);
        listaPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel totalesPanel = new JPanel(new GridLayout(2, 1));
        totalesPanel.setBackground(new Color(60, 63, 65));

        totalGastosLabel = new JLabel("Total Gastos: Q0.0");
        totalGastosLabel.setForeground(Color.WHITE);
        totalGastosLabel.setFont(new Font("Arial", Font.BOLD, 14));

        totalIngresosLabel = new JLabel("Total Ingresos: Q0.0");
        totalIngresosLabel.setForeground(Color.WHITE);
        totalIngresosLabel.setFont(new Font("Arial", Font.BOLD, 14));

        totalesPanel.add(totalGastosLabel);
        totalesPanel.add(totalIngresosLabel);
        listaPanel.add(totalesPanel, BorderLayout.SOUTH);

        return listaPanel;
    }

    private void actualizarTotales() {
        totalGastosLabel.setText("Total Gastos: Q" + usuario.getGasto());
        totalIngresosLabel.setText("Total Ingresos: Q" + usuario.getAhorro());
    }

    private ChartPanel crearPanelGrafica() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (gastosMensuales > 0 && presupuestoMensual > 0) {
            dataset.addValue(presupuestoMensual, "Ingresos", "Mes Actual");
            dataset.addValue(gastosMensuales, "Egresos", "Mes Actual");
        }

        JFreeChart barChart = ChartFactory.createBarChart(
            "Ingresos vs Egresos",  
            "Mes",                  
            "Monto (Q)",            
            dataset,                
            PlotOrientation.VERTICAL, 
            true, true, false        
        );

        return new ChartPanel(barChart);
    }
}