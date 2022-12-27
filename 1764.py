import sys
input = sys.stdin.readline

N, M = map(int, input().split())
NL = set()
ML = set()

for _ in range(N): NL.add(input().strip())
for _ in range(M): ML.add(input().strip())

R = sorted(list(NL & ML))
print(len(R))
for r in R:
    print(r)
