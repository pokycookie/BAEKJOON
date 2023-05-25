class Node:
    def __init__(self, value: int):
        self.value = value
        self.next = None

    def setNext(self, node):
        self.next = node


N, K = map(int, input().split())

HEAD = None
prev = None
for i in range(N):
    newNode = Node(i + 1)
    if prev:
        prev.setNext(newNode)
    if i == 0:
        HEAD = newNode
    if i == N - 1:
        newNode.setNext(HEAD)
    prev = newNode

ANS = []
if K == 1:
    ANS = [i + 1 for i in range(N)]
else:
    for _ in range(N):
        for cnt in range(K - 2):
            HEAD = HEAD.next
        ANS.append(HEAD.next.value)
        HEAD.setNext(HEAD.next.next)
        HEAD = HEAD.next

print("<" + ", ".join(map(str, ANS)) + ">")
