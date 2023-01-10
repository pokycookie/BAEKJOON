N = int(input())

def printStar(N: int):
    if N == 1: return ["*"]

    L = []
    S = printStar(N // 3)

    for s in S:
        L.append(s * 3)
    for s in S:
        L.append(s + " " * (N // 3) + s)
    for s in S:
        L.append(s * 3)

    return L
    
print("\n".join(printStar(N)))
