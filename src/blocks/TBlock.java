package blocks;

import java.awt.*;

import static main.GamePanel.cellArray;

public class TBlock extends Block {
    Color color;

    public TBlock() {
        super();
        color = new Color(146, 30, 213);
        id = lastId++;

        blockCells.add(cellArray[0][6]);
        blockCells.add(cellArray[0][7]);
        blockCells.add(cellArray[0][8]);
        blockCells.add(cellArray[1][7]);

        for (Cell cell : blockCells) {
            cell.cellId = id;
            cell.setColor(color);
        }
    }
}
