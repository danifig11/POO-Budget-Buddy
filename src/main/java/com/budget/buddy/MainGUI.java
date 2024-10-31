
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
 
     private double gastosMensuales = 0;
     private double presupuestoMensual = 0;
 
     private JTextField gastosField;
     private JTextField presupuestoField;
 
     private DefaultListModel<String> listaArticulosModel;
     private JLabel totalGastosLabel;
     private JLabel totalIngresosLabel;
 
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
         
         usuario = new Usuario("Ejemplo", 0, "contraseña123"); // Usuario de ejemplo
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
 
         JLabel gastosLabel = new JLabel("Ingrese sus gastos mensuales (Q): ");
         gastosLabel.setForeground(Color.WHITE);
         gastosLabel.setFont(new Font("Arial", Font.PLAIN, 14));
         gbc.gridx = 0;
         gbc.gridy = 0;
         formPanel.add(gastosLabel, gbc);
 
         gastosField = new JTextField(20);
         gbc.gridy = 1;
         formPanel.add(gastosField, gbc);
 
         JLabel presupuestoLabel = new JLabel("Ingrese su presupuesto mensual (Q): ");
         presupuestoLabel.setForeground(Color.WHITE);
         presupuestoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
         gbc.gridy = 2;
         formPanel.add(presupuestoLabel, gbc);
 
         presupuestoField = new JTextField(20);
         gbc.gridy = 3;
         formPanel.add(presupuestoField, gbc);
 
         JButton calcularButton = new JButton("Agregar Gasto");
         calcularButton.setBackground(new Color(75, 110, 175));
         calcularButton.setForeground(Color.WHITE);
         calcularButton.setFont(new Font("Arial", Font.BOLD, 14));
         gbc.gridy = 4;
         formPanel.add(calcularButton, gbc);
 
         calcularButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 try {
                     double gasto = Double.parseDouble(gastosField.getText());
                     double presupuesto = Double.parseDouble(presupuestoField.getText());
 
                     presupuestoMensual = presupuesto;
                     gastosMensuales += gasto;
 
                     // Crear un artículo y añadirlo al usuario
                     Articulo articulo = new Articulo("Artículo " + (usuario.getArticulos().size() + 1), gasto, "Lugar", "Fecha");
                     usuario.agregarArticulo(articulo);
 
                     // Añadir el artículo a la lista visual
                     listaArticulosModel.addElement(articulo.getNombre() + " - Q" + articulo.getPrecio());
 
                     // Actualizar etiquetas de totales
                     actualizarTotales();
 
                     gastosField.setText("");
                 } catch (NumberFormatException ex) {
                     JOptionPane.showMessageDialog(formPanel, "Por favor ingrese un valor numérico válido para los gastos y el presupuesto.", "Error", JOptionPane.ERROR_MESSAGE);
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
         totalGastosLabel.setText("Total Gastos: Q" + gastosMensuales);
         totalIngresosLabel.setText("Total Ingresos: Q" + presupuestoMensual);
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