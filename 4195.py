import sys
input = sys.stdin.readline

def findParent(vertex: int):
    if parent[vertex] != vertex:
        parent[vertex] = findParent(parent[vertex])
    return parent[vertex]

def unionParent(v1: int, v2: int):
    v1 = findParent(v1)
    v2 = findParent(v2)
    
    if v1 != v2:
        parent[v2] = v1
        number[v1] += number[v2]

ANS = []
for _ in range(int(input())):
    F = int(input())

    parent = {}
    number = {}

    for _ in range(F):
        v1, v2 = input().rstrip().split()

        if v1 not in parent:
            parent[v1] = v1
            number[v1] = 1
        
        if v2 not in parent:
            parent[v2] = v2
            number[v2] = 1

        unionParent(v1, v2)
        ANS.append(str(number[findParent(v1)]))

print("\n".join(ANS))
