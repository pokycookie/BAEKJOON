import sys
input = sys.stdin.readline

N = int(input())

def fibonacci(n: int):
    if n < 2: return n
    else: return fibonacci(n - 1) + fibonacci(n - 2)

print(fibonacci(N))
