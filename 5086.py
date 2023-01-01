import sys
input = sys.stdin.readline

R = []

while True:
    A, B = map(int, input().split())
    if A == 0 and B == 0:
        break

    if B % A == 0:
        R.append("factor")
    elif A % B == 0:
        R.append("multiple")
    else:
        R.append("neither")

print("\n".join(R))
