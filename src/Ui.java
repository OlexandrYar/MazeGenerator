import CellGrid.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;
public class Ui extends JFrame {
    Grid uiGrid = new Grid(10,10);
    MazeCanvas canvas = new MazeCanvas(uiGrid);
    Cell current;
    Cell previous;

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
        previous = null;
        current.isVisited = true;
        canvas.currentCell = current;
        visited.push(current);
        Timer timer = new Timer(300,e -> {
            if(uiGrid.getAllUnvisitedNeighbours(current).isEmpty()){
                return;
            }
            ArrayList<Cell> neighbours = uiGrid.getAllUnvisitedNeighbours(current);
            previous = current;
            current = neighbours.get((int) ((Math.random() * (neighbours.size() - 0)) + 0));
            deleteWall(previous,current);
            canvas.currentCell = current;
            current.isVisited = true;
            visited.push(current);
            canvas.repaint();

        });
        timer.start();
        System.out.println(visited);
    }
    private void deleteWall(Cell passedPrevious, Cell passedCurrent){
        if(passedCurrent.column == passedPrevious.column + 1){
            //moved right
            passedPrevious.walls[0] = false;
            passedCurrent.walls[1] = false;
        }
        if(passedCurrent.column == passedPrevious.column - 1){
            //moved left
            passedPrevious.walls[1] = false;
            passedCurrent.walls[0] = false;
        }
        if(passedCurrent.row == passedPrevious.row - 1){
            //moved up
            passedPrevious.walls[2] = false;
            passedCurrent.walls[3] = false;
        }
        if(passedCurrent.row == passedPrevious.row + 1){
            //moved down
            passedPrevious.walls[3] = false;
            passedCurrent.walls[2] = false;
        }

    }
    public static void main(String[] args){
        new Ui().setVisible(true);
    }
}
