import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer> sequence;
    private static int n, m;
    private static final Stack<String> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        sequence = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        backtracking(0);

        bw.flush();
        bw.close();
    }

    private static void backtracking(int start) throws IOException {
        if (stack.size() == m) {
            bw.write(String.join(" ", stack));
            bw.newLine();
            return;
        }
        for (int next = start; next < n; next++) {
            stack.push(sequence.get(next).toString());
            backtracking(next);
            stack.pop();
        }
    }
}
