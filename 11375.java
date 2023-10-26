import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] path;
    static int[] dest;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        path = new List[n];
        dest = new int[m];

        for (int i = 0; i < n; i++) {
            path[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            dest[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            while (s-- > 0) {
                int work = Integer.parseInt(st.nextToken());
                path[i].add(work - 1);
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            visited = new boolean[m];
            if (matching(i)) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    public static boolean matching(int start) {
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
