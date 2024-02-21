package blocks;

import javax.swing.*;
import java.util.Objects;

public class Cell extends JPanel {
    public int x, y;
    public int cellId;
    public Cell(int y, int x, int cellId) {
        this.y = y;
        this.x = x;

        this.cellId = cellId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y && cellId == cell.cellId;
    }
}
