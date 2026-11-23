package CellGrid;

public class Cell {
    public boolean isVisited;
    public int row;
    public int column;
    public boolean[] walls = new boolean[4];//right,left,top,bottom
    public Cell parent;

    public Cell(int row, int column){
        this.isVisited = false;
        this.row = row;
        this.column = column;
        this.walls[0] = this.walls[1] = this.walls[2] = this.walls[3] = true;
        this.parent = null;

    }
}
