import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class LogInGUI {
    //Por lo que entiendo este debería ser el Main porque es la interfaz que se presentará al Usuario
    //Todo va a ser pura prueba y error en esta cosa, por eso el Branch jiji

    public static void main(String[] args) {

        JPanel panel = new JPanel();
        JFrame ventana = new JFrame();
        ventana.setSize(600, 250);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        ventana.add(panel);

        panel.setLayout(null);

        //Ingresar el usuario, después cambio el tag
        JLabel tag = new JLabel("Usuario");
        tag.setBounds(10, 20, 80, 20);
        panel.add(tag);
        //Esta weonada se supone que lee texto pero no sé cómo guardar el imput aún
        JTextField textoUsuario = new JTextField(20);
        textoUsuario.setBounds(100, 20, 165, 25);
        panel.add(textoUsuario);

        //Aquí la contraseña
        JLabel tagContrasena = new JLabel("Contraseña");
        tagContrasena.setBounds(10, 50, 80, 20);
        panel.add(tagContrasena);

        JPasswordField password = new JPasswordField(); //En vez de que sea texto, son puntitos que lindo uwu
        password.setBounds(100, 50, 165, 25);
        panel.add(password);


        
        
        
        ventana.setVisible(true);

    }
}
