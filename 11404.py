import sys

INF = float("inf")
input = sys.stdin.readline

# N: city, M: bus
N = int(input())
M = int(input())

# init graph
graph = [[INF for _ in range(N)] for _ in range(N)]
for i in range(N):
    graph[i][i] = 0
for _ in range(M):
    start, end, cost = map(int, input().split())
    graph[start - 1][end - 1] = min(graph[start - 1][end - 1], cost)

# Floyd algorithm
for waypoint in range(N):
    for start in range(N):
        if waypoint == start or graph[start][waypoint] == INF:
            continue
        for end in range(N):
            newPath = graph[start][waypoint] + graph[waypoint][end]
            graph[start][end] = min(graph[start][end], newPath)

for start in range(N):
    ans = []
    for end in range(N):
        cost = graph[start][end]
        ans.append(cost if cost != INF else 0)
    print(" ".join(map(str, ans)))
