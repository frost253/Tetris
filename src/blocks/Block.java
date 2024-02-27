package blocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Block {
    public ArrayList<Cell> blockCells;
    static int lastId = 0;
    public int id;

    Block(Color color) {
        blockCells = new ArrayList<>();
    }

    public void shift(String direction, Cell[][] cellArray) {
        switch (direction) {
            case "LEFT" -> {
                for (Cell i : blockCells) {
                    boolean isLastCell = blockCells.get(blockCells.size() - 1) == i;

                    // checks if at edge
                    if (i.x <= 0) return;

                    if ((cellArray[i.y][i.x - 1].cellId == id || cellArray[i.y][i.x - 1].cellId == -1) && !isLastCell) {
                        continue;
                    }

                    if ((cellArray[i.y][i.x - 1].cellId != id) && (cellArray[i.y][i.x - 1].cellId != -1)) {
                        return;
                    }

                    if (isLastCell) {
                        ArrayList<Cell> newCells = new ArrayList<>();
                        for (Cell cell : blockCells) {
                            newCells.add(cellArray[cell.y][cell.x - 1]);
                            cellArray[cell.y][cell.x - 1].cellId = id;
                            cellArray[cell.y][cell.x - 1].setColor(cell.getColor());
                            blockCells = newCells;
                        }
                        // erase current position
                        for (Cell cell : blockCells) {
                            cellArray[cell.y][cell.x].cellId = -1;
                            cellArray[cell.y][cell.x].setColor(Color.WHITE);
                        }
                    }
                }
            }
            case "RIGHT" -> {
                for (Cell cell : blockCells) {
                    boolean isLastCell = blockCells.get(blockCells.size() - 1) == cell;

                    // checks if at edge
                    if (cell.x >= cellArray[0].length - 1) return;

                    if ((cellArray[cell.y][cell.x + 1].cellId == id || cellArray[cell.y][cell.x + 1].cellId == -1) && !isLastCell) {
                        continue;
                    }

                    if ((cellArray[cell.y][cell.x + 1].cellId != id) && (cellArray[cell.y][cell.x + 1].cellId != -1)) {
                        return;
                    }

                    if (isLastCell) {
                        // erase current position
                        for (Cell j : blockCells) {
                            cellArray[j.y][j.x].cellId = -1;
                            cellArray[j.y][j.x].setColor(Color.WHITE);
                        }

                        // updates the position
                        ArrayList<Cell> newCells = new ArrayList<>();
                        for (Cell j : blockCells) {
                            newCells.add(cellArray[j.y][j.x + 1]);
                            cellArray[j.y][j.x + 1].cellId = id;
                            cellArray[j.y][j.x + 1].setColor(j.getColor());
                            blockCells = newCells;
                        }
                    }
                }
            }
        }
    }

    public void moveDown(Cell[][] cellArray, String direction) {
        int var1, var2;
        boolean bool1, bool2, bool3, bool4;

        switch (direction) {
            case "down":
                var1 = 1;
                var2 = 0;
            break;

            case "left":
                var1 = 0;
                var2 = -1;

                for (Cell i : blockCells) {
                    boolean isLastCell = blockCells.get(blockCells.size() - 1) == i;

                    // checks if at edge
                    if (i.x <= 0) bool1 = true;

                    if ((cellArray[i.y][i.x - 1].cellId == id || cellArray[i.y][i.x - 1].cellId == -1) && !isLastCell) {
                        bool2 = true;
                    }

                    if ((cellArray[i.y][i.x - 1].cellId != id) && (cellArray[i.y][i.x - 1].cellId != -1)) {
                        bool3 = true;
                    }
            break;

            case "right":
                var1 = 0;
                var2 = 1;
                break;
            default:
                var1 = 0;
                var2 = 0;
        }

            ArrayList<Cell> newCells = new ArrayList<>();

            for (int i = blockCells.size() - 1; i >= 0; i--) {

                // add cells to new array
                newCells.add(cellArray[blockCells.get(i).y + var1][blockCells.get(i).x + var2]);

                // erases old cells
                cellArray[blockCells.get(i).y][blockCells.get(i).x].setColor(Color.WHITE);
                cellArray[blockCells.get(i).y][blockCells.get(i).x].cellId = -1;

                // removes old cells from cells array
                Cell cellToRemove = cellArray[blockCells.get(i).y][blockCells.get(i).x];
                blockCells.remove(cellToRemove);
            }
            blockCells = newCells;

            for (Cell cell : blockCells) {
                cellArray[cell.y][cell.x].cellId = id;
                cell.setColor(Color.BLUE);
            }
    }
}
