import sys
input = sys.stdin.readline

N, B = map(int, input().split())

A = []
for _ in range(N):
    A.append(list(map(int, input().split())))

def square(L1: list, L2: list):
    R = [[0 for _ in range(N)] for _ in range(N)]
    for n1 in range(N):
        for n2 in range(N):
            S = 0
            for i in range(N):
                S += (L1[n1][i] * L2[i][n2]) % 1000
            R[n1][n2] = S % 1000
    return R

def power(L: list, e: int):
    if e == 1:
        return [[l % 1000 for l in sub] for sub in L]
    else:
        if e % 2 == 0:
            p = power(L, e // 2)
            return square(p, p)
        else:
            p = power(L, e // 2)
            return square(square(p, p), L)

for r in power(A, B):
    print(" ".join(map(str, r)))
