import sys

input = sys.stdin.readline

N = int(input())

word = []
alphabet = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"]
alphabet_value = [None for _ in range(26)]

digit = [0 for _ in range(26)]


def rank(L: list):
    seq = [None for _ in range(len(L))]
    visited = [False for _ in range(len(L))]
    L_sorted = list(sorted(L, reverse=True))

    for i in range(len(L)):
        for j in range(len(L)):
            if visited[j]:
                continue
            if L[i] == 0:
                continue
            if L[i] == L_sorted[j]:
                visited[j] = True
                seq[j] = i
                break

    return seq


for _ in range(N):
    IN = input().rstrip()
    word.append(IN)
    for i, char in enumerate(reversed(IN)):
        digit[alphabet.index(char)] += 1 * (10**i)

v = 9
seq = rank(digit)
for s in seq:
    if s == None:
        break
    if alphabet_value[s] != None:
        continue
    alphabet_value[s] = v
    v -= 1

ans = []
for w in word:
    tmp = list(w)
    for i, char in enumerate(w):
        idx = alphabet.index(char)
        if alphabet_value[idx] != None:
            tmp[i] = alphabet_value[idx]
    ans.append(int("".join(map(str, tmp))))

print(sum(ans))
