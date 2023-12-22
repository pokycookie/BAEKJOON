import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int n, m;
    static List<Integer> sequence;
    static Stack<String> stack = new Stack<>();
    static Set<String> set = new HashSet<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =  new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sequence = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .sorted()
                .collect(Collectors.toList());

        backtracking(0);
        bw.flush();
        bw.close();
    }

    static void backtracking(int start) throws IOException {
        if (stack.size() == m) {
            String stackString = String.join(" ", stack);
            if (!set.contains(stackString)) {
                bw.write(stackString);
                bw.newLine();
                set.add(stackString);
            }
        }
        for (int i = start; i < n; i++) {
            stack.push(Integer.toString(sequence.get(i)));
            backtracking(i + 1);
            stack.pop();
        }
    }
}
