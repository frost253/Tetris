package blocks;

import main.GamePanel;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.GamePanel.cellArray;

public class LineBlock extends Block {

    public LineBlock() {
        id = lastId++;
//        int y=0;
//
//        for (int i=0; i<cells.size(); i++) {
//            cells.add(new Cell(i, y, id));
//        }

        cells.add(cellArray[0][6]);
        cells.add(cellArray[0][7]);
        cells.add(cellArray[0][8]);
        cells.add(cellArray[0][9]);

        for (Cell i : cells) {
            i.cellId = id;
        }
    }
}
