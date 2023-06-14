import sys

input = sys.stdin.readline

N = int(input())
T = [[0, []] for _ in range(N + 1)]
DP = [0 for _ in range(N + 1)]

for i in range(1, N + 1):
    L = list(map(int, input().split()))
    T[i] = [L[0], L[2:]]

ANS = 0
for i in range(1, N + 1):
    time, precs = T[i]

    if not precs:
        DP[i] = time
        ANS = max(ANS, DP[i])
        continue

    tmp = 0
    for prec in precs:
        tmp = max(tmp, DP[prec])

    DP[i] = tmp + time
    ANS = max(ANS, DP[i])

print(ANS)
