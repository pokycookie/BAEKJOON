T = input()
B = list(reversed(input()))
S = []

for c in T:
    S.append(c)
    F = True
    for i, b in enumerate(B):
        try:
            if S[-i - 1] != b:
                F = False
                break
        except:
            F = False
            break
    if F:
        for _ in range(len(B)): S.pop()

if S: print("".join(S))
else: print("FRULA")
