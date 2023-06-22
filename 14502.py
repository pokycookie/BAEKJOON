import sys
from itertools import combinations

input = sys.stdin.readline

N, M = map(int, input().split())
MAP_ORIGIN = [list(map(int, input().split())) for _ in range(N)]

MAP = [[MAP_ORIGIN[n][m] for m in range(M)] for n in range(N)]
VIRUS = [(n, m) for n in range(N) for m in range(M) if MAP_ORIGIN[n][m] == 2]
visited = [[False for _ in range(M)] for _ in range(N)]


def boom(n: int, m: int):
    if visited[n][m]:
        return
    visited[n][m] = True
    MAP[n][m] = 2
    for dn, dm in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
        if n + dn < 0 or n + dn >= N:
            continue
        if m + dm < 0 or m + dm >= M:
            continue
        curr = MAP[n + dn][m + dm]
        if curr == 0:
            boom(n + dn, m + dm)


ANS = 0

for comb in combinations([(n, m) for n in range(N) for m in range(M)], 3):
    v1, v2, v3 = [MAP_ORIGIN[c[0]][c[1]] for c in comb]
    if v1 != 0 or v2 != 0 or v3 != 0:
        continue
    MAP = [[MAP_ORIGIN[n][m] for m in range(M)] for n in range(N)]
    visited = [[False for _ in range(M)] for _ in range(N)]
    for c in comb:
        MAP[c[0]][c[1]] = 1
    for virus in VIRUS:
        boom(virus[0], virus[1])

    ANS = max(ANS, len([True for n in range(N) for m in range(M) if MAP[n][m] == 0]))

print(ANS)
