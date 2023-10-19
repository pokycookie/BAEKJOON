import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> budgets = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int m = Integer.parseInt(br.readLine());

        if (budgets.stream().mapToInt(Integer::intValue).sum() <= m) System.out.println(Collections.max(budgets));
        else System.out.println(new Budget(budgets, m).binarySearch());
    }
}

class Budget {
    private final List<Integer> budgets;
    private final int limit;

    public Budget(List<Integer> budgets, int limit) {
        this.budgets = budgets;
        this.limit = limit;
    }

    private boolean check(int limit) {
        int res = budgets.stream().mapToInt(it -> Math.min(it, limit)).sum();
        return res <= this.limit;
    }

    public int binarySearch() {
        int left = 0; // true
        int right = limit; // false

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (check(mid)) left = mid;
            else right = mid;
        }
        return left;
    }
}
