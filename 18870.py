import sys
input = sys.stdin.readline

N = int(input())
L = list(map(int, input().split()))

def binarySearch(target: int, l: list):
    start = 0
    end = len(l) - 1

    while start <= end:
        mid = (start + end) // 2
        if l[mid] == target: return mid
        elif l[mid] < target: start = mid + 1
        else: end = mid - 1
    
    return -1

S = sorted(set(L))
for i in L:
    print(binarySearch(i, S), end=" ")
