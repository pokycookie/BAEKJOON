import sys
input = sys.stdin.readline

N, M = map(int, input().split())

NL = set()
ML = []
for _ in range(N):
    NL.add(input().rstrip())
for _ in range(M):
    ML.append(input().rstrip())

S = 0
for m in ML:
    if m in NL:
        S += 1


print(S)
