import sys
import heapq

sys.setrecursionlimit(10**9)
input = sys.stdin.readline
INF = float("inf")

N, M, X = map(int, input().split())
graph = [[] for _ in range(N + 1)]

for _ in range(M):
    start, end, time = map(int, input().split())
    graph[start].append((time, end))


def dijkstra(start: int, end: int):
    distances = [INF for _ in range(N + 1)]
    heap = []

    distances[start] = 0
    heapq.heappush(heap, (0, start))

    while heap:
        dist, vertex = heapq.heappop(heap)

        if distances[vertex] < dist:
            continue
        for next_dist, next_vertex in graph[vertex]:
            new_path = distances[vertex] + next_dist
            if new_path < distances[next_vertex]:
                distances[next_vertex] = new_path
                heapq.heappush(heap, (new_path, next_vertex))

    return distances[end]


ans = 0
for i in range(1, N + 1):
    time = dijkstra(i, X) + dijkstra(X, i)
    ans = max(ans, time)

print(ans)
