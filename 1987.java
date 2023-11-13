import java.io.*;
import java.util.*;

public class Main {
    private static Stack<Integer> stack;
    private static char[][] map;
    private static final Set<Character> visited = new HashSet<>();
    private static int r, c;
    private static final int[][] direction = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static int ans = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for (int row = 0; row < r; row++) {
            char[] input = br.readLine().toCharArray();
            map[row] = input;
        }

        dfs(0, 0, 1);
        System.out.println(ans);
    }

    private static void dfs(int row, int column, int depth) {
        char alphabet = map[row][column];
        ans = Math.max(ans, depth);
        visited.add(alphabet);
        for (int[] next : direction) {
            int dr = next[0];
            int dc = next[1];
            if (row + dr < 0 || row + dr >= r) {
                continue;
            }
            if (column + dc < 0 || column + dc >= c) {
                continue;
            }
            char nextAlphabet = map[row + dr][column + dc];
            if (visited.contains(nextAlphabet)) {
                continue;
            }
            dfs(row + dr, column + dc, depth + 1);
        }
        visited.remove(alphabet);
    }
}
