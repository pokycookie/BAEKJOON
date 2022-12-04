N = int(input())

def hanoi(n, start, end, via):
    if n < 0: return
    if n == 1:
        print(start, end)
    else:
        hanoi(n - 1, start, via, end)
        print(start, end)
        hanoi(n - 1, via, end, start)

print(2 ** N - 1)
hanoi(N, 1, 3, 2)
