import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Stack<String> stack = new Stack<>();
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        backtracking();
        bw.flush();
        bw.close();
    }

    static void backtracking() throws IOException {
        if (stack.size() == n) {
            bw.write(String.join(" ", stack));
            bw.newLine();
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            stack.push(Integer.toString(i + 1));
            visited[i] = true;
            backtracking();
            visited[i] = false;
            stack.pop();
        }
    }
}
