import sys

input = sys.stdin.readline

DP = [[0 for _ in range(31)] for _ in range(31)]

for i in range(1, 31):
    DP[i][0] = 1
    for j in range(1, i + 1):
        DP[i][j] = DP[i][j - 1] + DP[i - 1][j]

while True:
    N = int(input())
    if N == 0:
        break

    print(DP[N][N])
