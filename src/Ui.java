import CellGrid.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;
public class Ui extends JFrame {
    Grid uiGrid = new Grid(10,10);
    MazeCanvas canvas = new MazeCanvas(uiGrid);
    Cell current;

    public Ui(){
        setTitle("Maze");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(canvas);
        generateMaze();
    }
    private void generateMaze(){
        Stack<Cell> visited = new Stack<Cell>();
        current = uiGrid.grid[0][0];
        current.isVisited = true;
        canvas.currentCell = current;
        Timer timer = new Timer(300,e -> {
            if(uiGrid.getAllUnvisitedNeighbours(current).isEmpty()){
                return;
            }
            ArrayList<Cell> neighbours = uiGrid.getAllUnvisitedNeighbours(current);
            current = neighbours.get((int) ((Math.random() * (neighbours.size() - 0)) + 0));
            canvas.currentCell = current;
            current.isVisited = true;
            canvas.repaint();

        });
        timer.start();

    }

    public static void main(String[] args){
        new Ui().setVisible(true);
    }
}
