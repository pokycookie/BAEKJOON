import sys
input = sys.stdin.readline

N, M, V = map(int, input().split())

GRAPH = {}

for _ in range(M):
    A, B = map(int, input().split())

    if A in GRAPH:
        GRAPH[A].append(B)
    else:
        GRAPH[A] = [B]
    
    if B in GRAPH:
        GRAPH[B].append(A)
    else:
        GRAPH[B] = [A]

for vertex in GRAPH:
    GRAPH[vertex].sort(reverse=True)

R = [0 for _ in range(N)]

def DFS(start):
    count = 1
    stack = [start]
    visited = [False for _ in range(N + 1)]
    while stack:
        CURR = stack.pop()
        if not visited[CURR]:
            visited[CURR] = True
            R[CURR - 1] = count
            count += 1
            if CURR not in GRAPH: break
            stack.extend(GRAPH[CURR])
    return R

DFS(V)
print("\n".join(map(str, R)))
