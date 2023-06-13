import sys

input = sys.stdin.readline

N = int(input())
DP = [0 for _ in range(max(N + 1, 3))]

DP[1] = 1
DP[2] = 3

for n in range(3, N + 1):
    DP[n] = DP[n - 1] + DP[n - 2] * 2

print(DP[N] % 10007)
