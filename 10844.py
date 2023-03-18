N = int(input())
DP = [[0 for _ in range(10)] for _ in range(N)]
DP[0] = [1 for _ in range(10)]

for n in range(1, N):
    for i in range(10):
        if i == 0:
            DP[n][i] = DP[n - 1][i + 1]
        elif i == 9:
            DP[n][i] = DP[n - 1][i - 1]
        else:
            DP[n][i] = DP[n - 1][i + 1] + DP[n - 1][i - 1]

print((sum(DP[N - 1]) - DP[N - 1][0]) % 1000000000)
