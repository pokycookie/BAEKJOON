import sys
input = sys.stdin.readline

n, k = map(int, input().split())
coins = [int(input()) for _ in range(n)]
# DP[i] = 가치의 합을 i로 만들기 위한 경우의 수
DP = [0 for _ in range(k + 1)]
DP[0] = 1

for coin in coins:
    for i in range(coin, k + 1):
        if i - coin < 0:
            continue
        DP[i] += DP[i - coin]

print(DP[k])
