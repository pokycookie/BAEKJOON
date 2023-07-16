import sys

input = sys.stdin.readline

R, C = map(int, input().split())
MAP = [list(input().rstrip()) for _ in range(R)]

ans = 0

for i in range(R):
    stack = [(i, 0)]

    while stack:
        y, x = stack.pop()
        MAP[y][x] = "x"

        if x == C - 1:
            ans += 1
            break

        for dy in [1, 0, -1]:
            cy, cx = y + dy, x + 1
            if not (0 <= cy < R and 0 <= cx < C):
                continue
            if MAP[cy][cx] == "x":
                continue
            stack.append((cy, cx))

print(ans)
