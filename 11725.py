from collections import deque
import sys
input = sys.stdin.readline

N = int(input())
T = [[] for _ in range(N + 1)]
V = [False] * (N + 1)
P = [0] * (N + 1)

for _ in range(N - 1):
    A, B = map(int, input().split())
    T[A].append(B)
    T[B].append(A)

def DFS(s):
    V[s] = True
    S = deque([s])

    while S:
        s = S.pop()
        for i in T[s]:
            if not V[i]:
                S.append(i)
                V[i] = True
                P[i] = s

DFS(1)

for i in range(N - 1):
    print(P[2 + i])
