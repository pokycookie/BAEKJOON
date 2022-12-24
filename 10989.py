import sys
input = sys.stdin.readline

L = [0] * 10000
for _ in range(int(input())):
    L[int(input()) - 1] += 1

for i in range(10000):
    if L[i] != 0:
        for j in range(L[i]): print(i + 1)
