import sys

input = sys.stdin.readline

N = int(input())
L = [[0, 0]] + [list(map(int, input().split())) for _ in range(N)]
DP = [0 for _ in range(N + 2)]

for i in range(N, 0, -1):
    Ti, Pi = L[i]

    if Ti > N - i + 1:
        DP[i] = DP[i + 1]
        continue

    DP[i] = max(DP[i + 1], DP[i + Ti] + Pi)

print(DP[1])
