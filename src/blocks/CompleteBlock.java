package blocks;

import java.util.List;

public class CompleteBlock extends Block {
    public void setCells(List<Cell> cells) {
        this.cells = List.copyOf(cells);
    }
}
