import sys
input = sys.stdin.readline

S = input().rstrip()
D = {}

def cs(c: str, S: str):
    L = []
    T = 0
    for s in S:
        if s == c: T += 1
        L.append(T)
    D[c] = L

R = []
for _ in range(int(input())):
    I = input().rstrip().split()
    a = I[0]
    l = int(I[1])
    r = int(I[2])
    
    if a not in D: cs(a, S)
    if l == 0: R.append(D[a][r] - 0)
    else: R.append(D[a][r] - D[a][l - 1])

print("\n".join(map(str, R)))
