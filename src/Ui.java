import CellGrid.*;

import java.util.ArrayList;
import Stack.StackReferenceBased;
import Queue.QueueArrayBased;

import javax.swing.*;
public class Ui extends JFrame {
    Grid uiGrid = new Grid(70,70);
    MazeCanvas canvas = new MazeCanvas(uiGrid);
    Cell current;
    Cell previous;
    Cell initialPoint = uiGrid.grid[generateRandomPoint(true)][generateRandomPoint(false)];//select random start point
    Cell targetPoint;//select random finish point
    int generationSpeed = 0;
    int solvingSpeed = 0;
    ArrayList<Cell> backtrackList = new ArrayList<Cell>();

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
            targetPoint = uiGrid.grid[generateRandomPoint(true)][generateRandomPoint(false)];
        }while(targetPoint == initialPoint);
        canvas.start = initialPoint;
        canvas.end = targetPoint;
        StackReferenceBased visited = new StackReferenceBased();
        canvas.backTrack = backtrackList;
        current = initialPoint;
        previous = null;
        current.isVisited = true;
        canvas.currentCell = current;
        visited.push(current);
        backtrackList.add(current);
        Timer timer = new Timer(generationSpeed, e -> {
            if(uiGrid.getAllUnvisitedNeighbours(current).isEmpty()){
                visited.pop();
                backtrackList.remove(backtrackList.size()-1);
                if(visited.isEmpty()){
                    ((Timer)e.getSource()).stop();//gets the timer that caused the event e and stops it
                    solveMaze();
                    return;
                }

                current = (Cell) visited.peek();
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
            backtrackList.add(current);
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
        QueueArrayBased traversalQueue = new QueueArrayBased();

        uiGrid.setAllUnvisited();
        canvas.isGenerate = false;
        current = initialPoint;
        canvas.currentCell = current;
        current.isVisited =true;
        traversalQueue.enqueue(current);
        canvas.repaint();
        Timer timer = new Timer(solvingSpeed, e -> {
            if(traversalQueue.isEmpty()){
                ((Timer)e.getSource()).stop();//gets the timer that caused the event e and stops it
                System.out.println("Maze traversed");
                return;
            }
            current = (Cell) traversalQueue.dequeue();
            canvas.currentCell = current;
            if(current == targetPoint){
                //gets the timer that caused the event e and stops it

                ArrayList<Cell> pathway = new ArrayList<Cell>();
                Cell step = targetPoint;
                while (step != null){
                    pathway.add(step);
                    step = step.parent;
                }
                canvas.path = pathway;
                canvas.repaint();
                ((Timer)e.getSource()).stop();
                return;
            }

            for(Cell neighbour: uiGrid.getAllNoWalls(current)){
                neighbour.isVisited = true;
                neighbour.parent = current;
                traversalQueue.enqueue(neighbour);
            }
            canvas.repaint();
        });
        timer.start();
    }

    public int generateRandomPoint(boolean forRows){
        if(forRows){
            int randomPoint = (int)(Math.random() * (uiGrid.grid.length - 0)) + 0;
            return randomPoint;
        }
        else {
            int randomPoint = (int)(Math.random() * (uiGrid.grid[0].length - 0)) + 0;
            return randomPoint;
        }

    }
    public static void main(String[] args){
        new Ui().setVisible(true);
    }
}
