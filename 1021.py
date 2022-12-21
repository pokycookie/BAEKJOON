from collections import deque
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
L = list(map(int, input().split()))
Q = deque([i for i in range(1, N + 1)])

R = 0

for i in L:
    index = Q.index(i)
    MID = len(Q) / 2
    r = 0

    if index != 0:
        if index > MID:
            r = len(Q) - index
            for _ in range(r):
                tmp = Q.pop()
                Q.appendleft(tmp)
        else:
            r = index
            for _ in range(r):
                tmp = Q.popleft()
                Q.append(tmp)

    Q.popleft()
    R += r

print(R)
