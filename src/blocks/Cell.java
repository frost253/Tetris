package blocks;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;

import main.GamePanel;

public class Cell {
    public int x, y;
    public int cellId;
    private final JLabel label;
    private final JPanel panel;
    private final Color blank;
    public Cell(int y, int x, int cellId) {
        Random random = new Random();

        // Generate random values for Red, Green, and Blue components
        int red = random.nextInt(256);    // Random value between 0 (inclusive) and 256 (exclusive)
        int green = random.nextInt(256);  // Random value between 0 (inclusive) and 256 (exclusive)
        int blue = random.nextInt(256);   // Random value between 0 (inclusive) and 256 (exclusive)

        // Create a Color object with the random RGB values
        color = new Color(red, green, blue);

        blank = Color.WHITE;
        label = new JLabel();
        panel.add(label);
        this.y = y;
        this.x = x;

        this.cellId = cellId;
    }

    public void moveDown() {
        Cell oldPos = GamePanel.getCellAt(y, x);
        Cell newPos = GamePanel.getCellAt(y + 1, x);

        newPos.cellId = cellId;
        newPos.setColor(getColor());

        oldPos.cellId = -1;
        oldPos.setColor(blank);
    }

    public void setColor(Color color) {
        panel.setBackground(color);
    }

    public Color getColor() {
        return panel.getBackground();
    }
    public JLabel getLabel() {
        return label;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setBorder(Border border) {
        panel.setBorder(border);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y && cellId == cell.cellId;
    }
}
