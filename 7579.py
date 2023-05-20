import sys

input = sys.stdin.readline

# N: 앱의 수, M: 필요한 메모리
N, M = map(int, input().split())
# 메모리 리스트
ML = [0] + list(map(int, input().split()))
# 비용 리스트
CL = [0] + list(map(int, input().split()))

# i까지의 앱 중 j만큼의 비용으로 얻을 수 있는 최대 메모리 (최대 비용은 모든 CL의 합)
DP = [[0 for _ in range(sum(CL) + 1)] for _ in range(N + 1)]
ANS = sum(CL)

for i in range(1, N + 1):
    # i번째 앱까지 사용
    for j in range(1, sum(CL) + 1):
        # j만큼의 비용을 사용한다고 가정

        if CL[i] > j:
            # i번째 앱의 비용이 j를 넘어가므로 앱을 종료할 수 없음
            DP[i][j] = DP[i - 1][j]
        else:
            # DP[i][j]는 두가지 경우 중 하나임 (둘 중 메모리가 더 많이 확보되는 쪽을 사용)
            # 1. i번째 메모리를 확보하지 않는 것이 더 유리한 경우 => DP[i - 1][j]
            # 2. i번째 메모리를 확보하는 것이 더 유리한 경우 => DP[i - 1][j - CL[i]] + ML[i]
            DP[i][j] = max(DP[i - 1][j], DP[i - 1][j - CL[i]] + ML[i])

        if DP[i][j] >= M:
            # i번째 앱 중 j만큼의 비용으로 얻을 수 있는 최대 메모리가 필요한 메모리 M보다 큰 경우
            # 즉, 충분한 메모리를 확보했다면 ANS와 비교하여 더 낮은 비용을 선택 (j가 비용이므로)
            ANS = min(ANS, j)

print(ANS)
