package cellabsorption;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("SameParameterValue")
public class CellSimulation {
    private static final int CELL_COUNT = 200;

    private CanvasWindow canvas;
    private List<Cell> cells;
    private Random rand = new Random();
    
    public static void main(String[] args) {
        new CellSimulation();
    }

    public CellSimulation() {
        canvas = new CanvasWindow("Cell Absorption", 800, 800);
        populateCells();

        //noinspection InfiniteLoopStatement
        while (true) {
            Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
            for (Cell cell : cells) cell.moveAround(canvasCenter);
            handleCellInteraction();
            canvas.draw();
            canvas.pause(10);
        }
    }

    private void populateCells() {
        cells = new ArrayList<>();
        for (int i=0; i<CELL_COUNT; i++) {
            double size = rand.nextInt(5) + 2;
            Cell cell = new Cell(
                rand.nextDouble() * (canvas.getWidth() - size),
                rand.nextDouble() * (canvas.getWidth() - size),
                size,
                Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
            canvas.add(cell.getShape());
            cells.add(cell);
        }
    }

    private void handleCellInteraction() {
        for (int i=0; i<CELL_COUNT; i++) {
            for (int j=i+1; j<CELL_COUNT; j++) {
                cells.get(i).interactWith(cells.get(j));
            }
        }
    }
}
