import sys

sys.setrecursionlimit(10**9)

input = sys.stdin.readline

Y, X = map(int, input().split())
MAP_ORIGIN = [list(map(int, input().split())) for _ in range(Y)]
MAP = [[MAP_ORIGIN[y][x] for x in range(X)] for y in range(Y)]
DELTA = [(0, 1), (0, -1), (1, 0), (-1, 0)]

visited = [[False for _ in range(X)] for _ in range(Y)]


def spread(y: int, x: int):
    MAP[y][x] = 2
    visited[y][x] = True
    for dy, dx in DELTA:
        cy, cx = y + dy, x + dx
        if not (0 <= cy < Y and 0 <= cx < X):
            continue
        if visited[cy][cx]:
            continue
        if MAP[cy][cx] == 0 or MAP[cy][cx] == 2:
            spread(cy, cx)


def poison():
    flag = True
    for y in range(Y):
        for x in range(X):
            if visited[y][x]:
                continue
            if MAP[y][x] != 2:
                continue
            for dy, dx in DELTA:
                cy, cx = y + dy, x + dx
                if not (0 <= cy < Y and 0 <= cx < X):
                    continue
                if MAP[cy][cx] == 1:
                    MAP[cy][cx] = 2
                    visited[cy][cx] = True
                    flag = False

    return flag


cnt = 0
last = None
spread(0, 0)
while True:
    visited = [[False for _ in range(X)] for _ in range(Y)]
    if poison():
        break
    cnt += 1
    if (tmp := [MAP[i // X][i % X] for i in range(X * Y)].count(1)) > 0:
        last = tmp
    visited = [[False for _ in range(X)] for _ in range(Y)]
    spread(0, 0)

print(cnt)
print(last if last else [MAP_ORIGIN[i // X][i % X] for i in range(X * Y)].count(1))
