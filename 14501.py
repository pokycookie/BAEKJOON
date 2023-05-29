import sys

input = sys.stdin.readline

N = int(input())
L = [0] + [tuple(map(int, input().split())) for _ in range(N)]  # (time, price)
DP = [0] + [0 for _ in range(N)] + [0]

for i in range(N, 0, -1):
    time, price = L[i]
    if time + i > N + 1:
        DP[i] = DP[i + 1]
    else:
        DP[i] = max(DP[i + 1], DP[i + time] + price)

print(DP[1])
