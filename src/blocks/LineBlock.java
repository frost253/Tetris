package blocks;

import java.awt.*;

import static main.GamePanel.cellArray;

public class LineBlock extends Block {
    Color color;

    public LineBlock() {
        color = Color.BLUE;
        id = lastId++;
//        int y=0;
//
//        for (int i=0; i<cells.size(); i++) {
//            cells.add(new Cell(i, y, id));
//        }

        blockCells.add(cellArray[0][6]);
        blockCells.add(cellArray[0][7]);
        blockCells.add(cellArray[0][8]);
        blockCells.add(cellArray[0][9]);

        for (Cell i : blockCells) {
            i.cellId = id;
        }
    }
}
