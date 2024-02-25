package blocks;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;

import main.GamePanel;
// import blocks.Direction;

public class Cell {
    JPanel panel;
    public int x, y;
    public int cellId;
    private JLabel label;
    private Color color;
    public Cell(int y, int x, int cellId) {
        panel = new JPanel();
        this.cellId = cellId;
        label = new JLabel();
        panel.add(label);
        this.y = y;
        this.x = x;
        Random random = new Random();

        // Generate random values for Red, Green, and Blue components
        int red = random.nextInt(256);    // Random value between 0 (inclusive) and 256 (exclusive)
        int green = random.nextInt(256);  // Random value between 0 (inclusive) and 256 (exclusive)
        int blue = random.nextInt(256);   // Random value between 0 (inclusive) and 256 (exclusive)

        // Create a Color object with the random RGB values
        color = new Color(red, green, blue);

        // don't like this
        if (cellId == -1) color = Color.WHITE;
    }

	public void setId(int id) {
		this.cellId = id;
	}

	public Cell copyToAdjacentCell(Direction direction) {
		Cell adjacentCell;

		switch (direction) {
			case LEFT:
				// TODO Implement LEFT case
				return null;
			case RIGHT:
				// TODO Implement RIGHT case
				return null;
			case UP:
				// TODO Implement UP case
				return null;
			case DOWN:
				adjacentCell = GamePanel.getCellAt(y+1, x);
				break;
			default:
				return null; // TODO Handle an invalid direction instead of returning null.
		}

		// Assign this cells fields to the adjacent cell.
		adjacentCell.setColor(color);
		adjacentCell.setId(cellId);

		return adjacentCell;
	}

    public void shiftDown() {
        Cell oldPos = GamePanel.getCellAt(y, x);
        Cell newPos = GamePanel.getCellAt(y + 1, x);

        newPos.cellId = cellId;
        newPos.setColor(color);

        oldPos.cellId = -1;
        oldPos.setColor(Color.WHITE);
    }

    public void setColor(Color color) {
        if (color == Color.WHITE) return;
        this.color = color;
        panel.setBackground(color);
    }

    public Color getColor() {
        return color;
    }
    public JLabel getLabel() {
        return this.label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y && cellId == cell.cellId;
    }

    public void setBorder(Border border) {
        panel.setBorder(border);
    }

    public Component getPanel() {
        return panel;
    }
}
