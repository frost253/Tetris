package blocks;

import java.awt.*;

import static main.GamePanel.cellArray;

public class JBlock extends Block {
    Color color;

    public JBlock() {
        super();
        color = new Color(248, 66, 255);
        id = lastId++;

        blockCells.add(cellArray[0][6]);
        blockCells.add(cellArray[1][6]);
        blockCells.add(cellArray[2][6]);
        blockCells.add(cellArray[2][5]);

        for (Cell cell : blockCells) {
            cell.cellId = id;
            cell.setColor(color);
        }
    }
}
