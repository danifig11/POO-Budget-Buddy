import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    private JTabbedPane pane;
    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI main = new MainGUI();
            main.showLogIn();
        });
    }

    public MainGUI() {
        frame = new JFrame("Budget_Buddy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Tamaño de la ventana
        frame.setLocationRelativeTo(null); // Centrar la ventana
    }

    public void showLogIn() {
        // JPanel de LogIn se añade al JFrame
        LogInGUI loginPanel = new LogInGUI(this);
        frame.setContentPane(loginPanel); // Cambiar el contenido a LogInGUI
        frame.setVisible(true);
    }

    public void mostrarMenu() {
        pane = new JTabbedPane(); // Inicializar el pane antes de usarlo

        // Pestaña 1 (Puede tener cualquier contenido)
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Panel 1"));
        pane.addTab("Pestaña 1", panel1);

        // Pestaña 2 (Formulario para calcular gastos)
        JPanel panel2 = new JPanel(new GridBagLayout()); // Usamos GridBagLayout para un diseño flexible
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre componentes

        // Etiqueta y campo para ingresar los gastos semanales
        JLabel gastosLabel = new JLabel("Ingrese sus gastos semanales (Q): ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(gastosLabel, gbc);

        JTextField gastosField = new JTextField(10); // Campo de texto para gastos
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel2.add(gastosField, gbc);

        // Etiqueta y campo para ingresar el presupuesto semanal
        JLabel presupuestoLabel = new JLabel("Ingrese su presupuesto semanal (Q): ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(presupuestoLabel, gbc);

        JTextField presupuestoField = new JTextField(10); // Campo de texto para presupuesto
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel2.add(presupuestoField, gbc);

        // Botón para calcular el dinero restante
        JButton calcularButton = new JButton("Calcular");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Hacer que el botón ocupe dos columnas
        gbc.anchor = GridBagConstraints.CENTER;
        panel2.add(calcularButton, gbc);

        // Acción al hacer clic en el botón calcular
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Leer los valores ingresados por el usuario
                    double gastos = Double.parseDouble(gastosField.getText());
                    double presupuesto = Double.parseDouble(presupuestoField.getText());

                    // Calcular el dinero restante
                    double restante = presupuesto - gastos;

                    // Mostrar el dinero restante en un JOptionPane
                    JOptionPane.showMessageDialog(panel2, "Te queda Q" + restante + " esta semana.");

                    // Comprobar si los gastos superan el 70% del presupuesto
                    if (gastos >= presupuesto * 0.7) {
                        JOptionPane.showMessageDialog(panel2, "Advertencia: Cuidado con tus gastos, ya has usado más del 70% de tu presupuesto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // Si el usuario ingresa valores no válidos, mostrar un mensaje de error
                    JOptionPane.showMessageDialog(panel2, "Por favor ingresa números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Añadir la pestaña al JTabbedPane
        pane.addTab("Pestaña 2", panel2);

        frame.setContentPane(pane); // Cambiar el contenido a las pestañas
        frame.revalidate();
        frame.repaint();
    }
}