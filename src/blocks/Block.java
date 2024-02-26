package blocks;

import main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Block {
    public ArrayList<Cell> cells;
    static int lastId = 0;
    public int id;
    private Color color;

    Block(Color color) {
        this.color = color;
        cells = new ArrayList<>();

    }

    public void draw(Cell[][] cellArray) {
        for (Cell cell : cells) {
            cellArray[cell.y][cell.x].setColor(cell.getColor());
        }
    }

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
                        erasePosition();
                        cells = getUpdatedCellPositions(cells, -1);
                    }
                }
            }
            case "RIGHT" -> {
                for (Cell cell : cells) {
                    boolean isLastCell = cells.get(cells.size() - 1) == cell;

                    // checks if at edge
                    if (cell.x >= cellArray[0].length - 1) return;

                    if ((cellArray[cell.y][cell.x + 1].cellId == id || cellArray[cell.y][cell.x + 1].cellId == -1) && !isLastCell) {
                        continue;
                    }

                    if ((cellArray[cell.y][cell.x + 1].cellId != id) && (cellArray[cell.y][cell.x + 1].cellId != -1)) {
                        return;
                    }

                    if (isLastCell) {
                        erasePosition();
                        cells = getUpdatedCellPositions(cells, 1);
                    }
                }
            }
        }
    }

    private void erasePosition() {
        Cell[][] cellArray = GamePanel.getCellArray();
        for (Cell cell : cells) {
            Cell panelCell = cellArray[cell.y][cell.x];
            panelCell.cellId = -1;
            panelCell.setColor(Color.WHITE);
        }
    }

    private ArrayList<Cell> getUpdatedCellPositions(List<Cell> cells, int offset) {
        Cell[][] cellArray = GamePanel.getCellArray();
        ArrayList<Cell> newCells = new ArrayList<>();
        for (Cell cell : cells) {
            newCells.add(cellArray[cell.y][cell.x + offset]);
            cellArray[cell.y][cell.x + offset].cellId = id;
            cellArray[cell.y][cell.x + offset].setColor(color);
        }
        return newCells;
    }

    public void moveDown(Cell[][] cellArray) {
        int size = cells.size() - 1;
        ArrayList<Cell> newCells = new ArrayList<>();

        for (int i=size; i>=0; i--) {

            // add cells to new array
            newCells.add(cellArray[cells.get(i).y + 1][cells.get(i).x]);

            // erases old cells
            cellArray[cells.get(i).y][cells.get(i).x].setColor(Color.WHITE);
            cellArray[cells.get(i).y][cells.get(i).x].cellId = -1;

            // removes old cells from cells array
            Cell cellToRemove = cellArray[cells.get(i).y][cells.get(i).x];
            cells.remove(cellToRemove);
        }
        cells = newCells;
        Collections.reverse(cells);

        for (Cell i : cells) {
            cellArray[i.y][i.x].cellId = id;
            cellArray[i.y][i.x].setColor(Color.RED);
        }
    }
}
