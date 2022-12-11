import sys
input = sys.stdin.readline

class Node:
    def __init__(self, value, next) -> None:
        self.value = value
        self.next = next


class Queue:
    def __init__(self) -> None:
        self.HEAD = None
        self.TAIL = None
        self.SIZE = 0

    def push(self, value: int) -> None:
        if self.SIZE == 0:
            tmp = Node(value, None)
            self.HEAD = tmp
            self.TAIL = tmp
        else:
            tmp = Node(value, None)
            self.TAIL.next = tmp
            self.TAIL = tmp

        self.SIZE += 1

    def pop(self) -> int:
        if self.SIZE == 0:
            return -1
        elif self.SIZE == 1:
            result = self.HEAD.value
            self.HEAD = None
            self.TAIL = None
            self.SIZE = 0
            return result
        else:
            result = self.HEAD.value
            self.HEAD = self.HEAD.next
            self.SIZE -= 1
            return result

    def empty(self) -> int:
        if self.SIZE == 0:
            return 1
        else:
            return 0

    def front(self) -> int:
        if self.SIZE == 0:
            return -1
        else:
            return self.HEAD.value

    def back(self) -> int:
        if self.SIZE == 0:
            return -1
        else:
            return self.TAIL.value


Q = Queue()

for _ in range(int(input())):
    I = input().strip()
    if I == "pop":
        print(Q.pop())
    elif I == "size":
        print(Q.SIZE)
    elif I == "empty":
        print(Q.empty())
    elif I == "front":
        print(Q.front())
    elif I == "back":
        print(Q.back())
    else:
        X = I.split()[-1]
        Q.push(int(X))
