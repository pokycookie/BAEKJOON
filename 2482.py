N = int(input())
K = int(input())
DP = [[0 for _ in range(K + 1)] for _ in range(N + 1)]

for n in range(N + 1):
    DP[n][1] = n

for k in range(2, K + 1):
    for n in range(4, N + 1):
        DP[n][k] = DP[n - 1][k] + DP[n - 2][k - 1]

print(DP[N][K] % 1000000003)
