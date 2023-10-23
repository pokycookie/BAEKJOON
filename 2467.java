import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> solution = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            solution.add(Integer.parseInt(st.nextToken()));
        }
        solution.sort(Comparator.naturalOrder());

        Answer ans = new Answer(1000000000, 1000000000);

        int left = 0;
        int right = solution.size() - 1;

        while (left < right) {
            int diff = Math.abs(solution.get(left) + solution.get(right));
            int leftMove = Math.abs(solution.get(left + 1) + solution.get(right));
            int rightMove = Math.abs(solution.get(left) + solution.get(right - 1));

            if (diff < ans.diff) {
                ans = new Answer(solution.get(left), solution.get(right));
            }

            if (leftMove < rightMove) {
                left++;
            } else {
                right--;
            }
        }

        ans.print();
    }
}

class Answer {
    int v1;
    int v2;
    int diff;

    public Answer(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
        this.diff = Math.abs(v1 + v2);
    }

    public void print() {
        System.out.println(v1 + " " + v2);
    }
}
