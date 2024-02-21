package main;

import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame {
    private final JPanel container;
    private final String title = "Tetris";
    private final int width = 516; // use pack() later
    private final int height = 1039; // use pack() later

    static GamePanel gamePanel = new GamePanel();

    Tetris() {
        setSize(width, height);
        setLocationRelativeTo(null);
        setTitle(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        container = (JPanel) this.getContentPane();
        container.setBounds(0,0,500,1000);
        container.setLayout(new CardLayout());
        container.add(new StartPanel(), "startPanel");
        container.add(gamePanel, "gamePanel");
        container.add(new EndPanel(), "endPanel");

        showGamePanel();
        setVisible(true);

        gamePanel.gameLoop();
    }

    public static void main(String[] args) {
        new Tetris();
    }

    private void showStartPanel() {
        ((CardLayout) container.getLayout()).show(container, "startPanel");
    }

    private void showGamePanel() {
        ((CardLayout) container.getLayout()).show(container, "gamePanel");
    }

    private void showEndPanel() {
        ((CardLayout) container.getLayout()).show(container, "endPanel");
    }

    public static GamePanel gamePanel() {
        return gamePanel;
    }
}
