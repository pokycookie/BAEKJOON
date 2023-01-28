import sys
input = sys.stdin.readline

L = []
D = {}
while True:
    a, b, c = map(int, input().split())
    if a == -1 and b == -1 and c == -1: break
    L.append([a, b, c])

def w(a: int, b: int, c: int):
    key = (a, b, c)
    if a <= 0 or b <= 0 or c <= 0: return 1
    if a > 20 or b > 20 or c > 20: return w(20, 20, 20)
    if key in D: return D[key]

    if a < b < c:
        D[key] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c)
        return D[key]

    D[key] = w(a - 1, b ,c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1)
    return D[key]

for l in L:
    print(f"w({l[0]}, {l[1]}, {l[2]}) = {w(l[0], l[1], l[2])}")
