# use Kruskal Algorithm

import sys
import heapq

input = sys.stdin.readline

N = int(input())
M = int(input())

edges = []
parents = [i for i in range(N + 1)]
ans = 0


def getParent(v: int):
    if parents[v] == v:
        return v
    parents[v] = getParent(parents[v])
    return parents[v]


def union(v1: int, v2: int):
    v1 = getParent(v1)
    v2 = getParent(v2)

    if v1 < v2:
        parents[v2] = v1
    else:
        parents[v1] = v2


def isCycle(v1: int, v2: int):
    if getParent(v1) == getParent(v2):
        return True
    else:
        union(v1, v2)
        return False


for _ in range(M):
    a, b, weight = map(int, input().split())
    heapq.heappush(edges, (weight, a, b))

while edges:
    weight, a, b = heapq.heappop(edges)
    if not isCycle(a, b):
        ans += weight

print(ans)
