package blocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompleteBlock extends Block {
    public void setCells(List<Cell> cells) {
        this.cells = new ArrayList<>(cells);
    }
}
