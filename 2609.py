N1, N2 = map(int, input().split())

def getGCD(n1: int, n2: int):
    A = n1 if n1 >= n2 else n2
    B = n2 if n1 >= n2 else n1
    R = B
    while True:
        r = A % B
        A = B
        B = r
        if r == 0: return R
        R = r

GCD = getGCD(N1, N2)
LCM = N1 * N2 // GCD

print(GCD)
print(LCM)
