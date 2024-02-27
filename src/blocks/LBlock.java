package blocks;

import java.awt.*;

import static main.GamePanel.cellArray;

public class LBlock extends Block {

    public LBlock() {
        super();
        id = lastId++;

        blockCells.add(cellArray[1][6]);
        blockCells.add(cellArray[2][6]);
        blockCells.add(cellArray[3][6]);
        blockCells.add(cellArray[3][7]);

        for (Cell cell : blockCells) {
            cell.cellId = id;
            cell.setColor(Colors.ORANGE.color);
        }
    }
}
