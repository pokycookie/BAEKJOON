import java.io.*;
import java.util.*;

public class Main {
    private static int[][] graph;
    private static boolean[][] visited;
    private static int n;

    private static Coord[] direction = {
            new Coord(0, -1),
            new Coord(0, 1),
            new Coord(-1, 0),
            new Coord(1, 0)
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];

        int maxHeight = 0;

        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 0; column < n; column++) {
                int height = Integer.parseInt(st.nextToken());
                graph[row][column] = height;
                maxHeight = Math.max(maxHeight, height);
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < maxHeight; i ++) {
            visited = new boolean[n][n];
            int safeZone = 0;
            for (int row = 0; row < n; row++) {
                for (int column = 0; column < n; column++) {
                    if (visited[row][column]) {
                        continue;
                    }
                    if (graph[row][column] > i) {
                        safeZone++;
                    }
                    bfs(row, column, i);
                }
            }
            ans = Math.max(ans, safeZone);
        }

        System.out.println(ans);
    }

    private static void bfs(int startRow, int startColumn, int threshold) {
        Queue<Coord> queue = new LinkedList<>();
        queue.add(new Coord(startRow, startColumn));
        boolean type = graph[startRow][startColumn] > threshold;

        while (!queue.isEmpty()) {
            Coord curr = queue.poll();
            for (Coord d : direction) {
                Coord next = new Coord(curr.row + d.row, curr.column + d.column);
                if (next.row < 0 || next.row > n - 1) {
                    continue;
                }
                if (next.column < 0 || next.column > n - 1) {
                    continue;
                }
                if (visited[next.row][next.column]) {
                    continue;
                }
                boolean nextType = graph[next.row][next.column] > threshold;
                if (type != nextType) {
                    continue;
                }
                visited[next.row][next.column] = true;
                queue.add(next);
            }
        }
    }
}

class Coord {
    int row;
    int column;

    public Coord(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
