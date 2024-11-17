
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
// import java.time.LocalDate;

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
    private JLabel balance;
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

    private Graficas graficasPanel;

    public void mostrarMenu() {
        pane = new JTabbedPane();
    
        // Pestaña 1: Gráfica
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(new Color(245, 245, 245));
        graficasPanel = new Graficas(usuario); // Instanciar Graficas con el usuario
        panel1.add(graficasPanel, BorderLayout.CENTER);
        pane.addTab("Gráfica", panel1);
    
        // Pestaña 2: Formulario y lista de artículos
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setBackground(new Color(245, 245, 245));
    
        JPanel formPanel = crearPanelFormulario();
        JPanel listaPanel = crearPanelLista();
    
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, listaPanel);
        splitPane.setDividerLocation(300);
        panel2.add(splitPane, BorderLayout.CENTER);
    
        pane.addTab("Formulario y Gastos", panel2);

        // Pestaña 3: Cuestionario de Finanzas
        CuestionarioFinanzasGUI cuestionarioPanel = new CuestionarioFinanzasGUI();
        pane.addTab("Cuestionario de Finanzas", cuestionarioPanel);
        
        frame.setContentPane(pane);
        frame.revalidate();
        frame.repaint();
    }


    private JPanel crearPanelFormulario() {
        JPanel formPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(245, 245, 245), getWidth(), getHeight(), new Color(225, 225, 235));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nombreArticuloLabel = new JLabel("Nombre del artículo:");
        nombreArticuloLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nombreArticuloLabel.setForeground(new Color(50, 50, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nombreArticuloLabel, gbc);

        nombreArticuloField = new JTextField(15);
        nombreArticuloField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nombreArticuloField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(nombreArticuloField, gbc);

        JLabel gastoArticuloLabel = new JLabel("Monto del gasto (Q):");
        gastoArticuloLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gastoArticuloLabel.setForeground(new Color(50, 50, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(gastoArticuloLabel, gbc);

        gastoArticuloField = new JTextField(15);
        gastoArticuloField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gastoArticuloField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(gastoArticuloField, gbc);

        JButton agregarGastoButton = new JButton("Agregar Gasto");
        agregarGastoButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        agregarGastoButton.setForeground(Color.WHITE);
        agregarGastoButton.setBackground(new Color(100, 149, 237));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(agregarGastoButton, gbc);

        JLabel ingresoLabel = new JLabel("Ingrese su ingreso (Q):");
        ingresoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ingresoLabel.setForeground(new Color(50, 50, 50));
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(ingresoLabel, gbc);

        ingresoField = new JTextField(15);
        ingresoField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ingresoField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(ingresoField, gbc);

        JButton ingresoButton = new JButton("Agregar Ingreso Mensual");
        ingresoButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        ingresoButton.setForeground(Color.WHITE);
        ingresoButton.setBackground(new Color(100, 149, 237));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(ingresoButton, gbc);

        
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
                    Articulo articulo = new Articulo(nombreArticulo, gastoArticulo, "Lugar");
                    usuario.agregarArticulo(articulo);
                    usuario.setGasto(usuario.getGasto() + gastoArticulo);
        
                    // Actualizar el archivo de usuarios con el nuevo gasto
                    GestorCSV.guardarUsuarios(usuarios, rutaArchivoUsuarios);
        
                    // Añadir el artículo a la lista visual
                    listaArticulosModel.addElement(nombreArticulo + " - Q" + gastoArticulo);
                    actualizarTotales();
        
                    // Actualizar la gráfica
                    graficasPanel.actualizarGrafica();
        
                    nombreArticuloField.setText("");
                    gastoArticuloField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formPanel, "Por favor, ingrese un valor numérico válido para el gasto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return formPanel;
    }

    private void actualizarTotales() {
        totalGastosLabel.setText("Total Gastos: Q" + usuario.getGasto());
        totalIngresosLabel.setText("Total Ingresos: Q" + usuario.getAhorro());
        double balanceCalculado = usuario.getAhorro() - usuario.getGasto(); 
        balance.setText("Total balance: Q" + balanceCalculado);
    }

    private JPanel crearPanelLista() {
        JPanel listaPanel = new JPanel(new BorderLayout());
        listaPanel.setBackground(new Color(245, 245, 245));

        listaArticulosModel = new DefaultListModel<>();
        JList<String> listaArticulos = new JList<>(listaArticulosModel);
        listaArticulos.setBackground(new Color(245, 245, 245));
        listaArticulos.setForeground(new Color(50, 50, 50));
        listaArticulos.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(listaArticulos);
        listaPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel totalesPanel = new JPanel(new GridLayout(3, 1));
        totalesPanel.setBackground(new Color(245, 245, 245));

        totalGastosLabel = new JLabel("Total Gastos: Q0.0");
        totalGastosLabel.setForeground(new Color(50, 50, 50));
        totalGastosLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        totalIngresosLabel = new JLabel("Total Ingresos: Q0.0");
        totalIngresosLabel.setForeground(new Color(50, 50, 50));
        totalIngresosLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        balance = new JLabel("Total balance: Q0.0");
        balance.setForeground(new Color(50, 50, 50));
        balance.setFont(new Font("Segoe UI", Font.BOLD, 14));

        totalesPanel.add(totalGastosLabel);
        totalesPanel.add(totalIngresosLabel);
        totalesPanel.add(balance);
        listaPanel.add(totalesPanel, BorderLayout.SOUTH);

        return listaPanel;
    }
    @SuppressWarnings("unused")
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