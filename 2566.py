L = []
for _ in range(9):
    L.append(list(map(int, input().split())))

M = 0
C = 1
R = 1
for column in range(9):
    for row in range(9):
        T = L[column][row]
        if T > M:
            M = T
            C = column + 1
            R = row + 1

print(M)
print(f"{C} {R}")
