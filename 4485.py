import sys
import heapq

input = sys.stdin.readline
INF = float("inf")
DELTA = [(1, 0), (-1, 0), (0, 1), (0, -1)]
ANS = []

while True:
    if (N := int(input())) == 0:
        break
    MAP = [list(map(int, input().split())) for _ in range(N)]

    graph = [[] for _ in range(N * N)]
    distances = [INF for _ in range(N * N)]

    # MAP을 하나씩 돌아 상하좌우로 연결된 길을 graph형태로 만들어 냄
    for y in range(N):
        for x in range(N):
            for dy, dx in DELTA:
                cy, cx = y + dy, x + dx
                # MAP영역을 벗어나는 경우 continue
                if not (0 <= cy < N and 0 <= cx < N):
                    continue
                # graph에 (거리, 정점)의 순서로 추가
                # 이때 2차원 배열인 MAP을 1차원 배열의 형태로 변환
                graph[N * y + x].append([MAP[cy][cx], N * cy + cx])

    distances[0] = 0
    queue = []
    # 우선순위 큐에 출발점을 설정
    heapq.heappush(queue, (0, 0))

    while queue:
        # 현재 가장 거리가 짧은 지점을 꺼냄
        distance, vertex = heapq.heappop(queue)
        # 꺼낸 거리가 이미 기록된 거리보다 더 크다면 continue
        # 이는 이미 다른 더 짧은 경로로 방문한 기록이 존재하기 때문
        if distances[vertex] < distance:
            continue
        # 현재 정점에서 이동할 수 있는 모든 정점들에 대해 탐색
        # 다음으로 이동할 수 있는 정점을 next_vertex, 그리고 그 거리를 next_dist라 함
        for next_dist, next_vertex in graph[vertex]:
            # distances[vertex] + next_dist는 해당 vertex를 이용해 next_vertex로 이동하는 새로운 경로
            # 새로운 경로가 기존에 기록된 경로보다 짧은 경로인 경우
            if (dist := distances[vertex] + next_dist) < distances[next_vertex]:
                # 더 짧은 경로로 기록을 갱신
                distances[next_vertex] = dist
                # 새롭게 만들어진 더 짧은 경로를 우선순위 큐에 push
                heapq.heappush(queue, [dist, next_vertex])

    ANS.append(distances[-1] + MAP[0][0])

for i, ans in enumerate(ANS):
    print(f"Problem {i + 1}: {ans}")
