import sys

sys.setrecursionlimit(10**9)
input = sys.stdin.readline

N = int(input())
MAP = [list(input().rstrip()) for _ in range(N)]
MAP_WEEK = [
    [MAP[n][m] if MAP[n][m] != "R" else "G" for m in range(N)] for n in range(N)
]
visited = [[False for _ in range(N)] for _ in range(N)]

ANS = []
ANS_WEEK = []


def boom(n: int, m: int, MAP: list, color: str):
    if visited[n][m]:
        return
    visited[n][m] = True
    for dn, dm in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
        if n + dn < 0 or n + dn >= N:
            continue
        if m + dm < 0 or m + dm >= N:
            continue
        if MAP[n + dn][m + dm] == color:
            boom(n + dn, m + dm, MAP, color)


for n in range(N):
    for m in range(N):
        if visited[n][m]:
            continue
        ANS.append(MAP[n][m])
        boom(n, m, MAP, MAP[n][m])

visited = [[False for _ in range(N)] for _ in range(N)]
for n in range(N):
    for m in range(N):
        if visited[n][m]:
            continue
        ANS_WEEK.append(MAP_WEEK[n][m])
        boom(n, m, MAP_WEEK, MAP_WEEK[n][m])

print(len(ANS), len(ANS_WEEK))
