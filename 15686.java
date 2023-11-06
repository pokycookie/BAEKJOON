// ref: https://steady-coding.tistory.com/23

import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static List<Coord> houses;
    static List<Coord> stores;
    static int ans;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        houses = new ArrayList<>();
        stores = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    houses.add(new Coord(i, j));
                } else if (map[i][j] == 2) {
                    stores.add(new Coord(i, j));
                }
            }
        }

        ans = Integer.MAX_VALUE;
        visited = new boolean[stores.size()];

        DFS(0, 0);
        bw.write(Integer.toString(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    public static void DFS(int start, int cnt) {
        if (cnt == m) {
            int res = 0;
            for (Coord coord : houses) {
                int temp = Integer.MAX_VALUE;
                for (int j = 0; j < stores.size(); j++) {
                    if (visited[j]) {
                        int dist = Math.abs(coord.column - stores.get(j).column) + Math.abs(coord.row - stores.get(j).row);
                        temp = Math.min(temp, dist);
                    }
                }
                res += temp;
            }
            ans = Math.min(ans, res);
            return;
        }

        for (int i = start; i < stores.size(); i++) {
            visited[i] = true;
            DFS(i + 1, cnt + 1);
            visited[i] = false;
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
