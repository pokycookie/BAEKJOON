import sys
input = sys.stdin.readline

N = int(input())
L = []

MW = 0
MH = 0
index = 0

TYPE = [0, 0, 0, 0]

for _ in range(6):
    I = tuple(map(int, input().split()))
    L.append(I)
    TYPE[I[0] - 1] += 1

    if I[0] == 3 or I[0] == 4:
        if I[1] > MH:
            MH = I[1]
            index = len(L) - 1
    else:
        if I[1] > MW:
            MW = I[1]


L = L[index:] + L[:index]

W = 0
H = 0

if TYPE == [2, 1, 2, 1] or TYPE == [1, 2, 1, 2]:
    W = L[3][1]
    H = L[4][1]
elif TYPE == [2, 1, 1, 2] or TYPE == [1, 2, 2, 1]:
    W = L[3][1]
    H = L[2][1]

print(((MW * MH) - (W * H)) * N)
