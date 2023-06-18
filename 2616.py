import sys

input = sys.stdin.readline

# N: 원래 객차의 수
N = int(input())
# P: 객차 손님의 수 리스트
P = [0] + list(map(int, input().split()))
# K: 소형 기관차가 최대로 끌 수 있는 객차의 수
K = int(input())

# DP[i][j]: 소형 기관차가 i개 있고, j번째 객차까지 사용했을 때의 최대 승객
DP = [[0 for _ in range(N + 1)] for _ in range(4)]
# 누적합
acc = 0

# K번째 객차까지의 누적합을 구함
for i in range(1, K + 1):
    acc += P[i]
# K번째 객차까지만 사용한다면 소형기관차는 1개 사용할 수 있음
# 1개의 소형기관차를 사용하여 K개의 객차를 끈다면 승객수는 지금까지의 누적합
DP[1][K] = acc

# K + 1번째 객차까지 사용하는 순간부터는 소형기관차를 여러 개 사용할 수 있음
for i in range(K + 1, N + 1):
    # 기존 누적합에 새로운 객차의 손님 수만큼 더하고, 처음 객차의 손님 수만큼 뺌
    acc += P[i] - P[i - K]
    # 1개의 소형기관차만 이용한다면, 이전의 DP값과 비교하여 큰 값을 사용
    DP[1][i] = max(DP[1][i - 1], acc)

    # 객차를 K * 2개 이상으로 사용하기 시작하면 소형기관차를 2개 사용할 수 있음
    if i >= K * 2:
        DP[2][i] = max(DP[2][i - 1], DP[1][i - K] + acc)

    # 객차를 K * 3개 이상으로 사용하기 시작하면 소형기관차를 3개 사용할 수 있음
    if i >= K * 3:
        DP[3][i] = max(DP[3][i - 1], DP[2][i - K] + acc)

print(DP[3][N])
