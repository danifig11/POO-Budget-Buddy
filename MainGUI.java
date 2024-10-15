import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainGUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LogInGUI LogInGUI;
    private NewUserGUI NewUserGUI;
    private MainPanel mainContentPanel;

    BufferedReader md = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Usuario> usuarios = GestorCSV.cargarUsuarios("usuarios.csv"); // Cargar usuarios desde CSV
    boolean logueado = false;
    String rutaArchivo = "usuarios.csv"; 
    

    public MainGUI() {
        // Configurar el JFrame
        frame = new JFrame("Sistema de Gestión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        // Usar CardLayout para intercambiar entre paneles
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Crear los diferentes paneles
        LogInGUI = new LogInGUI();
        NewUserGUI = new NewUserGUI();
        mainContentPanel = new MainPanel();

        // Añadir paneles al CardLayout
        mainPanel.add(LogInGUI, "login");
        mainPanel.add(NewUserGUI, "newUser");
        mainPanel.add(mainContentPanel, "mainContent");

        // Listener para el botón de login
        LogInGUI.addLoginListener(e -> {
            // Aquí deberías añadir validación real para el login
            cardLayout.show(mainPanel, "mainContent");
        });

        // Listener para el botón de crear nuevo usuario
        LogInGUI.addNewUserListener(e -> cardLayout.show(mainPanel, "newUser"));

        // Listener para el botón de regreso al login desde NewUserGUI
        NewUserGUI.addBackListener(e -> cardLayout.show(mainPanel, "login"));

        // Añadir el mainPanel al frame y mostrarlo
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Ejecutar la aplicación
        SwingUtilities.invokeLater(Main::new);
    }
}

