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

        blockCells.add(cellArray[39][0]);
        blockCells.add(cellArray[39][1]);
        blockCells.add(cellArray[39][2]);
        blockCells.add(cellArray[39][3]);

        blockCells.add(cellArray[39][4]);
        blockCells.add(cellArray[39][5]);
        blockCells.add(cellArray[39][6]);
        blockCells.add(cellArray[39][7]);

        blockCells.add(cellArray[39][8]);
        blockCells.add(cellArray[39][9]);
        blockCells.add(cellArray[39][10]);
        blockCells.add(cellArray[39][11]);

        blockCells.add(cellArray[39][12]);
        blockCells.add(cellArray[39][13]);
        blockCells.add(cellArray[39][14]);
        blockCells.add(cellArray[39][15]);

        blockCells.add(cellArray[38][12]);
        blockCells.add(cellArray[38][13]);
        blockCells.add(cellArray[38][14]);
        blockCells.add(cellArray[38][15]);

        for (Cell i : blockCells) {
            i.cellId = id;
        }
    }
}
