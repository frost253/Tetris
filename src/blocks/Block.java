package blocks;

import main.GamePanel;
import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Block {
    private final Color fill;
    public List<Cell> cells;
    static int lastId = 0;
    public int id;

    Block() {
        Random random = new Random();

        // Generate random values for Red, Green, and Blue components
        int red = random.nextInt(256);    // Random value between 0 (inclusive) and 256 (exclusive)
        int green = random.nextInt(256);  // Random value between 0 (inclusive) and 256 (exclusive)
        int blue = random.nextInt(256);   // Random value between 0 (inclusive) and 256 (exclusive)

        // Create a Color object with the random RGB values
        fill = new Color(red, green, blue);
        cells = new ArrayList<>();

    }

    public void draw(Cell[][] cellArray) {
        for (Cell i : cells) {
            cellArray[i.y][i.x].setBackground(fill);
        }
    }

    public abstract void rotate();

    public void shift(String direction, Cell[][] cellArray) {
        switch (direction) {
            case "LEFT" -> {
                for (Cell i : cells) {
                    boolean isLastCell = cells.get(cells.size() - 1) == i;

                    // checks if at edge
                    if (i.x <= 0) return;

                    if ((cellArray[i.y][i.x - 1].cellId == id || cellArray[i.y][i.x - 1].cellId == -1) && !isLastCell) {
                        continue;
                    }

                    if ((cellArray[i.y][i.x - 1].cellId != id) && (cellArray[i.y][i.x - 1].cellId != -1)) {
                        return;
                    }

                    if (isLastCell) {
                        // erase current position
                        for (Cell j : cells) {
                            cellArray[j.y][j.x].cellId = -1;
                            cellArray[j.y][j.x].setBackground(GamePanel.blank);
                        }

                        // updates the position
                        List<Cell> newCells = new ArrayList<>();
                        for (Cell j : cells) {
                            newCells.add(cellArray[j.y][j.x - 1]);
                            cellArray[j.y][j.x - 1].cellId = id;
                            cellArray[j.y][j.x - 1].setBackground(fill);
                            cells = newCells;
                        }
                    }
                }
            }
            case "RIGHT" -> {
                for (Cell i : cells) {
                    boolean isLastCell = cells.get(cells.size() - 1) == i;

                    // checks if at edge
                    if (i.x >= cellArray[0].length - 1) return;

                    if ((cellArray[i.y][i.x + 1].cellId == id || cellArray[i.y][i.x + 1].cellId == -1) && !isLastCell) {
                        continue;
                    }

                    if ((cellArray[i.y][i.x + 1].cellId != id) && (cellArray[i.y][i.x + 1].cellId != -1)) {
                        return;
                    }

                    if (isLastCell) {
                        // erase current position
                        for (Cell j : cells) {
                            cellArray[j.y][j.x].cellId = -1;
                            cellArray[j.y][j.x].setBackground(GamePanel.blank);
                        }

                        // updates the position
                        List<Cell> newCells = new ArrayList<>();
                        for (Cell j : cells) {
                            newCells.add(cellArray[j.y][j.x + 1]);
                            cellArray[j.y][j.x + 1].cellId = id;
                            cellArray[j.y][j.x + 1].setBackground(fill);
                            cells = newCells;
                        }
                    }
                }
            }
        }
    }

    public void moveDown(Cell[][] cellArray) {
        int size = cells.size() - 1;
        List<Cell> newCells = new ArrayList<>();

        for (int i=size; i>=0; i--) {

            // add cells to new array
            newCells.add(cellArray[cells.get(i).y + 1][cells.get(i).x]);

            // erases old cells
            cellArray[cells.get(i).y][cells.get(i).x].setBackground(GamePanel.blank);
            cellArray[cells.get(i).y][cells.get(i).x].cellId = -1;

            // removes old cells from cells array
            Cell cellToRemove = cellArray[cells.get(i).y][cells.get(i).x];
            cells.remove(cellToRemove);
        }
        cells = newCells;
        Collections.reverse(cells);

        for (int i=0; i<cells.size()-1; i++) {
            cellArray[cells.get(i).y][cells.get(i).x].cellId = id;
        }
    }
}
