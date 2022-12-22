import sys
input = sys.stdin.readline

L = []
for _ in range(int(input())):
    x, y = map(int, input().split())
    L.append((x, y))

R = []
for t in L:
    k = 1
    for e in L:
        if e[0] > t[0] and e[1] > t[1]: k += 1
    R.append(str(k))

print(" ".join(R))
