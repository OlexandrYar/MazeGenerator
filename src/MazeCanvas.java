import CellGrid.Cell;
import CellGrid.Grid;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class MazeCanvas extends JPanel{
    Grid mazeGrid;
    ArrayList<Cell> backTrack;
    Cell start;
    Cell end;
    Boolean isGenerate = true;//track if this is generate or solve stage
    ArrayList<Cell> path = null;
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
            g.fillRect(left,top,passedCellSize,passedCellSize);
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
        int width = getWidth()/mazeGrid.grid[0].length;
        int height = getHeight()/mazeGrid.grid.length;
        int cellSize;
        if (width<height){
            cellSize = width;
        }
        else {
            cellSize = height;
        }
        for(int i =0; i< mazeGrid.grid.length; i++){
            for(int j = 0; j<mazeGrid.grid[0].length; j++){
                drawCell(g,mazeGrid.grid[i][j],i*cellSize,j*cellSize,cellSize);
            }
        }
        if (currentCell!=null){
            drawDot(g,currentCell,mazeGrid,backTrack,start,end,path);
        }
    }
    public void drawDot(Graphics g,Cell passedCell,Grid passedGrid, ArrayList<Cell> passedStack,Cell startPoint, Cell endPoint, ArrayList<Cell> passedPath){
        int width = getWidth()/mazeGrid.grid[0].length;
        int height = getHeight()/mazeGrid.grid.length;
        int cellSize;
        if (width<height){
            cellSize = width;
        }
        else {
            cellSize = height;
        }
        int dotSize = cellSize/2;
        int dotX = (passedCell.column*cellSize) + (cellSize/2);
        int dotY = (passedCell.row*cellSize) + (cellSize/2);
        g.setColor(Color.GREEN);
        g.fillOval(dotX - dotSize/2,dotY - dotSize/2,dotSize,dotSize);
        //Neighouring cells being viewved
        ArrayList<Cell> neighbours;
        if(isGenerate)
            neighbours = passedGrid.getAllUnvisitedNeighbours(passedCell);
        else
            neighbours = passedGrid.getAllNoWalls(passedCell);

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
        //draw start point
        g.setColor(Color.GRAY);
        int startX = (startPoint.column*cellSize) + (cellSize/2);
        int startY = (startPoint.row*cellSize) + (cellSize/2);
        g.fillOval(startX - dotSize/2, startY - dotSize/2 , dotSize,dotSize);
        //draw end point
        g.setColor(Color.CYAN);
        int endX = (endPoint.column*cellSize) + (cellSize/2);
        int endY = (endPoint.row*cellSize) + (cellSize/2);
        g.fillOval(endX - dotSize/2, endY - dotSize/2 , dotSize,dotSize);

        //draw a shortest path
        if(passedPath != null){
            g.setColor(Color.PINK);
            for(Cell pathCell : passedPath){
                int pathX = (pathCell.column*cellSize) + (cellSize/2);
                int pathY = (pathCell.row*cellSize) + (cellSize/2);
                g.fillOval(pathX - backtrackDotSize/2, pathY - backtrackDotSize/2 , backtrackDotSize,backtrackDotSize);
            }
        }

    }
}
