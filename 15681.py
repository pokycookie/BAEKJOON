import sys

sys.setrecursionlimit(10**9)
input = sys.stdin.readline

# N: 노드의 수, R: 트리의 루트번호, Q: 쿼리의 수
N, R, Q = map(int, input().split())
# TREE[i]는 노드번호 i와 연결된 노드들을 배열로 나타냄 (직계자식 + 직계부모)
TREE = [[] for _ in range(N + 1)]
# visited[i]는 노드번호 i가 가지는 자식노드들의 수를 나타냄(자신포함)
visited = [0 for _ in range(N + 1)]


def DFS(startNode: int):
    # 도착한 노드의 visited을 1로 함
    # 자식이 정해지지 않은 상태에서 visited[i]는 자기 자신만을 포함하므로 1로 초기화
    visited[startNode] = 1

    # TREE에서 startNode와 연결된 모든 node들에 대해 반복문을 실행
    for node in TREE[startNode]:
        # 만약 node가 이미 방문이력이 있다면, 이는 자신의 부모노드이므로 continue
        if visited[node]:
            continue
        # 자식노드 node에 대해서도 재귀적으로 DFS를 실행
        DFS(node)
        # 가장 아래의 노드는 visited가 1이고, 그 위부터는 자식의 수만큼 visited가 쌓이게 됨
        visited[startNode] += visited[node]


for _ in range(N - 1):
    U, V = map(int, input().split())
    TREE[U].append(V)
    TREE[V].append(U)

# R을 루트로 하여 트리를 구성
DFS(R)

for _ in range(Q):
    U = int(input())
    print(visited[U])
