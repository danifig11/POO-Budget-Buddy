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

    public MainGUI(){
        frame = new JFrame("Budget_Buddy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Tamaño de la ventana
        frame.setLocationRelativeTo(null); // Centrar la ventana
    }

    public void showLogIn(){
        //JPanel de LogIn se añade al JFrame
        LogInGUI LogIn = new LogInGUI(this);
        frame.setContentPane(LogIn);
        frame.setVisible(true);
    }

    public void mostrarMenu(){
        frame.setContentPane(pane);
        frame.revalidate();
        frame.repaint();
    }




}
