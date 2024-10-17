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

    public void showNewUserPanel() {
        // Cambiar al panel de creación de usuario
        NewUserGUI newUserPanel = new NewUserGUI(this); // Pasar la instancia de MainGUI
        frame.setContentPane(newUserPanel); // Cambiar el contenido a NewUserGUI
        frame.revalidate();
        frame.repaint();
    }


    public void mostrarMenu() {
        pane = new JTabbedPane(); // Inicializar el pane antes de usarlo

        // Pestaña 1
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(45, 45, 48)); // Fondo oscuro para coincidir con la otra pestaña
        panel1.setLayout(new GridBagLayout()); // Usamos GridBagLayout para centrar el texto

        // Etiqueta con el mensaje
        JLabel messageLabel = new JLabel("Para ingresar tus datos, ve a la pestaña 2");
        messageLabel.setForeground(Color.WHITE); // Color del texto blanco para que contraste con el fondo
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente más grande y negrita

        // Configuramos la posición de la etiqueta
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado alrededor de la etiqueta
        panel1.add(messageLabel, gbc); // Añadir la etiqueta al panel

        pane.addTab("Pestaña 1", panel1); // Añadir la pestaña 1

        // Pestaña 2 (Formulario para calcular gastos)
        JPanel panel2 = new JPanel(new BorderLayout(10, 10));
        panel2.setBackground(new Color(45, 45, 48)); // Fondo oscuro

        // Panel para el formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(60, 63, 65)); // Fondo del formulario

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Que las componentes se expandan horizontalmente
        gbc.anchor = GridBagConstraints.WEST;

        // Etiqueta de gastos semanales
        JLabel gastosLabel = new JLabel("Ingrese sus gastos semanales (Q): ");
        gastosLabel.setForeground(Color.WHITE);
        gastosLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(gastosLabel, gbc);

        // Campo de texto para gastos (debajo de la etiqueta)
        JTextField gastosField = new JTextField(20); // Más grande
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(gastosField, gbc);

        // Etiqueta de presupuesto semanal
        JLabel presupuestoLabel = new JLabel("Ingrese su presupuesto semanal (Q): ");
        presupuestoLabel.setForeground(Color.WHITE);
        presupuestoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(presupuestoLabel, gbc);

        // Campo de texto para presupuesto (debajo de la etiqueta)
        JTextField presupuestoField = new JTextField(20); // Más grande
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(presupuestoField, gbc);

        // Añadir el formulario al panel principal
        panel2.add(formPanel, BorderLayout.CENTER);

        // Panel para el botón de cálculo
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(45, 45, 48));

        JButton calcularButton = new JButton("Calcular");
        calcularButton.setBackground(new Color(75, 110, 175));
        calcularButton.setForeground(Color.WHITE);
        calcularButton.setFocusPainted(false); // Quitar el borde de enfoque
        calcularButton.setFont(new Font("Arial", Font.BOLD, 14));
        calcularButton.setPreferredSize(new Dimension(150, 40));
        buttonPanel.add(calcularButton);

        // Añadir el botón en la parte inferior del panel principal
        panel2.add(buttonPanel, BorderLayout.SOUTH);

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