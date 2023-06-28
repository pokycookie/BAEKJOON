import sys
from collections import deque

input = sys.stdin.readline

N = int(input())
MAP = [list(map(int, input().split())) for _ in range(N)]
DELTA = [(0, 1), (0, -1), (1, 0), (-1, 0)]

visited = [[False for _ in range(N)] for _ in range(N)]
islands = []


def checkEdge(y: int, x: int):
    edge = 0
    for dy, dx in DELTA:
        ty, tx = y + dy, x + dx
        if not (0 <= ty < N and 0 <= tx < N) == 1 or MAP[ty][tx]:
            edge += 1

    return False if edge >= 4 else True


def BFS():
    for y in range(N):
        for x in range(N):
            if visited[y][x]:
                continue
            if MAP[y][x] == 0:
                continue
            islands.append([])
            queue = deque([(y, x)])
            while queue:
                cy, cx = queue.popleft()
                if visited[cy][cx]:
                    continue
                visited[cy][cx] = True
                if checkEdge(cy, cx):
                    islands[-1].append((cy, cx))
                for dy, dx in DELTA:
                    ty, tx = cy + dy, cx + dx
                    if not (0 <= ty < N and 0 <= tx < N):
                        continue
                    if MAP[ty][tx] == 1:
                        queue.append((ty, tx))


BFS()
ans = float("INF")

for i in range(len(islands)):
    for j in range(i + 1, len(islands)):
        for y1, x1 in islands[i]:
            for y2, x2 in islands[j]:
                dy, dx = abs(y1 - y2), abs(x1 - x2)
                ans = min(ans, dy + dx - 1)

print(ans)
