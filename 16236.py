import sys
from collections import deque

input = sys.stdin.readline

N = int(input())
SPACE = [list(map(int, input().split())) for _ in range(N)]
DELTA = [(1, 0), (-1, 0), (0, 1), (0, -1)]
INF = float("inf")

shark_x, shark_y = 0, 0
shark_size = 2
shark_food = 0
ans = 0

for y in range(N):
    for x in range(N):
        if SPACE[x][y] == 9:
            shark_x, shark_y = x, y
            SPACE[x][y] = 0


def BFS():
    queue = deque([(shark_x, shark_y)])
    DIST = [[-1 for _ in range(N)] for _ in range(N)]
    DIST[shark_x][shark_y] = 0

    while queue:
        cx, cy = queue.popleft()

        for dx, dy in DELTA:
            currX, currY = cx + dx, cy + dy

            # SPACE범위를 벗어나는 경우
            if not (0 <= currX < N and 0 <= currY < N):
                continue
            # 먹이의 크기가 더 큰 경우
            if shark_size < SPACE[currX][currY]:
                continue
            # 이미 방문한 경우
            if DIST[currX][currY] != -1:
                continue

            # 방문한 위치까지의 최단거리를 저장 (BFS이므로 자연스럽게 최단거리)
            DIST[currX][currY] = DIST[cx][cy] + 1
            queue.append((currX, currY))

    return DIST


def seek():
    pos_x, pos_y = 0, 0
    dist = INF
    DIST = BFS()

    for x in range(N):
        for y in range(N):
            # BFS로 방문할 수 없는 곳인 경우
            if DIST[x][y] == -1:
                continue
            # 빈 칸인 경우
            if SPACE[x][y] == 0:
                continue
            # 아기 상어가 먹을 수 없는 경우
            if SPACE[x][y] >= shark_size:
                continue
            # 최소거리를 계속하여 갱신
            if DIST[x][y] < dist:
                dist = DIST[x][y]
                pos_x, pos_y = x, y

    # 먹을 물고기가 존재하지 않는 경우
    if dist == INF:
        return None

    return pos_x, pos_y, dist


while True:
    if not (fish := seek()):
        break

    shark_x, shark_y, dist = fish
    ans += dist
    SPACE[shark_x][shark_y] = 0
    shark_food += 1

    if shark_food >= shark_size:
        shark_size += 1
        shark_food = 0

print(ans)
