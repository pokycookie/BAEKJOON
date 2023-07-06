# use Topology Sort

import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[] for _ in range(N + 1)]
degree = [0 for _ in range(N + 1)]

for _ in range(M):
    input_list = list(map(int, input().split()))
    for i in range(1, len(input_list) - 1):
        curr_vertex, next_vertex = input_list[i], input_list[i + 1]
        graph[curr_vertex].append(next_vertex)
        degree[next_vertex] += 1

queue = deque()
for i in range(1, N + 1):
    if degree[i] == 0:
        queue.append(i)

ans = []
while queue:
    vertex = queue.popleft()
    ans.append(vertex)
    for next_vertex in graph[vertex]:
        degree[next_vertex] -= 1
        if degree[next_vertex] == 0:
            queue.append(next_vertex)

if len(ans) == N:
    print("\n".join(map(str, ans)))
else:
    print(0)
