import CellGrid.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;
public class Ui extends JFrame {
    Grid uiGrid = new Grid(20,20);
    MazeCanvas canvas = new MazeCanvas(uiGrid);
    Cell current;
    Cell previous;
    int speed = 300;

    public Ui(){
        setTitle("Maze");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(canvas);
        generateMaze();
    }
    private void generateMaze(){
        Stack<Cell> visited = new Stack<Cell>();
        current = uiGrid.grid[(int)(Math.random() * (uiGrid.grid.length - 0)) + 0][(int)(Math.random() * (uiGrid.grid[0].length - 0)) + 0];//select random start point
        previous = null;
        current.isVisited = true;
        canvas.currentCell = current;
        visited.push(current);
        Timer timer = new Timer(speed,e -> {
            if(uiGrid.getAllUnvisitedNeighbours(current).isEmpty()){
                visited.pop();
                if(visited.isEmpty()){
                    ((Timer)e.getSource()).stop();//gets the timer that caused the event e and stops it
                    System.out.println("Maze Generated");
                    return;
                }

                current = visited.peek();
                canvas.currentCell = current;
                canvas.repaint();
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
