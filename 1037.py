import sys
input = sys.stdin.readline

input()
L = sorted(list(map(int, input().split())))

print(L[0] * L[-1])
