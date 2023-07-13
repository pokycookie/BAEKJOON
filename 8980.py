import sys

input = sys.stdin.readline

N, C = map(int, input().split())
M = int(input())

L = [tuple(map(int, input().split())) for _ in range(M)]
L.sort(key=lambda x: x[1])

ans = 0
capa = [C for _ in range(N)]
for _from, _to, box in L:
    _min = C
    for i in range(_from, _to):
        _min = min(capa[i], box, _min)
    for i in range(_from, _to):
        capa[i] -= _min
    ans += _min

print(ans)
