import sys
input = sys.stdin.readline

L = set()
for _ in range(int(input())):
    L.add(input().strip())

L = list(L)
L.sort()
L.sort(key=len)

print("\n".join(L))
