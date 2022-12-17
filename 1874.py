import sys
input = sys.stdin.readline

L = []
for _ in range(int(input())): L.append(int(input()))

S = []
R = []
s = 1

for l in L:
    if not S:
        S.append(s)
        s += 1
        R.append("+")

    if l == S[-1]:
        S.pop()
        R.append("-")
    elif l < S[-1]:
        R = ["NO"]
        break
    else:
        while l != S[-1]:
            S.append(s)
            s += 1
            R.append("+")
        S.pop()
        R.append("-")

for i in R:
    print(i)
