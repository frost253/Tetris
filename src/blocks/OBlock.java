package blocks;

import java.awt.*;

import static main.GamePanel.cellArray;

public class OBlock extends Block {
    Color color;

    public OBlock() {
        super();
        color = new Color(252, 241, 94);
        id = lastId++;

        blockCells.add(cellArray[0][6]);
        blockCells.add(cellArray[0][7]);
        blockCells.add(cellArray[1][6]);
        blockCells.add(cellArray[1][7]);

        for (Cell cell : blockCells) {
            cell.cellId = id;
            cell.setColor(color);
        }
    }
}
