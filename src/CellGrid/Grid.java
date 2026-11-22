package CellGrid;
import java.util.ArrayList;
public class Grid {
    int columns;
    int rows;
    public Cell[][] grid;

    public Grid(int rows ,int columns){
        this.rows = rows;
        this.columns = columns;
        grid = new Cell[rows][columns];

        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j<grid[0].length;j++){
                grid[i][j] = new Cell(i,j);
            }
        }
    }

    public Cell getLefCell(Cell passedCell){
        if( passedCell.column - 1 >= 0)
            return grid[passedCell.row][passedCell.column-1];
        else
            return null;
    }
    public Cell getRightCell(Cell passedCell){
        if( passedCell.column + 1 < grid[0].length)
            return grid[passedCell.row][passedCell.column+1];
        else
            return null;
    }
    public Cell getBottomCell(Cell passedCell){
        if(passedCell.row + 1 < grid.length)
            return grid[passedCell.row+1][passedCell.column];
        else
            return null;
    }
    public Cell getTopCell(Cell passedCell){
        if(passedCell.row - 1 >= 0)
            return grid[passedCell.row -1][passedCell.column];
        else
            return null;
    }
    public ArrayList<Cell> getAllUnvisitedNeighbours(Cell passedCell){
        ArrayList<Cell> avalilavle = new ArrayList<Cell>();
        if (getRightCell(passedCell) != null && getRightCell(passedCell).isVisited == false)
            avalilavle.add(getRightCell(passedCell));
        if (getLefCell(passedCell) != null && getLefCell(passedCell).isVisited == false)
            avalilavle.add(getLefCell(passedCell));
        if (getTopCell(passedCell) != null && getTopCell(passedCell).isVisited == false)
            avalilavle.add(getTopCell(passedCell));
        if (getBottomCell(passedCell) != null && getBottomCell(passedCell).isVisited == false)
            avalilavle.add(getBottomCell(passedCell));
        return avalilavle;
    }
}
