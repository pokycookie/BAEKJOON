import sys

input = sys.stdin.readline

INF = float("inf")

N = int(input())
L = [list(map(int, input().split())) for _ in range(N)]
ANS = INF

for j in range(3):
    DP = [[INF, INF, INF] for _ in range(N)]
    DP[0][j] = L[0][j]

    for i in range(1, N):
        DP[i][0] = L[i][0] + min(DP[i - 1][1], DP[i - 1][2])
        DP[i][1] = L[i][1] + min(DP[i - 1][0], DP[i - 1][2])
        DP[i][2] = L[i][2] + min(DP[i - 1][0], DP[i - 1][1])

    for tj in range(3):
        if j == tj:
            continue
        ANS = min(ANS, DP[N - 1][tj])

print(ANS)
