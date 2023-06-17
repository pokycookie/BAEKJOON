import sys

sys.setrecursionlimit(10**9)
input = sys.stdin.readline

N = int(input())
P = [0] + list(map(int, input().split()))
L = [[0, 0]] + [list(map(int, input().split())) for _ in range(N - 1)]

TREE = [[] for _ in range(N + 1)]
DP = [[0, 0] for _ in range(N + 1)]
visited = [False for _ in range(N + 1)]

for i in range(1, N):
    v1, v2 = L[i]
    TREE[v1].append(v2)
    TREE[v2].append(v1)


def DFS(node: int):
    visited[node] = True
    for child in TREE[node]:
        if visited[child]:
            continue
        DFS(child)
        DP[node][0] += max(DP[child])
        DP[node][1] += DP[child][0]

    DP[node][1] += P[node]


DFS(1)
print(max(DP[1]))
