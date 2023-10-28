import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

        path = new List[101];
        for (int i = 0; i < 101; i++) {
            path[i] = new ArrayList<>();
        }

        dest = new int[10000];
        for (int i = 0; i < 10000; i++) {
            dest[i] = -1;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int person = Integer.parseInt(st.nextToken()) - 1;
            int laptop = Integer.parseInt(st.nextToken()) - 1;
            path[person].add(laptop);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            visited = new boolean[10000];
            if (matching(i)) {
                ans++;
            }
        }
        System.out.println(ans);
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
