import sys
input = sys.stdin.readline

def getPrime(start: int, end: int):
    L = [i for i in range(end)]
    R = []

    for i in range(end):
        if L[i] < 2: L[i] = 0
        if L[i] == 0: continue

        for j in range(i * 2, end, i):
            L[j] = 0

    for e in L:
        if e != 0 and e >= start: R.append(e)

    return R

N = []
P = getPrime(2, 10001)[::-1]

for _ in range(int(input())):
    N.append(int(input()))

for i in N:
    for prime in P:
        if prime > (i / 2): continue
        if i - prime in P:
            print(f"{prime} {i - prime}")
            break
