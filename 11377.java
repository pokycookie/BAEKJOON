import java.io.*;
import java.util.*;

public class Main {
    private static List<Integer>[] path;
    private static int[] dest;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        path = new List[n];
        for (int i = 0; i < n; i++) {
            path[i] = new ArrayList<>();
        }

        dest = new int[m];
        for (int i = 0; i < m; i++) {
            dest[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            while (cnt -- > 0) {
                int vertex = Integer.parseInt(st.nextToken());
                path[i].add(vertex - 1);
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            visited = new boolean[m];
            if (matching(i)) {
                ans++;
            }
        }

        for (int i = 0; i < n; i++) {
            visited = new boolean[m];
            if (matching(i)) {
                ans++;
                k--;
            }
            if (k <= 0) {
                break;
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
        bw.close();
    }

    private static boolean matching(int start) {
        for (int end : path[start]) {
            if (visited[end]) {
                continue;
            }
            visited[end] = true;
            if (dest[end] == -1 || matching(dest[end])) {
                dest[end] = start;
                return true;
            }
        }
        return false;
    }
}
