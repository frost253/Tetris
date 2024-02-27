package blocks;

import java.awt.*;

import static main.GamePanel.cellArray;

public class IBlock extends Block {
    Color color;

    public IBlock() {
        super();
        color = new Color(72, 229, 253);
        id = lastId++;

        blockCells.add(cellArray[0][6]);
        blockCells.add(cellArray[0][7]);
        blockCells.add(cellArray[0][8]);
        blockCells.add(cellArray[0][9]);

        for (Cell cell : blockCells) {
            cell.cellId = id;
            cell.setColor(color);
        }
    }
}
