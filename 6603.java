import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static Stack<String> stack;
    private static List<Integer> sequence;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            stack = new Stack<>();
            sequence = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if (k == 0) {
                break;
            }

            for (int i = 0; i < k; i ++) {
                int s = Integer.parseInt(st.nextToken());
                sequence.add(s);
            }

            backtracking(0);
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    public static void backtracking(int start) throws IOException {
        if (stack.size() == 6) {
            bw.write(String.join(" ", stack));
            bw.newLine();
        }
        for (int next = start; next < sequence.size(); next++) {
            stack.push(sequence.get(next).toString());
            backtracking(next + 1);
            stack.pop();
        }
    }
}
