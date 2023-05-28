import sys

input = sys.stdin.readline

DP = [0, 1, 2, 4]
ANS = []

for _ in range(int(input())):
    n = int(input())

    if len(DP) - 1 >= n:
        ANS.append(DP[n])
        continue

    for i in range(len(DP), n + 1):
        DP.append(DP[i - 1] + DP[i - 2] + DP[i - 3])

    ANS.append(DP[n])

print("\n".join(map(str, ANS)))
