N = []
while True:
    I = int(input())
    if I == 0: break
    N.append(I)

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

for i in N:
    print(len(getPrime(i + 1, (i * 2) + 1)))
