import sys
input = sys.stdin.readline

N = int(input())
L = [list(map(int, input().split())) for _ in range(N)]

def checkSquare(x: int, y: int, n: int):
    color = L[y][x]
    flag = True

    for iy in range(y, y + n):
        for ix in range(x, x + n):
            if L[iy][ix] != color:
                flag = False
                break
        if not flag: break
    
    return (flag, color)

def square(x: int, y: int, n: int):
    white, black = (0, 0)
    flag, color = checkSquare(x, y, n)

    if n == 1:
        if L[y][x] == 0: white += 1
        else: black += 1
        return (white, black)

    if flag:
        if color == 0: white += 1
        else: black += 1
        return (white, black)
    else:
        tn = n // 2
        for tx, ty in [(x, y), (x + tn, y), (x, y + tn), (x + tn, y + tn)]:
            tw, tb = square(tx, ty, tn)
            white += tw
            black += tb
        return (white, black)

print("\n".join(map(str, square(0, 0, N))))
