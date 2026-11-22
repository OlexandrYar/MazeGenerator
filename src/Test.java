public class Test {
    public static void main(String[] args){
        Grid testGrid = new Grid(0,0);
        Cell test = testGrid.grid[0][6];
        test.walls[2] = false;
        System.out.println(test.row  + " "+ test.column +" Right: " + test.walls[0]+ " Left: " + test.walls[1] + " Top: " + test.walls[2] + " Bottom: "+ test.walls[3]);
    }
}
