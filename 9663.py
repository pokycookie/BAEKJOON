N = int(input())

R = 0
V = [0 for _ in range(N)]


def checkQueen(x: int):
    for i in range(x):
        if V[x] == V[i] or abs(V[x] - V[i]) == abs(x - i):
            return False
    return True


def DFS(row: int):
    global R

    if row == N:
        R += 1
        return
    for column in range(N):
        V[row] = column
        if checkQueen(row):
            DFS(row + 1)


DFS(0)
print(R)
