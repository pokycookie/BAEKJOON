# topology sort

import sys
from collections import deque

sys.setrecursionlimit(10**9)
input = sys.stdin.readline

N, M = map(int, input().split())

graph = [[] for _ in range(N + 1)]
degree = [0 for _ in range(N + 1)]
queue = deque()
ans = []

for _ in range(M):
    A, B = map(int, input().split())
    graph[A].append(B)
    degree[B] += 1

for vertex in range(1, N + 1):
    if degree[vertex] == 0:
        queue.append(vertex)

while queue:
    vertex = queue.popleft()
    ans.append(vertex)
    for v in graph[vertex]:
        degree[v] -= 1
        if degree[v] == 0:
            queue.append(v)

print(*ans)
