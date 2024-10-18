package com.budget.buddy;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
