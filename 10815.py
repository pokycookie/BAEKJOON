import sys
input = sys.stdin.readline

N = int(input())
NL = sorted(list(map(int, input().split())))
M = int(input())
ML = list(map(int, input().split()))

def binarySearch(l: list, t: int):
    start = 0
    end = len(l) - 1

    while start <= end:
        mid = (start + end) // 2

        if l[mid] == t: return mid
        elif l[mid] < t: start = mid + 1
        else: end = mid - 1
    
    return None
    
for i in ML:
    if binarySearch(NL, i) != None: print(1, end=" ")
    else: print(0, end=" ")
