import sys
input = sys.stdin.readline

N = int(input())
M = int(input())

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

def DFS(start):
    stack = [start]
    visited = []
    while stack:
        CURR = stack.pop()
        if CURR not in visited:
            visited.append(CURR)
            if CURR not in GRAPH: break
            for vertex in sorted(GRAPH[CURR], reverse=True):
                stack.append(vertex)
    return visited

print(len(DFS(1)) - 1)
