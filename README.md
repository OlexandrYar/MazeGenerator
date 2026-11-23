# 🧩 Maze Generator & Solver

An animated **maze generator and solver** written in **Java Swing**.
It builds a random maze with **depth-first search (backtracking)** and then finds the **shortest path** between two random points with **breadth-first search**. Both algorithms run on Stack and Queue data structures I implemented myself.

> Built as a Data Structures & Algorithms project (TU Dublin).

![demo](docs/demo.gif)
<!-- Record a short GIF of the program (e.g. ScreenToGif) and save it to docs/demo.gif -->

---

## ✨ Features

- **Random maze generation**: randomized DFS with backtracking (a "perfect" maze, so there is exactly one route between any two cells).
- **Shortest path solving**: BFS from a random start to a random end point.
- **Step-by-step animation**: every algorithm step is drawn live with `javax.swing.Timer`, so you can watch the stack and queue at work.
- **Custom data structures**: the lab-built `StackReferenceBased` (linked list) and `QueueArrayBased` (circular array) are used instead of `java.util` collections.
- **Any grid size**: square or rectangular. Cells scale to fit the window.

---

## 🎨 What the colours mean

| Colour | Meaning |
|--------|---------|
| 🟥 Red cell | Not visited yet |
| 🟢 Green dot | Current cell |
| 🔵 Blue dots | Neighbours being considered |
| 🩷 Small pink dots | Generation: backtracking stack · Solving: final shortest path |
| ⚪ Gray dot | Start point |
| 🩵 Cyan dot | End point |

---

## 🧠 How it works

### 1. Generation: randomized depth-first search (Stack)
1. Start at a random cell, mark it visited and **push** it onto the stack.
2. Look at the current cell's unvisited neighbours:
   - If there are any, pick one at random, **remove the wall** between the two cells (on both sides), mark it visited and push it.
   - If there are none (a dead end), **pop** to go back to the previous cell.
3. When the stack is empty, every cell has been visited and the maze is finished.

### 2. Solving: breadth-first search (Queue)
1. Reset all cells to unvisited, then **enqueue** the start cell.
2. **Dequeue** a cell. If it is the target, stop.
3. Otherwise, enqueue every unvisited neighbour that has **no wall in between**, and record `neighbour.parent = current`.
4. Rebuild the shortest path by following `parent` links from the target back to the start.

BFS explores the maze layer by layer, so the first time it reaches the target it has found the **shortest** path.

### Complexity
For a maze of `V = rows × columns` cells:

| | Time | Extra space |
|---|---|---|
| Generation (DFS) | O(V) | O(V) stack |
| Solving (BFS) | O(V) | O(V) queue + parent links |

---

## 📁 Project structure

```
src/
├── Ui.java              # JFrame: runs generation (DFS) and solving (BFS) with Timers
├── MazeCanvas.java      # JPanel: draws walls, cells, dots and the path
├── CellGrid/
│   ├── Cell.java        # row, column, walls[4] (right, left, top, bottom), isVisited, parent
│   └── Grid.java        # 2D cell array + neighbour lookup helpers
├── Stack/               # StackReferenceBased: linked-list stack
└── Queue/               # QueueArrayBased: circular array queue
```

---

## 🚀 How to run

Requires **JDK 17+** (tested on JDK 21).

```bash
# from the project root
javac -sourcepath src -d out src/Ui.java
java -cp out Ui
```

Or open the folder in **IntelliJ IDEA** and run `Ui.main()`.

### Changing the maze size / speed
In `Ui.java`:

```java
Grid uiGrid = new Grid(30, 30);  // maze dimensions
int generationSpeed = 50;        // ms per step (0 = as fast as possible)
int solvingSpeed = 50;
```

---

## 🛠 Tech

Java · Swing (`JFrame`, `JPanel`, `Timer`) · custom Stack and Queue ADTs · DFS · BFS

## 👤 Author

**Olexandr Yar** · [GitHub](https://github.com/OlexandrYar)
