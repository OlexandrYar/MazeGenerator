import CellGrid.*;

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;
public class Ui extends JFrame {
    Grid uiGrid = new Grid(20,20);
    MazeCanvas canvas = new MazeCanvas(uiGrid);
    Cell current;
    Cell previous;
    Cell initialPoint = uiGrid.grid[generateRandomPoint()][generateRandomPoint()];//select random start point
    Cell targetPoint;//select random finish point
    int generationSpeed = 0;

    public Ui(){
        setTitle("Maze");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(canvas);
        generateMaze();
    }
    private void generateMaze(){
        //just to make shure end point is never the same as start point
        do{
            targetPoint = uiGrid.grid[generateRandomPoint()][generateRandomPoint()];
        }while(targetPoint == initialPoint);
        canvas.start = initialPoint;
        canvas.end = targetPoint;
        Stack<Cell> visited = new Stack<Cell>();
        canvas.backTrack = visited;
        current = initialPoint;
        previous = null;
        current.isVisited = true;
        canvas.currentCell = current;
        visited.push(current);
        Timer timer = new Timer(generationSpeed, e -> {
            if(uiGrid.getAllUnvisitedNeighbours(current).isEmpty()){
                visited.pop();
                if(visited.isEmpty()){
                    ((Timer)e.getSource()).stop();//gets the timer that caused the event e and stops it
                    System.out.println("Maze Generated");
                    return;
                }

                current = visited.peek();
                canvas.currentCell = current;
                canvas.backTrack = visited;
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
            canvas.backTrack = visited;
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

    private void solveMaze(){

    }

    public int generateRandomPoint(){
        int randomPoint = (int)(Math.random() * (uiGrid.grid.length - 0)) + 0;
        return randomPoint;
    }
    public static void main(String[] args){
        new Ui().setVisible(true);
    }
}
