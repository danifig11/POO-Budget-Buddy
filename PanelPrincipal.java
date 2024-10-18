import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {
    public PanelPrincipal() {
        setLayout(new BorderLayout());
        setBackground(new Color(60, 63, 65));

        JLabel welcomeLabel = new JLabel("Bienvenido al Panel Principal");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        add(welcomeLabel, BorderLayout.CENTER);
    }
}
