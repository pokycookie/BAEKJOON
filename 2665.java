import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        int n = Integer.parseInt(br.readLine());
        char[][] graph = new char[n][n];

        for (int i = 0; i < n; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<PathPosition> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.path));
        queue.offer(new PathPosition(0, 0, 0));

        while (!queue.isEmpty()) {
            PathPosition curr = queue.poll();
            if (dist[curr.row][curr.column] < curr.path) {
                continue;
            }
            for (int[] next : direction) {
                int nextRow = curr.row + next[0];
                int nextColumn = curr.column + next[1];

                if (nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n) {
                    continue;
                }

                int nextWeight = graph[nextRow][nextColumn] == '1' ? 0 : 1;
                int nextPath = curr.path + nextWeight;

                if (nextPath < dist[nextRow][nextColumn]) {
                    dist[nextRow][nextColumn] = nextPath;
                    queue.offer(new PathPosition(nextRow, nextColumn, nextPath));
                }
            }
        }

        System.out.println(dist[n - 1][n - 1]);
    }

    static class PathPosition {
        int row;
        int column;
        int path;

        PathPosition(int row, int column, int path) {
            this.row = row;
            this.column = column;
            this.path = path;
        }
    }
}
