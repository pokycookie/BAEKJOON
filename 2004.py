N, M = map(int, input().split())

def facZero(n, k):
    R = 0
    i = 1
    while True:
        if k ** i > n: break
        R += n // (k ** i)
        i += 1
    return R

def C(n, r):
    A = facZero(n, 5) - (facZero(n - r, 5) + facZero(r, 5))
    B = facZero(n, 2) - (facZero(n - r, 2) + facZero(r, 2))
    return min(A, B)

print(C(N, M))
