import java.awt.*;

public class MazeCanvas extends Canvas{
    Grid mazeGrid;
    public MazeCanvas(Grid grid){
        this.mazeGrid = grid;
    }
    public void drawCell(Graphics g, Cell c, int top, int left, int passedCellSize){
        if (c.walls[0])
            g.drawLine(left+passedCellSize, top,left+passedCellSize, top-passedCellSize);//draw right line
        if (c.walls[1])
            g.drawLine(left,top, left,top-passedCellSize);//draw left line
        if (c.walls[2])
            g.drawLine(left,top, left+passedCellSize, top);//draw top line
        if (c.walls[3])
            g.drawLine(left,top-passedCellSize, left+passedCellSize,top-passedCellSize );//draw bottom line




    }
    public void paint(Graphics g){
        int width = getWidth();
        int height = getHeight();
        int cellSize;
        if (width<height){
            cellSize = width/mazeGrid.grid.length;

        }
        else{
            cellSize = height/mazeGrid.grid.length;
        }

        for(int i =0; i< mazeGrid.grid.length; i++){
            for(int j = 0; j<mazeGrid.grid[0].length;j++){
                drawCell(g,mazeGrid.grid[i][j],i*cellSize,j*cellSize,cellSize);
            }
        }
    }
}
