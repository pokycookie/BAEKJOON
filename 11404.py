import sys

INF = float("inf")
input = sys.stdin.readline

# N: city, M: bus
N = int(input())
M = int(input())

# init graph
graph = [[INF if i != j else 0 for j in range(N + 1)] for i in range(N + 1)]
for _ in range(M):
    start, end, cost = map(int, input().split())
    graph[start][end] = min(graph[start][end], cost)

# Floyd algorithm
for waypoint in range(1, N + 1):
    for start in range(1, N + 1):
        for end in range(1, N + 1):
            newPath = graph[start][waypoint] + graph[waypoint][end]
            graph[start][end] = min(graph[start][end], newPath)

for start in range(1, N + 1):
    ans = []
    for end in range(1, N + 1):
        cost = graph[start][end]
        ans.append(cost if cost != INF else 0)
    print(" ".join(map(str, ans)))
