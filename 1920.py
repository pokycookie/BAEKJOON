import sys
input = sys.stdin.readline

int(input())
N = sorted(list(map(int, input().split())))
int(input())
M = list(map(int, input().split()))

def binarySearch(l: list, e: int):
    start = 0
    end = len(l) - 1

    while start <= end:
        mid = (start + end) // 2
        if l[mid] == e: return True
        elif l[mid] < e: start = mid + 1
        else: end = mid - 1

    return False

for i in M:
    if binarySearch(N, i): print(1)
    else: print(0)
