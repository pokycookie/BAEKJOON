import sys
input = sys.stdin.readline
from collections import deque

L = deque()

for _ in range(int(input())):
    I = input().rstrip().split()
    C = I[0]
    
    if len(I) == 2:
        if C == "push_back":
            L.append(I[1])
        elif C == "push_front":
            L.appendleft(I[1])
    else:
        if C == "pop_front":
            if not L: print(-1)
            else: print(L.popleft())
        elif C == "pop_back":
            if not L: print(-1)
            else: print(L.pop())
        elif C == "size":
            print(len(L))
        elif C == "empty":
            print(1 if not L else 0)
        elif C == "front":
            if not L: print(-1)
            else: print(L[0])
        elif C == "back":
            if not L: print(-1)
            else: print(L[-1])
