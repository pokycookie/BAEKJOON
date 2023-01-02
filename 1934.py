import sys
input = sys.stdin.readline

def getLCM(n1: int, n2: int):
    A = n1 if n1 >= n2 else n2
    B = n2 if n1 >= n2 else n1
    R = B
    while True:
        r = A % B
        A = B
        B = r
        if r == 0: return n1 * n2 // R
        R = r

R = []
for _ in range(int(input())):
    n1, n2 = map(int, input().split())
    R.append(getLCM(n1, n2))

for r in R: print(r)
