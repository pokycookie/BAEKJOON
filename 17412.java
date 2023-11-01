import java.io.*;
import java.util.*;

public class Main {
    private static int[][] capacity;
    private static int[][] flow;
    private static List<Integer>[] graph;
    private static int[] visited;

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // capacity[v1][v2] := v1에서 v2로 이동하는 간선의 용량
        capacity = new int[n][n];
        // flow[v1][v2] := v1에서 v2로 현재 흐르고 있는 유량
        flow = new int[n][n];

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            graph[v1].add(v2); // 간선
            graph[v2].add(v1); // 역간선
            capacity[v1][v2] = 1;
        }

        System.out.println(maxFlow(0, 1));
    }

    private static void bfs(int start, int end) {
        // 정점 방문 여부를 -1로 초기화
        visited = new int[n];
        for (int i = 0; i < n; i++) {
            visited[i] = -1;
        }

        // 큐를 생성 및 시작점을 추가
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        // 시작점에서 도착점까지 최단으로 도달하는 경로 탐색
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : graph[curr]) {
                // 다음 정점이 이미 방문한 적 있다면 continue
                if (visited[next] != -1) {
                    continue;
                }
                // 다음 정점의 잔여 용량이 남아있지 않다면 continue
                if (capacity[curr][next] - flow[curr][next] <= 0) {
                    continue;
                }
                queue.add(next); // 큐에 다음 정점을 추가
                visited[next] = curr; // 이전 정점을 저장하여 방문을 표시

                // 원하는 도착점에 도달한 경우 탐색을 종료
                if (next == end) {
                    break;
                }
            }
        }
    }

    private static int maxFlow(int source, int sink) {
        int result = 0;

        while (true) {
            // 시작점에서 도착점까지의 가장 빠른 경로를 탐색
            bfs(source, sink);

            // 모든 탐색을 했음에도 도착점에 도달할 수 없는 경우
            // 더 이상의 증가 경로가 없음
            if (visited[sink] == -1) {
                break;
            }
            
            // 이번 경로에서 최대 사용할 수 있는 유량의 크기
            int currPathFlow = Integer.MAX_VALUE;
            
            // 도착점에서 시작점으로 이전 정점을 하나씩 찾아가며 되돌아감
            // 되돌아가면서 사용할 수 있던 잔여 용량 중 가장 작은 값을 찾음
            // 가장 작은 값이 해당 경로에서 최대로 사용할 수 있는 유량의 크기 (currPathFlow)
            for (int curr = sink; curr != source; curr = visited[curr]) {
                int prev = visited[curr];
                currPathFlow = Math.min(currPathFlow, capacity[prev][curr] - flow[prev][curr]);
            }

            // 도착점에서 시작점으로 이전 정점을 하나씩 찾아가며 되돌아감
            // 되돌아가면서 실제 사용한 유량을 적용함
            for (int curr = sink; curr != source; curr = visited[curr]) {
                int prev = visited[curr];
                flow[prev][curr] += currPathFlow; // 정방향 간선은 유량이 증가함
                flow[curr][prev] -= currPathFlow; // 역방향 간선은 유량이 감소함
            }

            result += currPathFlow;
        }

        return result;
    }
}
