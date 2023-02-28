from collections import deque
import sys
input = sys.stdin.readline

M, N = map(int, input().split())

BOARD = []
START = []

for y in range(N):
    L = list(map(int, input().split()))
    BOARD.append(L)

    for x, e in enumerate(L):
        if e == 1: START.append((x, y, 0))

def LRTB(x, y):
    R = []
    for dx, dy in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
        tx, ty = (x + dx, y + dy)
        if 0 <= tx < M and 0 <= ty < N:
            R.append((tx, ty))
    return R

def BFS():
    QUEUE = deque(START)
    COUNT = 0

    while QUEUE:
        x, y, LV = QUEUE.popleft()
        if LV > COUNT: COUNT = LV
        for X, Y in LRTB(x, y):
            if BOARD[Y][X] == 0:
                QUEUE.append((X, Y, LV + 1))
                BOARD[Y][X] = 1

    if 0 in [j for i in BOARD for j in i]: COUNT = -1
    return COUNT

print(BFS())
