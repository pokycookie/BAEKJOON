import sys
input = sys.stdin.readline

WL, W = int(input()), list(map(int, input().split()))
BL, B = int(input()), list(map(int, input().split()))

# DP[i][j]: i번째 추까지 사용했을 때 j무게를 만들 수 있는지
DP = [[False for _ in range((WL + 1) * 500 + 1)] for _ in range(WL + 1)]

def setDP(i: int, j: int):
    if i > WL: return
    if DP[i][j]: return

    DP[i][j] = True
    
    setDP(i + 1, j)
    setDP(i + 1, j + W[i - 1])
    setDP(i + 1, abs(j - W[i - 1]))

setDP(0, 0)

ANS = []
for bead in B:
    if bead > 500 * WL or not DP[WL][bead]:
        ANS.append("N")
    else:
        ANS.append("Y")

print(*ANS)
