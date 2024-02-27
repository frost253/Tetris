package blocks;

import java.awt.*;
import java.util.ArrayList;

public abstract class Block {
    public ArrayList<Cell> blockCells;
    static int lastId = 0;
    public int id;

    Block() {
        blockCells = new ArrayList<>();
    }

    public void move(Cell[][] cellArray, String direction) {

        //System.out.println(ogColor.toString());
        int var1, var2;

        switch (direction) {
            case "down":
                var1 = 1;
                var2 = 0;
                break;

            case "left":
                for (Cell i : blockCells) {
                    boolean isLastCell = blockCells.get(blockCells.size() - 1) == i;

                    // checks if at edge
                    if (i.x <= 0) return;

                    if ((cellArray[i.y][i.x - 1].cellId == id || cellArray[i.y][i.x - 1].cellId == -1) && !isLastCell)
                        continue;

                    // checks if block to right is different cell
                    if ((cellArray[i.y][i.x - 1].cellId != id) && (cellArray[i.y][i.x - 1].cellId != -1))
                        return;
                }
                var1 = 0;
                var2 = -1;
                break;

            case "right":
                for (Cell cell : blockCells) {
                    boolean isLastCell = blockCells.get(blockCells.size() - 1) == cell;

                    // checks if at edge
                    if (cell.x >= cellArray[0].length - 1) return;

                    if ((cellArray[cell.y][cell.x + 1].cellId == id || cellArray[cell.y][cell.x + 1].cellId == -1) && !isLastCell)
                        continue;

                    if ((cellArray[cell.y][cell.x + 1].cellId != id) && (cellArray[cell.y][cell.x + 1].cellId != -1))
                        return;
                }
                var1 = 0;
                var2 = 1;
                break;

            default:
                var1 = 0;
                var2 = 0;
                break;
        }

        ArrayList<Cell> newCells = new ArrayList<>();
        Color ogColor = blockCells.get(0).getColor();
        System.out.println(ogColor.toString());
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
            cell.setColor(ogColor);
        }
    }
    public boolean isOnLastRow() {
        for (Cell cell : blockCells) {
            if (cell.y == 39) return true;
        }
        return false;
    }

}