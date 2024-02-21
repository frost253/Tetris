package main;

import javax.swing.*;

public class StartPanel extends JPanel {
    int x = 0;
    int y = 0;
    int width = 500;
    int height = 1000;
    JTextField textField;
    JButton play;
    JButton exit;

    StartPanel() {
        setBounds(x, y, width, height);

    }
}
