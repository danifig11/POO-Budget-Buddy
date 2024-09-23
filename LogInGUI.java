import javax.swing.*;


public class LogInGUI {
    //Por lo que entiendo este debería ser el Main porque es la interfaz que se presentará al Usuario
    //Todo va a ser pura prueba y error en esta cosa, por eso el Branch jiji

    public static void main(String[] args) {

        JPanel panel = new JPanel();
        JFrame ventana = new JFrame();
        ventana.setSize(500, 200);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true); 
        ventana.add(panel);

        panel.setLayout(null);

        JLabel tag = new JLabel("Usuario");
        tag.setBounds(10, 20, 80, 25);
        panel.add(tag);


    }
}
