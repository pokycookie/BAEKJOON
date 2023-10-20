import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Group sunlinWorld = new Group(n);
        for (int i = 0; i < n - 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            sunlinWorld.union(v1, v2);
        }
        StringJoiner sj = new StringJoiner(" ");
        sunlinWorld.getLeader().forEach(it -> sj.add(it.toString()));
        System.out.println(sj.toString());
    }
}

class Group {
    int[] parents;

    public Group(int n) {
        this.parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    public int find(int vertex) {
        if (parents[vertex] == vertex) return vertex;
        return find(parents[vertex]);
    }

    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 < p2) parents[p2] = p1;
        else parents[p1] = p2;
    }

    public List<Integer> getLeader() {
        // distinct 메서드를 사용해 중복제거
        return Arrays.stream(parents).map(it -> find(it) + 1).boxed().distinct().collect(Collectors.toList());
    }
}
