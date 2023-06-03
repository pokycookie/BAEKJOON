import sys

input = sys.stdin.readline

N = int(input())
L = [[]] + [list(map(int, input().split())) for _ in range(N)]
DP = [None for _ in range(N + 1)]


def getDP(i: int):
    time, precs = L[i][0], L[i][1:-1]
    visited = set()

    if DP[i]:
        return DP[i]

    if not precs:
        DP[i] = time
        return DP[i]

    tmpTime = 0
    for prec in precs:
        if prec in visited:
            continue
        visited.update([prec] + L[prec][1:-1])
        tmpTime = max(tmpTime, DP[prec] if DP[prec] else getDP(prec))

    DP[i] = time + tmpTime
    return DP[i]


ANS = [getDP(i) for i in range(1, N + 1)]
print("\n".join(map(str, ANS)))
