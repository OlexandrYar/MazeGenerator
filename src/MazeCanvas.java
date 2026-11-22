import java.awt.*;

public class MazeCanvas extends Canvas{
    Grid mazeGrid;
    public MazeCanvas(Grid grid){
        this.mazeGrid = grid;
    }

    public void paint(Graphics g){
        int width = getWidth();
        int height = getHeight();
        int cellSize;
        if (width<height){
            cellSize = width/mazeGrid.grid.length;
        }
        g.drawLine(0,0,500,500);
    }
}
