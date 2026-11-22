import CellGrid.*;

import java.awt.*;
import java.util.Stack;

import javax.swing.*;
public class Ui extends JFrame {
    Grid uiGrid = new Grid(5,5);
    MazeCanvas canvas = new MazeCanvas(uiGrid);

    public Ui(){
        setTitle("Maze");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(canvas);
        generateMaze();
    }
    private void generateMaze(){
        Cell initialCell = uiGrid.grid[0][0];
        Stack<Cell> visited = new Stack<Cell>();
        Cell current = initialCell;
        while (true){
            canvas.currentCell = current;
            current = uiGrid.getAllUnvisitedNeighbours(current).get(0);

        }


    }

    public static void main(String[] args){
        new Ui().setVisible(true);
    }
}
