package blocks;

import java.awt.*;

import static main.GamePanel.cellArray;

public class LBlock extends Block {
    Color color;

    public LBlock() {
        super();
        color = new Color(17, 17, 238);
        id = lastId++;

        blockCells.add(cellArray[1][6]);
        blockCells.add(cellArray[2][6]);
        blockCells.add(cellArray[3][6]);
        blockCells.add(cellArray[3][7]);

        for (Cell i : blockCells) {
            i.cellId = id;
            i.setColor(color);
        }
    }
}
