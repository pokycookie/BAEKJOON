import sys
input = sys.stdin.readline

R = 665
for _ in range(int(input())):
    while True:
        R += 1
        if str(R).find("666") != -1: break

print(R)
