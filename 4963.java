import java.io.*;
import java.util.*;

public class Main {
    private static int[][] graph;
    private static boolean[][] visited;
    private static int w;
    private static int h;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final Coord[] direction = {
            new Coord(0, -1),
            new Coord(0, 1),
            new Coord(-1, 0),
            new Coord(1, 0),
            new Coord(1, 1),
            new Coord(1, -1),
            new Coord(-1, 1),
            new Coord(-1, -1)
    };

    public static void main(String[] args) throws IOException {
        while (solution()) {
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static boolean solution() throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        if (w == 0 && h == 0) {
            return false;
        }

        graph = new int[h][w];

        for (int row = 0; row < h; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 0; column < w; column++) {
                int height = Integer.parseInt(st.nextToken());
                graph[row][column] = height;
            }
        }

        int island = 0;
        visited = new boolean[h][w];

        for (int row = 0; row < h; row++) {
            for (int column = 0; column < w; column++) {
                if (visited[row][column]) {
                    continue;
                }
                if (graph[row][column] == 1) {
                    island++;
                    bfs(row, column);
                }
            }
        }

        bw.write(Integer.toString(island));
        return true;
    }

    private static void bfs(int startRow, int startColumn) {
        Queue<Coord> queue = new LinkedList<>();
        queue.add(new Coord(startRow, startColumn));

        while (!queue.isEmpty()) {
            Coord curr = queue.poll();
            for (Coord d : direction) {
                Coord next = new Coord(curr.row + d.row, curr.column + d.column);
                if (next.row < 0 || next.row > h - 1) {
                    continue;
                }
                if (next.column < 0 || next.column > w - 1) {
                    continue;
                }
                if (visited[next.row][next.column]) {
                    continue;
                }
                if (graph[next.row][next.column] == 0) {
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
