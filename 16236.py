import sys
from collections import deque

input = sys.stdin.readline

N = int(input())
SPACE = [list(map(int, input().split())) for _ in range(N)]
DELTA = [(1, 0), (-1, 0), (0, 1), (0, -1)]
INF = float("inf")

shark_pos = None
shark_size = 2
shark_food = 0
ans = 0

for y in range(N):
    for x in range(N):
        if SPACE[y][x] == 9:
            SPACE[y][x] = 0
            shark_pos = [y, x]
            break
    if shark_pos:
        break


def BFS():
    y, x = shark_pos if shark_pos else [0, 0]

    queue = deque([(y, x)])
    DIST = [[-1 for _ in range(N)] for _ in range(N)]
    DIST[y][x] = 0

    while queue:
        cy, cx = queue.popleft()

        for dy, dx in DELTA:
            currY, currX = cy + dy, cx + dx

            if 0 <= currX < N and 0 <= currY < N:
                if shark_size >= SPACE[currY][currX] and DIST[currY][currX] == -1:
                    DIST[currY][currX] = DIST[cy][cx] + 1
                    queue.append((currY, currX))

            # # SPACE범위를 벗어나는 경우
            # if not (0 <= currX < N and 0 <= currY < N):
            #     continue
            # # 먹이의 크기가 더 큰 경우
            # if shark_size < SPACE[currY][currX]:
            #     continue
            # # 이미 방문한 경우
            # if DIST[currY][currX] != -1:
            #     continue

            # # 방문한 위치까지의 최단거리를 저장 (BFS이므로 자연스럽게 최단거리)
            # DIST[currY][currX] = DIST[cy][cx] + 1
            # queue.append((currY, currX))

    return DIST


def seek():
    pos = [0, 0]
    dist = INF
    DIST = BFS()
    print(DIST)

    for y in range(N):
        for x in range(N):
            if DIST[y][x] != -1 and 1 <= SPACE[y][x] < shark_size:
                if DIST[y][x] < dist:
                    dist = DIST[y][x]
                    pos = [y, x]

            # # BFS로 방문할 수 없는 곳인 경우
            # if DIST[y][x] == -1:
            #     continue
            # # 빈 칸인 경우
            # if SPACE[y][x] == 0:
            #     continue
            # # 아기 상어가 먹을 수 없는 경우
            # if SPACE[y][x] >= shark_size:
            #     continue
            # # 최소거리를 계속하여 갱신
            # if DIST[y][x] < dist:
            #     dist = DIST[y][x]
            #     pos = [y, x]

    # 먹을 물고기가 존재하지 않는 경우
    if dist == INF:
        return None

    return dist, pos[0], pos[1]


while True:
    # 먹을 물고기가 존재하지 않는 경우
    if not (fish := seek()):
        break

    dist, currY, currX = fish
    ans += dist
    SPACE[currY][currX] = 0
    shark_food += 1

    if shark_food >= shark_size:
        shark_size += 1
        shark_food = 0

print(ans)
