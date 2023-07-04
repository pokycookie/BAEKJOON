import sys
import heapq

input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[] for _ in range(N + 1)]
degree = [0 for _ in range(N + 1)]

ans = []
heap = []

for _ in range(M):
    A, B = map(int, input().split())
    graph[A].append(B)
    degree[B] += 1

for i in range(1, N + 1):
    if degree[i] == 0:
        heapq.heappush(heap, i)

while heap:
    vertex = heapq.heappop(heap)
    ans.append(vertex)
    for v in graph[vertex]:
        degree[v] -= 1
        if degree[v] == 0:
            heapq.heappush(heap, v)

print(*ans)
