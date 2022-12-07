import sys
input = sys.stdin.readline

L = []
for _ in range(int(input())):
    F = 0
    for c in input().strip():
        if c == "(": F += 1
        else:
            F -= 1
            if F == 0: break
    L.append("YES" if F == 0 else "NO")

print("\n".join(L))
