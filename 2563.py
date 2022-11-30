import sys
input = sys.stdin.readline

T = int(input())
L = [[0] * 100 for _ in range(100)]
for _ in range(T):
    R, C = map(int, input().split())
    for r in range(R, R + 10):
        for c in range(C, C + 10):
            L[r][c] = 1

S = 0
for r in L: S += sum(r)
print(S)
