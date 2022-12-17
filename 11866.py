N, K = map(int, input().split())
L = [i for i in range(1, N + 1)]

R = []
tmp = 0
for i in range(N):
    index = (K + tmp) % len(L) - 1
    R.append(L[index])
    tmp = index % len(L)
    L.pop(index)

print("<", end="")
END = len(R) - 1
for i, r in enumerate(R):
    if i != END: print(f"{r},", end=" ")
    else: print(r, end="")
print(">")
