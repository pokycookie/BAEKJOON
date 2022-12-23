from collections import deque
import sys
input = sys.stdin.readline

N = int(input())
L = deque()

while True:
    I = int(input())
    if I == 0 and L:
        L.popleft()
    elif I == -1:
        break
    elif len(L) < N:
        L.append(I)

if not L:
    print("empty")
else:
    print(*L)
