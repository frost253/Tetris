package blocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Block {
    public ArrayList<Cell> blockCells;
    static int lastId = 0;
    public int id;

    Block() {

        blockCells = new ArrayList<>();

    }

//    public void draw(Cell[][] cellArray) {
//        for (Cell i : blockCells) {
//            cellArray[i.y][i.x].setColor(i.getColor());
//        }
//    }

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
                        // erase current position
                        for (Cell cell : blockCells) {
                            cellArray[cell.y][cell.x].cellId = -1;
                            cellArray[cell.y][cell.x].setColor(Color.WHITE);
                        }

                        // updates the position
                        ArrayList<Cell> newCells = new ArrayList<>();
                        for (Cell cell : blockCells) {
                            newCells.add(cellArray[cell.y][cell.x - 1]);
                            cellArray[cell.y][cell.x - 1].cellId = id;
                            cellArray[cell.y][cell.x - 1].setColor(cell.getColor());
                            blockCells = newCells;
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

    public void moveDown(Cell[][] cellArray) {
        ArrayList<Cell> newCells = new ArrayList<>();

        for (int i=blockCells.size()-1; i>=0; i--) {

            // add cells to new array
            newCells.add(cellArray[blockCells.get(i).y + 1][blockCells.get(i).x]);

            // erases old cells
            cellArray[blockCells.get(i).y][blockCells.get(i).x].setColor(Color.WHITE);
            cellArray[blockCells.get(i).y][blockCells.get(i).x].cellId = -1;

            // removes old cells from cells array
            Cell cellToRemove = cellArray[blockCells.get(i).y][blockCells.get(i).x];
            blockCells.remove(cellToRemove);
        }
        blockCells = newCells;
        Collections.reverse(blockCells);

        for (Cell i : blockCells) {
            cellArray[i.y][i.x].cellId = id;
        }
    }
}
