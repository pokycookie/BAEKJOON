from collections import deque
import sys
input = sys.stdin.readline

R = []
for _ in range(int(input())):
    REV = False
    ERR = False

    F = input().strip()
    N = input()

    L = list(input().replace(
        "[", "").replace("]", "").strip().split(","))
    if L == [""]:
        L = []

    D = deque(L)

    for f in F:
        if f == "R":
            REV = True if REV == False else False
        else:
            if D:
                if REV:
                    D.pop()
                else:
                    D.popleft()
            else:
                ERR = True
                break
    if D:
        if REV:
            D.reverse()
        R.append(list(D))
    else:
        if ERR:
            R.append("error")
        else:
            R.append([])

for r in R:
    if r == "error":
        print("error")
    else:
        print("[", end="")
        print(",".join(r), end="")
        print("]")
