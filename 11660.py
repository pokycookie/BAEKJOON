import sys
input = sys.stdin.readline

N, M = map(int, input().split())

L = []
for _ in range(N):
    T = []
    S = 0
    for e in map(int, input().split()):
        S += e
        T.append(S)
    if L:
        for i, e in enumerate(L[-1]):
            T[i] += e
    L.append(T)

R = []
for _ in range(M):
    x1, y1, x2, y2 = map(lambda x: int(x) - 1, input().split())
    V1, V2, V3, V4 = [L[x2][y2], 0, 0, 0]
    if x1 - 1 >= 0: V2 = L[x1 - 1][y2]
    if y1 - 1 >= 0: V3 = L[x2][y1 - 1]
    if x1 - 1 >= 0 and y1 - 1 >= 0: V4 = L[x1 - 1][y1 - 1]
    R.append(V1 - V2 - V3 + V4)

print("\n".join(map(str, R)))
