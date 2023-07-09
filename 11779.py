import sys
import heapq

sys.setrecursionlimit(10**9)
input = sys.stdin.readline
INF = float("inf")

N = int(input())
M = int(input())
graph = [[] for _ in range(N + 1)]

for _ in range(M):
    start, end, cost = map(int, input().split())
    graph[start].append((cost, end))

START, END = map(int, input().split())


def dijkstra(start: int, end: int):
    distances = [INF for _ in range(N + 1)]
    before = [None for _ in range(N + 1)]
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
                before[next_vertex] = vertex
                heapq.heappush(heap, (new_path, next_vertex))

    cnt = 0
    before_arr = []
    head = end
    while head:
        before_arr.append(head)
        cnt += 1
        head = before[head]

    before_arr.reverse()
    return [distances[end], cnt, before_arr]


a, b, c = dijkstra(START, END)
print(a)
print(b)
print(*c)
