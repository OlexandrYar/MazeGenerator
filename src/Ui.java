import CellGrid.Grid;

import javax.swing.*;
public class Ui extends JFrame {
    Grid uiGrid = new Grid(3,2);

    public  Ui(){
        setTitle("Maze");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new MazeCanvas(uiGrid));
        uiGrid.grid[0][0].walls[0] = false;
        uiGrid.grid[0][1].walls[1] = false;
    }

    public static void main(String[] args){
        new Ui().setVisible(true);
    }
}
