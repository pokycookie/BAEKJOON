import heapq
import sys
input = sys.stdin.readline

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

def distance(x1: int, y1: int, x2: int, y2: int):
    return (((x1 - x2) ** 2) + ((y1 - y2) ** 2)) ** 0.5

N, M = map(int, input().split())
parent = [i for i in range(N + 1)]
stars = [0]
HEAP = []

for i in range(N):
    x, y = map(int, input().split())
    stars.append((x, y))

for _ in range(M):
    v1, v2 = map(int, input().split())
    isCycle(v1, v2)

for i in range(1, N):
    x, y = stars[i]
    for j in range(i + 1, N + 1):
        tx, ty = stars[j]
        w = distance(x, y, tx, ty)
        heapq.heappush(HEAP, (w, i, j))

ANS = 0
for _ in range(len(HEAP)):
    w, v1, v2 = heapq.heappop(HEAP)
    if not isCycle(v1, v2):
        ANS += w

print(f"{round(ANS, 2):.2f}")
