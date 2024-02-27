package blocks;

import java.awt.*;

import static main.GamePanel.cellArray;

public class ZBlock extends Block {
    Color color;

    public ZBlock() {
        super();
        color = new Color(38, 243, 58);
        id = lastId++;

        blockCells.add(cellArray[0][6]);
        blockCells.add(cellArray[0][7]);
        blockCells.add(cellArray[1][7]);
        blockCells.add(cellArray[1][8]);

        for (Cell cell : blockCells) {
            cell.cellId = id;
            cell.setColor(color);
        }
    }
}
