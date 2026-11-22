import CellGrid.Grid;

import javax.swing.*;
public class Ui extends JFrame {
    Grid uiGrid = new Grid(20,20);
    public  Ui(){

        setTitle("Maze");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new MazeCanvas(uiGrid));
    }

    public static void main(String[] args){
        new Ui().setVisible(true);
    }
}
