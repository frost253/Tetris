package main;

import javax.swing.*;
import java.awt.*;

public class EndPanel extends JPanel {
    int x = 0;
    int y = 0;
    int width = 500;
    int height = 1000;

    EndPanel() {
        setBounds(x, y, width, height);
        setBackground(Color.RED);
    }
}
