L = ["A", "B", "C", "D", "E"]

def R(l: list, s: int, r: int):
    result = []
    if r == 0: return [""]
    for i in range(s, len(l)):
        for j in R(l, i + 1, r - 1):
            result.append(l[i] + j)
    return result
  
for i in range(1, len(L) + 1):
    print(R(L, 0, i))
