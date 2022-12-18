import sys
input = sys.stdin.readline

Q = []
R = []

for _ in range(int(input())):
    N, M = map(int, input().split())
    Q = list(map(int, input().split()))
    QS = sorted(Q, reverse=True)
    T = Q[M]
    Q[M] = -1
    result = 1

    while True:
        if Q[0] == -1 and max(QS) == T:
            R.append(result)
            break
        elif Q[0] == QS[0]:
            QS.pop(0)
            Q.pop(0)
            result += 1
        else:
            tmp = Q.pop(0)
            Q.append(tmp)

for r in R: print(r)
