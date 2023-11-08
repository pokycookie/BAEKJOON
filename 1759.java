import java.io.*;
import java.util.*;

public class Main {
    private static final List<Character> vowels = new ArrayList<>();
    private static final List<Character> allowed = new ArrayList<>();
    private static final Stack<String> stack = new Stack<>();
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static {
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            allowed.add(st.nextToken().charAt(0));
        }
        allowed.sort((a, b) -> a - b);

        backtracking(0, l);
        bw.flush();
        bw.close();
    }

    private static void backtracking(int start, int length) throws IOException {
        if (stack.size() == length && checkConsonant()) {
            if (checkVowels()) {
                bw.write(String.join("", stack));
                bw.newLine();
            }
            return;
        }
        for (int next = start; next < allowed.size(); next++) {
            stack.push(allowed.get(next).toString());
            backtracking(next + 1, length);
            stack.pop();
        }
    }

    private static boolean checkVowels() {
        for (String c : stack) {
            if (vowels.contains(c.charAt(0))) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkConsonant() {
        int cnt = 0;
        for (String c : stack) {
            if (!vowels.contains(c.charAt(0))) {
                cnt++;
            }
            if (cnt >= 2) {
                return true;
            }
        }
        return false;
    }
}
