package main;

import blocks.*;
import blocks.LineBlock;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel implements EventListener, KeyListener {
    private Block block;
    private final int x = 0;
    private final int y = 0;
    private final int WIDTH = 516;
    private final int HEIGHT = 1039;
    public static Cell[][] cellArray;
    boolean running = true;
    boolean paused = false;
    Random r = new Random();
    private final static Color blank = Color.WHITE;
    int counter;

    public GamePanel() {
        int id = -1;
        setBounds(x, y, WIDTH, HEIGHT);
        setLayout(new GridLayout(40, 20));
        setFocusable(true);
        addKeyListener(this);
        cellArray = new Cell[40][20];
        Border blackLine = BorderFactory.createLineBorder(Color.BLACK, 1);
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 20; j++) {
                cellArray[i][j] = new Cell(i, j, id);
                cellArray[i][j].setColor(blank);
                cellArray[i][j].setBorder(blackLine);
                super.add(cellArray[i][j].getPanel());
            }
        }
        counter = 0;
    }

    public static Cell getCellAt(int y, int x) {
        return cellArray[y][x];
    }

    void update() {
        for (Cell[] cells : cellArray) {
            for (Cell j : cells) {
                j.getLabel().setText(String.valueOf(j.cellId));
            }
        }

        // change this later
        for (int i=0; i<40; i++) {
            for (int j=0; j<20; j++) {
                cellArray[i][j].setColor(cellArray[i][j].getColor());
            }
        }

        if (counter == 10) {
            block.moveDown(cellArray, "down");
            counter = 0;
        }
    }

    boolean checkYCollision() {

        for (int i = 0; i<block.blockCells.size(); i++) {
            if (block.blockCells.get(i).y == 39) {
                return true;
            }
        }

        //check collision with other block here
        for (Cell cell : block.blockCells) {
            if ((cellArray[cell.y + 1][cell.x].cellId != -1) && (cellArray[cell.y - 1][cell.x].cellId != cell.cellId))
                return true;
        }
        return false;
    }

    void newBlock() {
        switch (r.nextInt(1)) {
            case 0:

                // deletes old block
                block = null;
                System.gc();

                // create new block
                this.block = new LineBlock();

                break;
        }
    }

    void checkComplete() throws InterruptedException {
        int cellCount = 0;
        for (Cell[] rows : cellArray) {
            for (Cell cols : rows) {

                // checks if cell is full
                if (cols.cellId != -1) cellCount++;
                else {
                    cellCount = 0;
                    continue;
                }

                if (cellCount == rows.length) {
                    System.out.println("complete");

                    // delete completed row
                    for (int i=0; i<=19; i++) {
                        cellArray[cols.y][i].setColor(Color.WHITE);
                        cellArray[cols.y][i].cellId = -1;
                    }
                    //Thread.sleep(Integer.MAX_VALUE);

                    List<Cell> cells = new ArrayList<>();
                    for (Cell[] cellRows : cellArray) {
                        for (Cell cellCols : cellRows) {
                            if (cellCols.cellId != -1) {
                                cells.add(cellCols);
//                                cellCols.setColor(Color.BLACK);
                            }
                        }
                    }

                    for (Cell cell : cells) {
                        cell.shiftDown();
                    }
                    return;
                }
                if (cols.x == rows.length) cellCount = 0;
            }
        }
    }

    @Override public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
//            case KeyEvent.VK_RIGHT -> block.shift("RIGHT", cellArray);
//            case KeyEvent.VK_LEFT -> block.shift("LEFT", cellArray);
            case KeyEvent.VK_LEFT -> block.moveDown(cellArray, "left");
            case KeyEvent.VK_RIGHT -> block.moveDown(cellArray, "right");
            case KeyEvent.VK_P -> paused = !paused;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    void testBlocks() {
        int counter = 0;
        for (Cell cell : cellArray[39]) {
            if (counter < 16) {
                cell.setColor(Color.BLUE);
                cell.setCellId(99);
            }
            counter++;
        }
        for (int i = 12; i < 16; i++) {
            cellArray[38][i].setColor(Color.BLUE);
            cellArray[38][i].setCellId(99);
        }
    }

    void gameLoop() throws InterruptedException {
        testBlocks();
        newBlock();
        while (running) {
            long startTime = System.nanoTime();
            update();
//            activeBlock.draw(cellArray);
            if (checkYCollision()) {
                checkComplete();

                newBlock();
            }

            repaint(); // Render the game graphics

            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            long targetTime = 1000000000L / 60; // Target time for 60 FPS (nanoseconds)

            // Control the frame rate to avoid the game running too fast
            if (elapsedTime < targetTime) {
                try {
                    Thread.sleep((targetTime - elapsedTime) / 1000000); // Convert nanoseconds to milliseconds
                    if (paused) continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            counter++;
        }
    }
}
