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

}
