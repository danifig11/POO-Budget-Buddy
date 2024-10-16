import javax.swing.*;


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
        frame.setContentPane(loginPanel); // Cambia el contenido a LogInGUI
        frame.setVisible(true);
    }

    public void mostrarMenu() {
        pane = new JTabbedPane(); // Inicializar el pane antes de usarlo
        // Aquí puedes añadir diferentes pestañas al pane
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Panel 1"));
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Panel 2"));
        
        pane.addTab("Pestaña 1", panel1);
        pane.addTab("Pestaña 2", panel2);

        frame.setContentPane(pane); // Cambiar el contenido a las pestañas
        frame.revalidate();
        frame.repaint();
    }
}