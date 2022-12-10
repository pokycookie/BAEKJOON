import sys
input = sys.stdin.readline

N, K = map(int, input().split())
T = list(map(int, input().split()))
L = [0]

for i in T:
    L.append(L[-1] + i)
L.pop(0)

R = []
for i in range(len(L) - K + 1):
    if i - 1 < 0:
        R.append(L[i + K - 1])
    else:
        R.append(L[i + K - 1] - L[i - 1])

print(max(R))
