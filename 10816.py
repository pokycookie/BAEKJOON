import sys
input = sys.stdin.readline

N = int(input())
LN = list(map(int, input().split()))
M = int(input())
LM = list(map(int, input().split()))

D = {}
for e in LN:
    if e in D: D[e] += 1
    else: D[e] = 1

for e in LM:
    if e in D: print(D[e], end=" ")
    else: print(0, end=" ")
