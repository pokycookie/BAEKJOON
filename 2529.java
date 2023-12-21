import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int n;
    static List<Integer> signs;
    static Stack<Integer> stack;
    static boolean[] visited;
    static String min;
    static String max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        signs = Arrays.stream(br.readLine().split(" "))
                .map(it -> it.equals("<") ? 0 : 1)
                .collect(Collectors.toList());

        visited = new boolean[10];
        stack = new Stack<>();
        backtracking(false);

        visited = new boolean[10];
        stack = new Stack<>();
        backtracking(true);

        System.out.println(max);
        System.out.println(min);
    }

    static boolean backtracking(boolean reverse) {
        if (stack.size() == n + 1) {
            List<Integer> sequence = new ArrayList<>(stack);
            if (check(sequence)) {
                if (reverse) {
                    max = sequence.stream().map(Object::toString).collect(Collectors.joining(""));
                    return true;
                }
                min = sequence.stream().map(Object::toString).collect(Collectors.joining(""));
                return true;
            }
            return false;
        }
        for (int j = 0; j <= 9; j++) {
            int i = reverse ? 9 - j : j;
            if (visited[i]) {
                continue;
            }
            stack.push(i);
            visited[i] = true;
            if (backtracking(reverse)) {
                return true;
            }
            visited[i] = false;
            stack.pop();
        }
        return false;
    }

    static boolean check(List<Integer> sequence) {
        if (sequence.size() != n + 1) {
            return false;
        }
        Queue<Integer> queue = new LinkedList<>(sequence);
        int prev = queue.poll();
        for (int i = 0; i < n; i++) {
            if (queue.isEmpty()) {
                return false;
            }
            int curr = queue.poll();
            if (signs.get(i) == 0 && prev >= curr) {
                return false;
            }
            if (signs.get(i) == 1 && prev <= curr) {
                return false;
            }
            prev = curr;
        }
        return true;
    }
}
