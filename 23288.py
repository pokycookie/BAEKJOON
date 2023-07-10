import sys
from collections import deque

input = sys.stdin.readline
DELTA = [(0, 1), (0, -1), (1, 0), (-1, 0)]

N, M, K = map(int, input().split())
MAP = [list(map(int, input().split())) for _ in range(N)]
SCORE = [[None for _ in range(M)] for _ in range(N)]

dice = [2, 4, 1, 3, 5, 6]
direction = 1


def roll():
    if direction == 0:
        tmp = dice[0]
        dice[0] = dice[2]
        dice[2] = dice[4]
        dice[4] = dice[5]
        dice[5] = tmp
    elif direction == 1:
        tmp = dice[3]
        dice[3] = dice[2]
        dice[2] = dice[1]
        dice[1] = dice[5]
        dice[5] = tmp
    elif direction == 2:
        tmp = dice[5]
        dice[5] = dice[4]
        dice[4] = dice[2]
        dice[2] = dice[0]
        dice[0] = tmp
    elif direction == 3:
        tmp = dice[1]
        dice[1] = dice[2]
        dice[2] = dice[3]
        dice[3] = dice[5]
        dice[5] = tmp

    return dice[5]


def score(n: int, m: int):
    if SCORE[n][m]:
        return SCORE[n][m]

    cnt = 0
    queue = deque([(n, m)])
    visited = [[False for _ in range(M)] for _ in range(N)]
    while queue:
        cn, cm = queue.popleft()
        if visited[cn][cm]:
            continue
        visited[cn][cm] = True
        cnt += MAP[n][m]
        for dn, dm in DELTA:
            tn, tm = cn + dn, cm + dm
            if not (0 <= tn < N and 0 <= tm < M):
                continue
            if MAP[tn][tm] == MAP[cn][cm]:
                queue.append((tn, tm))

    SCORE[n][m] = cnt
    return SCORE[n][m]


ans = 0
r, c = 0, 0
for _ in range(K):
    if direction == 0:
        if r == 0:
            direction = 2
            r += 1
        else:
            r -= 1
    elif direction == 1:
        if c == M - 1:
            direction = 3
            c -= 1
        else:
            c += 1
    elif direction == 2:
        if r == N - 1:
            direction = 0
            r -= 1
        else:
            r += 1
    elif direction == 3:
        if c == 0:
            direction = 1
            c += 1
        else:
            c -= 1

    A = roll()
    B = MAP[r][c]
    ans += score(r, c)

    if A > B:
        direction = (direction + 1) % 4
    elif A < B:
        direction = (direction + 3) % 4

print(ans)
