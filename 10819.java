import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] sequence;
    static Stack<Integer> stack = new Stack<>();
    static boolean[] visited;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        sequence = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        visited = new boolean[n];
        backtracking();
        System.out.println(ans);
    }

    static void backtracking() {
        if (stack.size() == n) {
            ans = Math.max(ans, calculate(new ArrayList<>(stack)));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            stack.push(sequence[i]);
            visited[i] = true;
            backtracking();
            visited[i] = false;
            stack.pop();
        }
    }

    static int calculate(List<Integer> sequence) {
        int acc = 0;
        for (int i = 0; i < n - 1; i++) {
            acc += Math.abs(sequence.get(i) - sequence.get(i + 1));
        }
        return acc;
    }
}
