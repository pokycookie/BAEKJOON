import sys
input = sys.stdin.readline

N = int(input())
SEQ = list(map(int, input().split()))
OPR = list(map(int, input().split()))

STK = []
ANS = []
STKS = []

def DFS():
    if len(STK) == N - 1:
        STKS.append(list(STK))
        return
    for i in range(4):
        if OPR[i] < 1: continue
        OPR[i] -= 1
        STK.append(i)
        DFS()
        STK.pop()
        OPR[i] += 1

DFS()

for stk in STKS:
    R = SEQ[0]
    index = 1

    for opr in stk:
        if opr == 0: R += SEQ[index]
        elif opr == 1: R -= SEQ[index]
        elif opr == 2: R *= SEQ[index]
        else: R = int(R / SEQ[index])
        index += 1

    ANS.append(R)

print(max(ANS))
print(min(ANS))
