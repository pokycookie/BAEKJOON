# ref: https://aerocode.net/392

import sys

input = sys.stdin.readline

N = int(input())
W = list(sorted(map(int, input().split())))

end = 1
for w in W:
    if end < w:
        break
    end += w

print(end)
