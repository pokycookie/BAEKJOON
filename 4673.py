def d(n: int):
    L = list(str(n))
    S = n
    for i in L:
        S += int(i)
    return S

notSelf = []
for i in range(10000):
    notSelf.append(d(i))

self = []
for i in range(10000):
    if notSelf.count(i) == 0: self.append(i)

for i in self:
    print(i)
