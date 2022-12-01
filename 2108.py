import sys
input = sys.stdin.readline

T = int(input())

L = []
M = [0 for _ in range(8001)]

for _ in range(T):
    N = int(input())
    M[N] += 1
    L.append(N)

L.sort()

TML = [i - 8001 if i > 4000 else i for i, e in enumerate(M) if e == max(M)]
TML.sort()

mean = sum(L) / len(L)
median = L[len(L) // 2]
mode = 0
ran = L[-1] - L[0]


print(round(mean))
print(median)
print(TML[1] if len(TML) > 1 else TML[0])
print(ran)
