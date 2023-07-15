# use Union-Find
# path compression

import sys

input = sys.stdin.readline
sys.setrecursionlimit(10**9)

G = int(input())
P = int(input())

ans = 0
gates = [i for i in range(G + 1)]


def find(i: int):
    if gates[i] == i:
        return i

    gates[i] = find(gates[i])
    return gates[i]


for _ in range(P):
    if (gate := find(int(input()))) == 0:
        break
    gates[find(gate)] = find(gate - 1)
    ans += 1

print(ans)
