import sys
input = sys.stdin.readline

L = []

for _ in range(int(input())):
    I = input().strip()
    if I == "pop":
        if len(L) > 0: print(L.pop())
        else: print(-1)
    elif I == "size":
        print(len(L))
    elif I == "empty":
        if len(L) > 0: print(0)
        else: print(1)
    elif I == "top":
        if len(L) > 0: print(L[-1])
        else: print(-1)
    else:
        X = I.split()[-1]
        L.append(X)
