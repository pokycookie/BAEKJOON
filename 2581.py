M = int(input())
N = int(input())
L = []

for i in range(N + 1):
    L.append(i)

for i in range(N + 1):
    if L[i] < 2: L[i] = 0
    if L[i] == 0: continue
    
    for j in range(i * 2, N + 1, i):
        L[j] = 0

result = []
for e in L:
    if e >= M and e != 0: result.append(e)

if len(result) > 0:
    print(sum(result))
    print(min(result))
else:
    print(-1)
