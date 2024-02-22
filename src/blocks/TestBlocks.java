package blocks;

import static main.GamePanel.cellArray;

public class TestBlocks extends Block {
    public TestBlocks() {
        id = lastId++;
//        int y=0;
//
//        for (int i=0; i<cells.size(); i++) {
//            cells.add(new Cell(i, y, id));
//        }

        cells.add(cellArray[39][0]);
        cells.add(cellArray[39][1]);
        cells.add(cellArray[39][2]);
        cells.add(cellArray[39][3]);

        cells.add(cellArray[39][4]);
        cells.add(cellArray[39][5]);
        cells.add(cellArray[39][6]);
        cells.add(cellArray[39][7]);

        cells.add(cellArray[39][8]);
        cells.add(cellArray[39][9]);
        cells.add(cellArray[39][10]);
        cells.add(cellArray[39][11]);

        cells.add(cellArray[39][12]);
        cells.add(cellArray[39][13]);
        cells.add(cellArray[39][14]);
        cells.add(cellArray[39][15]);

        cells.add(cellArray[38][12]);
        cells.add(cellArray[38][13]);
        cells.add(cellArray[38][14]);
        cells.add(cellArray[38][15]);

        for (Cell i : cells) {
            i.cellId = id;
        }
    }
}
