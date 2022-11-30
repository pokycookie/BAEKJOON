import math

N = int(input())
R = []

if N % 5 == 0: R.append(int(N / 5))
else: R.append(-1)

if N % 3 == 0: R.append(int(N / 3))
else: R.append(-1)

T5 = int(math.floor(N / 5))
while True:
    TN = N - T5 * 5
    if TN % 3 == 0:
        R.append(int(TN / 3) + T5)
        break
    T5 -= 1
    if T5 == -1:
        R.append(-1)
        break

if R[0] == -1 and R[1] == -1 and R[2] == -1: print(-1)
else:
    for i in range(R.count(-1)): R.remove(-1)
    print(min(R))
