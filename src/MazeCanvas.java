import CellGrid.Cell;
import CellGrid.Grid;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class MazeCanvas extends JPanel{
    Grid mazeGrid;
    Stack<Cell>backTrack;
    public Cell currentCell = null;
    public MazeCanvas(Grid grid){
        this.mazeGrid = grid;
    }
    public void drawCell(Graphics g, Cell c, int top ,int left,int passedCellSize){
        //fill the cell with the corresponding color
        if(c.isVisited == false){
            int rectX = (c.column*passedCellSize) + (passedCellSize/2);
            int recY = (c.row*passedCellSize) + (passedCellSize/2);
            g.setColor(Color.RED);
            g.fillRect(rectX - passedCellSize/2,recY - passedCellSize/2,passedCellSize,passedCellSize);
        }
        //draw walls
        g.setColor(Color.BLACK);
        if (c.walls[2])
            g.drawLine(left,top,left+passedCellSize,top);//top
        if (c.walls[0])
            g.drawLine(left+passedCellSize,top,left+passedCellSize, top+passedCellSize);//right
        if (c.walls[3])
            g.drawLine(left +passedCellSize, top+passedCellSize,left,top+passedCellSize);//bottom
        if (c.walls[1])
            g.drawLine(left,top+passedCellSize,left,top);//left

    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int cellSize;
        if (width<height){
            cellSize = width/mazeGrid.grid[0].length;

        }
        else{
            cellSize = height/mazeGrid.grid[0].length;
        }
        for(int i =0; i< mazeGrid.grid.length; i++){
            for(int j = 0; j<mazeGrid.grid[0].length; j++){
                drawCell(g,mazeGrid.grid[i][j],i*cellSize,j*cellSize,cellSize);
            }
        }
        if (currentCell!=null){
            drawDot(g,currentCell,mazeGrid,backTrack);
        }
    }
    public void drawDot(Graphics g,Cell passedCell,Grid passedGrid, Stack<Cell> passedStack){
        int width = getWidth();
        int height = getHeight();
        int cellSize;
        if (width<height){
            cellSize = width/mazeGrid.grid[0].length;

        }
        else{
            cellSize = height/mazeGrid.grid[0].length;
        }
        int dotSize = cellSize/2;
        int dotX = (passedCell.column*cellSize) + (cellSize/2);
        int dotY = (passedCell.row*cellSize) + (cellSize/2);
        g.setColor(Color.GREEN);
        g.fillOval(dotX - dotSize/2,dotY - dotSize/2,dotSize,dotSize);
        //Neighouring cells being viewved
        ArrayList<Cell> neighbours = passedGrid.getAllUnvisitedNeighbours(passedCell);
        for(Cell neighbour: neighbours){
            g.setColor(Color.BLUE);
            int neighDotX = (neighbour.column*cellSize) + (cellSize/2);
            int neighDotY = (neighbour.row*cellSize) + (cellSize/2);
            g.fillOval(neighDotX - dotSize/2,neighDotY - dotSize/2,dotSize,dotSize);
        }
        //Backtrack path form Stack
        int backtrackDotSize = cellSize/4;
        for (Cell backtrackDot : passedStack){
            g.setColor(Color.PINK);
            int neighDotX = (backtrackDot.column*cellSize) + (cellSize/2);
            int neighDotY = (backtrackDot.row*cellSize) + (cellSize/2);
            g.fillOval(neighDotX - backtrackDotSize/2,neighDotY - backtrackDotSize/2,backtrackDotSize,backtrackDotSize);
        }



    }
}
