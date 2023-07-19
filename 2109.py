import sys
import heapq

input = sys.stdin.readline

n = int(input())
prices = [tuple(map(int, input().split())) for _ in range(n)]
heap = []

prices.sort(key=lambda x: x[1])

for p, d in prices:
    heapq.heappush(heap, p)
    if len(heap) > d:
        heapq.heappop(heap)

print(sum(heap))
