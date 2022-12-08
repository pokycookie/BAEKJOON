L = []

while True:
    I = input()
    if I == ".": break
    L.append(I)

R = []
for s in L:
    S = []
    flag = "yes"
    for c in s:
        if c == "(" or c == "[": S.append(c)
        elif c == ")":
            if len(S) > 0 and S[-1] == "(": S.pop()
            else: flag = "no"; break
        elif c == "]":
            if len(S) > 0 and S[-1] == "[": S.pop()
            else: flag = "no"; break
    if len(S) > 0: flag = "no"
    R.append(flag)

print("\n".join(R))
