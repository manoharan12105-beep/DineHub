package Utils;

import java.util.*;

public class PathFinder {
  private final int[] dr = {-1, 1, 0, 0};
  private final int[] dc = {0, 0, -1, 1};

  public List<Cell> shortestPath(String[][] cityMap, int[] source, int[] destination) {
    int rows = cityMap.length;
    int cols = cityMap[0].length;

    boolean[][] visited = new boolean[rows][cols];
    Cell[][] parent = new Cell[rows][cols];

    Queue<Cell> queue = new LinkedList<>();

    Cell start = new Cell(source[0], source[1]);
    Cell end = new Cell(destination[0], destination[1]);

    queue.offer(start);
    visited[start.row][start.col] = true;

    while(!queue.isEmpty()) {
        Cell current = queue.poll();

        if(current.row == end.row && current.col == end.col) {
          break;
        }

        for(int i = 0; i < 4; i++) {
          int newRow = current.row + dr[i];
          int newCol = current.col + dc[i];

          if(isValid(cityMap, visited, newRow, newCol)) {
            visited[newRow][newCol] = true;
            parent[newRow][newCol] = current;
            queue.offer(new Cell(newRow, newCol));
            }
        }
    }

    return buildPath(parent, visited, end);
  }

  private boolean isValid(String[][] cityMap, boolean[][] visited, int row, int col) {
    if(row < 0 || row >= cityMap.length)
      return false;

    if(col < 0 || col >= cityMap[0].length)
      return false;

    if(visited[row][col])
      return false;

    return cityMap[row][col].equals("1");
  }

  private List<Cell> buildPath(Cell[][] parent, boolean[][] visited, Cell destination) {
    List<Cell> path = new ArrayList<>();

    if (!visited[destination.row][destination.col])
      return path;

    Cell current = destination;

    while (current != null) {
      path.add(current);
      current = parent[current.row][current.col];
    }

    Collections.reverse(path);

    return path;
  }

  public void printPath(List<Cell> path) {
    if (path.isEmpty()) {
      System.out.println("No Path Found.");
      return;
    }

    System.out.println("Shortest Path:");

    for (Cell cell : path) {
      System.out.print(cell + " ");
    }

    System.out.println();
  }
}