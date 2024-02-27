package blocks;

import java.awt.*;

import static main.GamePanel.cellArray;

public class IBlock extends Block {

    public IBlock() {
        super();
        id = lastId++;

        blockCells.add(cellArray[0][6]);
        blockCells.add(cellArray[0][7]);
        blockCells.add(cellArray[0][8]);
        blockCells.add(cellArray[0][9]);

        for (Cell cell : blockCells) {
            cell.cellId = id;
            cell.setColor(Colors.LIGHT_BLUE.color);
        }
    }
}
