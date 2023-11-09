import java.io.*;
import java.util.*;

public class Main {
    private static final List<Integer> sequence = new ArrayList<>();
    private static final Stack<Integer> stack = new Stack<>();
    private static int ans = 0;
    private static int n, s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            sequence.add(value);
        }

        backtracking(0);
        System.out.println(ans);
    }

    private static void backtracking(int start) {
        int sum = stack.stream().mapToInt(Integer::intValue).sum();
        if (!stack.isEmpty() && sum == s) {
            ans++;
        }
        for (int i = start; i < n; i++) {
            stack.push(sequence.get(i));
            backtracking(i + 1);
            stack.pop();
        }
    }
}
