import sys

sys.setrecursionlimit(10**9)
input = sys.stdin.readline

N = int(input())
W = [0] + list(map(int, input().split()))

DP = [[[0, []], [0, []]] for _ in range(N + 1)]
TREE = [[] for _ in range(N + 1)]
visited = [False for _ in range(N + 1)]

for _ in range(N - 1):
    n1, n2 = map(int, input().split())
    TREE[n1].append(n2)
    TREE[n2].append(n1)


def DFS(startNode: int):
    visited[startNode] = True
    DP[startNode][1][0] = W[startNode]
    DP[startNode][1][1] = [startNode]

    for node in TREE[startNode]:
        if visited[node]:
            continue
        DFS(node)

        flag = 0 if DP[node][0][0] > DP[node][1][0] else 1
        DP[startNode][0] = [
            DP[startNode][0][0] + DP[node][flag][0],
            DP[startNode][0][1] + DP[node][flag][1],
        ]
        DP[startNode][1] = [
            DP[startNode][1][0] + DP[node][0][0],
            DP[startNode][1][1] + DP[node][0][1],
        ]

    flag = 0 if DP[startNode][0][0] > DP[startNode][1][0] else 1
    return [DP[startNode][flag][0], DP[startNode][flag][1]]


MAX = DFS(1)
print(MAX[0])
print(" ".join(map(str, sorted(MAX[1]))))
