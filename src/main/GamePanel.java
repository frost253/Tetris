package main;

import blocks.*;
import blocks.LineBlock;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.Array;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel implements EventListener, KeyListener {
    private Block activeBlock;
    private final int x = 0;
    private final int y = 0;
    private final int WIDTH = 516;
    private final int HEIGHT = 1039;
    public static Cell[][] cellArray;
    boolean running = true;
    boolean paused = false;
    ArrayList<Block> blockList = new ArrayList<>();
    Random r = new Random();
    public static Color blank;
    int counter;

    public GamePanel() {
        int id = -1;
        blank = Color.WHITE;
        setBounds(x, y, WIDTH, HEIGHT);
        setLayout(new GridLayout(40, 20));
        setFocusable(true);
        addKeyListener(this);
        cellArray = new Cell[40][20];
        Border blackLine = BorderFactory.createLineBorder(Color.BLACK, 1);
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 20; j++) {
                cellArray[i][j] = new Cell(i, j, id);
                cellArray[i][j].setBackground(blank);
                cellArray[i][j].setBorder(blackLine);
                super.add(cellArray[i][j]);
            }
        }
        counter = 0;
    }

    void update() {
        for (Cell[] i : cellArray) {
            for (Cell j : i) {
                j.getLabel().setText(String.valueOf(j.cellId));
            }
        }
        if (counter == 5) {
            activeBlock.moveDown(cellArray);
            counter = 0;
        }
    }

    boolean checkYCollision() {

        for (int i=0; i<activeBlock.cells.size(); i++) {
            if (activeBlock.cells.get(i).y == 39) {
                return true;
            }
        }

        //check collision with other block here
        for (Cell i : activeBlock.cells) {
            if ((cellArray[i.y + 1][i.x].cellId != -1) && (cellArray[i.y - 1][i.x].cellId != i.cellId))
                return true;
        }
        return false;
    }

    void newBlock() {
        switch (r.nextInt(1)) {
            case 0:
                Block block = new LineBlock();
                blockList.add(block);
                activeBlock = block;
                break;
        }
    }

    boolean checkComplete() {
        int rowCount = 0;
        for (Cell[] rows : cellArray) {
            for (Cell cols : rows) {
                if (cols.cellId != -1) rowCount++;
                else {
                    rowCount = 0;
                    continue;
                }

                if (rowCount == rows.length) {
                    System.out.println("complete");

                    // delete completed row
                    for (int i=0; i<=19; i++) {
                        cellArray[cols.y][i].setBackground(GamePanel.blank);
                        cellArray[cols.y][i].cellId = -1;
                        update();
                    }

                    // cells aren't actually getting deleted ?
                    CompleteBlock completeBlock = new CompleteBlock();
                    List<Cell> cells = new ArrayList<>();
                    for (Cell[] cellRows : cellArray) {
                        for (Cell cellCols : cellRows) {
//                            cells.addAll(Arrays.asList(cellRows));
                            if (cellCols.cellId != -1) {
                                cells.add(cellCols);
                                cellCols.setBackground(Color.BLACK);
                            }
                        }
                    }
                    completeBlock.setCells(cells);
                    completeBlock.moveDown(cellArray);

                    return true;
                }
                if (cols.x == rows.length) rowCount = 0;
            }
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> activeBlock.shift("RIGHT", cellArray);
            case KeyEvent.VK_LEFT -> activeBlock.shift("LEFT", cellArray);
            case KeyEvent.VK_P -> paused = !paused;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    void gameLoop() {
        newBlock();
        while (running) {
            long startTime = System.nanoTime();
            update();
            activeBlock.draw(cellArray);
            if (checkYCollision()) {
                checkComplete();
                activeBlock = null;
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
