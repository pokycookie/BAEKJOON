from collections import deque
import sys
input = sys.stdin.readline

N, M = map(int, input().split())

GRAPH = {}
visited = {}
R = 0


def setGRAPH(X, Y, X1, Y1):
    if (X1, Y1) in GRAPH:
        GRAPH[(X1, Y1)].append((X, Y))
        GRAPH[(X, Y)].append((X1, Y1))


def DFS(start, cnt):
    if start == (M - 1, N - 1):
        R.append(cnt)
        return
    for vertex in GRAPH[start]:
        if visited[start[0]][start[1]]:
            continue
        visited[start[0]][start[1]] = True
        DFS(vertex, cnt + 1)
        visited[start[0]][start[1]] = False


def BFS(start):
    global R
    queue = deque()
    queue.append((start, 1))
    while queue:
        CQ = queue.popleft()
        CURR = CQ[0]
        if CURR == (M - 1, N - 1):
            R = CQ[1]
            break
        if CURR not in visited:
            visited[CURR] = True
            if CURR not in GRAPH:
                break
            queue.extend(list(map(lambda x: (x, CQ[1] + 1), GRAPH[CURR])))


for Y in range(N):
    for X, value in enumerate(list(map(int, list(input().rstrip())))):
        if value == 1:
            GRAPH[(X, Y)] = []
            setGRAPH(X, Y, X + 1, Y)
            setGRAPH(X, Y, X - 1, Y)
            setGRAPH(X, Y, X, Y + 1)
            setGRAPH(X, Y, X, Y - 1)

BFS((0, 0))
print(R)
