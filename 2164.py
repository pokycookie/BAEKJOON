L = [i for i in range(1, int(input()) + 1)]

TL = []
pop = True
while True:
    if len(L) == 1: break
    for i in range(len(L)):
        if pop:
            pop = False
            continue
        else:
            pop = True
            TL.append(L[i])
    L = TL
    TL = []

print(L[0])
