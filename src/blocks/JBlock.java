package blocks;

import java.awt.*;

import static main.GamePanel.cellArray;

public class JBlock extends Block {

    public JBlock() {
        super();
        id = lastId++;

        blockCells.add(cellArray[0][6]);
        blockCells.add(cellArray[1][6]);
        blockCells.add(cellArray[2][6]);
        blockCells.add(cellArray[2][5]);

        for (Cell cell : blockCells) {
            cell.cellId = id;
            cell.setColor(Colors.PINK.color);
        }
    }
}
