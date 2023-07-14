import sys
import heapq

input = sys.stdin.readline

N = int(input())
HEAP = []

for _ in range(N):
    heapq.heappush(HEAP, int(input()))

if N < 2:
    print(0)
else:
    ans = -sum(HEAP)
    while HEAP:
        v1 = heapq.heappop(HEAP)
        if not HEAP:
            ans += v1
            break
        v2 = heapq.heappop(HEAP)
        ans += v1 + v2
        heapq.heappush(HEAP, v1 + v2)
    print(ans)
