import sys

input = sys.stdin.readline

N, K = map(int, input().split())
L = list(map(int, input().split()))

sockets = set()

if N >= K:
    print(0)
    exit()


def find(start: int):
    res = 0
    max_idx = -1
    for elec in sockets:
        try:
            idx = L[start:].index(elec)
        except:
            idx = K
        if max_idx < idx:
            res, max_idx = elec, idx

    return res


ans = 0
for i, elec in enumerate(L):
    sockets.add(elec)
    if len(sockets) > N:
        ans += 1
        sockets.discard(find(i))

print(ans)
