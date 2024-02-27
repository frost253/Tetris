package blocks;

import java.awt.*;

import static main.GamePanel.cellArray;

public class SBlock extends Block {

    public SBlock() {
        super();
        id = lastId++;

        blockCells.add(cellArray[0][6]);
        blockCells.add(cellArray[0][7]);
        blockCells.add(cellArray[1][5]);
        blockCells.add(cellArray[1][6]);

        for (Cell cell : blockCells) {
            cell.cellId = id;
            cell.setColor(Colors.RED.color);
        }
    }
}
