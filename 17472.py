import heapq
from collections import deque
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
world = [list(map(int, input().split())) for _ in range(N)]
islands = []
bridge = []

def findParent(vertex: int):
    if parent[vertex] != vertex:
        parent[vertex] = findParent(parent[vertex])
    return parent[vertex]

def unionParent(v1: int, v2: int):
    v1 = findParent(v1)
    v2 = findParent(v2)
    if v1 < v2:
        parent[v2] = v1
    else:
        parent[v1] = v2

def isCycle(v1: int, v2: int):
    if findParent(v1) == findParent(v2):
        return True        
    else:
        unionParent(v1, v2)
        return False

island = []
visited = {}

for row in range(N):
    for column in range(M):
        curr = world[row][column]
        if curr == 1:
            island.append((row, column))

def findIsland(y: int, x: int):
    queue = deque([(y, x)])
    result = []
    while queue:
        cy, cx = queue.popleft()
        if (cy, cx) in visited:
            continue
        visited[(cy, cx)] = True
        result.append((cy, cx))
        for ty, tx in [(cy, cx - 1), (cy, cx + 1), (cy + 1, cx), (cy - 1, cx)]:
            if tx < 0 or tx >= M:
                continue
            if ty < 0 or ty >= N:
                continue
            if world[ty][tx] == 1:
                queue.append((ty, tx))
    
    return result

for y, x in island:
    if (y, x) in visited:
        continue
    islands.append(findIsland(y, x))

def getIsland(y: int, x: int):
    for i, sub in enumerate(islands):
        if (y, x) in sub:
            return i

for row in range(N):
    island = []

    for column in range(M):
        curr = world[row][column]
        if curr == 1:
            island.append(column)
    
    for i in range(len(island) - 1):
        if island[i + 1] > island[i] + 2:
            distance = island[i + 1] - island[i] - 1
            start = getIsland(row, island[i])
            end = getIsland(row, island[i + 1])
            heapq.heappush(bridge, (distance, start, end))

for column in range(M):
    island = []

    for row in range(N):
        curr = world[row][column]
        if curr == 1:
            island.append(row)

    for i in range(len(island) - 1):
        if island[i + 1] > island[i] + 2:
            distance = island[i + 1] - island[i] - 1
            start = getIsland(island[i], column)
            end = getIsland(island[i + 1], column)
            heapq.heappush(bridge, (distance, start, end))

length = len(bridge)
parent = [i for i in range(len(islands))]
ANS = 0

graph = [[] for _ in range(len(islands))]
for w, v1, v2 in bridge:
    graph[v1].append(v2)
    graph[v2].append(v1)
    
Q = deque([0])
V = [False for _ in range(len(islands))]
S = 0
while Q:
    curr = Q.popleft()
    if V[curr]:
        continue
    V[curr] = True
    S += 1
    Q.extend(graph[curr])

if S == len(islands):
    for _ in range(len(bridge)):
        w, v1, v2 = heapq.heappop(bridge)
        if not isCycle(v1, v2):
            ANS += w
    print(ANS)
else:
    print(-1)
