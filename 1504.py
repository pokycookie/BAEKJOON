import sys
import heapq

input = sys.stdin.readline
INF = float("inf")

N, E = map(int, input().split())
graph = [[] for _ in range(N + 1)]

for _ in range(E):
    a, b, c = map(int, input().split())
    graph[a].append((c, b))
    graph[b].append((c, a))

v1, v2 = map(int, input().split())


def dijkstra(start: int, end: int):
    distances = [INF for _ in range(N + 1)]
    heap = []

    # Initialize
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

    return distances[end] if distances[end] != INF else -1


start_v1 = dijkstra(1, v1)
start_v2 = dijkstra(1, v2)
end_v1 = dijkstra(v1, N)
end_v2 = dijkstra(v2, N)
v1_v2 = dijkstra(v1, v2)

if -1 in [start_v1, start_v2, end_v1, end_v2, v1_v2]:
    print(-1)
else:
    path1 = start_v1 + v1_v2 + end_v2
    path2 = start_v2 + v1_v2 + end_v1
    print(min(path1, path2))
