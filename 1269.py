import sys
input = sys.stdin.readline

N, M = map(int, input().split())
NL = set(map(int, input().split()))
ML = set(map(int, input().split()))

print(len(NL ^ ML))
