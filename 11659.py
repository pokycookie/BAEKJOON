import sys
input = sys.stdin.readline

N, M = map(int, input().split())
T = list(map(int, input().split()))
L = [0]

for i in T:
    L.append(L[-1] + i)
L.pop(0)

R = []
for _ in range(M):
    i, j = map(int, input().split())
    if i - 2 < 0:
        R.append(L[j - 1])
    else:
        R.append(L[j - 1] - L[i - 2])

for r in R:
    print(r)
