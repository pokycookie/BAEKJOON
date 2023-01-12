from collections import deque
import sys
input = sys.stdin.readline

N = int(input())
T = [[] for _ in range(N + 1)] # T[i] = [...i의 자식들]
V = [False] * (N + 1) # 해당 노드를 방문했는지
P = [0] * (N + 1) # 부모를 빠르게 찾기 위한 결과리스트

for _ in range(N - 1):
    A, B = map(int, input().split())
    T[A].append(B) # A의 자식으로 B를 추가
    T[B].append(A) # B의 자식으로 A를 추가

def DFS(s):
    V[s] = True
    S = deque([s]) # DFS를 위한 스택

    # 스택이 존재하는 한 계속 반복
    while S:
        s = S.pop()
        # T[s]의 자식들을 i로 하여 반복
        for i in T[s]:
            # T[s]의 자식인 i를 방문한 적 없다면
            if not V[i]:
                S.append(i)     # 스택에 i를 추가
                V[i] = True     # 방문했음을 표시
                P[i] = s        # 부모리스트에 i의 부모는 s임을 알림

DFS(1)

for i in range(N - 1):
    print(P[2 + i])
