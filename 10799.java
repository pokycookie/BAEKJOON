import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();
        int stack = 0;
        int ans = 0;

        char prev = '?';
        for (char bracket : input) {
            if (bracket == '(') {
                stack++;
                prev = bracket;
                continue;
            }
            stack--;
            if (prev == '(') {
                // laser
                ans += stack;
            } else {
                // end
                ans++;
            }
            prev = bracket;
        }

        System.out.println(ans);
    }
}
