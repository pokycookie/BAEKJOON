import sys

input = sys.stdin.readline

# N: 물건의 개수, C: 가방의 최대 용량 (최대로 담을 수 있는 무게)
N, C = map(int, input().split())
# L: 물건의 무게
L = list(map(int, input().split()))
# L1, L2: L을 절반으로 나눔
L1, L2 = L[: N // 2], L[N // 2 :]


# 리스트 L에서 만들 수 있는 모든 부분 집합의 합을 반환
def BF(L: list, seq=None, size=0, summ=0):
    # seq의 값이 없는 경우 빈 리스트로 초기화
    if seq is None:
        seq = []

    # size가 리스트 L의 크기와 같을 경우 seq에 summ을 추가하여 반환
    # 리스트의 모든 요소를 사용하였기 때문에 더 이상 작업을 진행하지 않음
    if len(L) == size:
        seq.append(summ)
        return seq

    # size를 1 증가시켜 BF를 다시 계산
    # 이전보다 1개 더 많은 요소를 사용한 결과를 seq에 추가
    # size가 계속 증가하다가 len(L)과 같아지면 seq를 반환
    BF(L, seq, size + 1, summ)
    BF(L, seq, size + 1, summ + L[size])

    return seq


ANS = 0
L1 = BF(L1)
L2 = sorted(BF(L2))

for v in L1:
    # L1의 값인 v가 가방의 최대 용량(C)을 넘어가는 경우 continue
    if v > C:
        continue

    # L2를 이분탐색하기 위한 두 개의 포인터 지정
    start, end = 0, len(L2)
    while start < end:
        # start와 end의 중간지점을 mid로 정함
        mid = (start + end) // 2
        # 만약 L2[mid]의 값에 v를 더했을 때 가방의 최대 용량(C)을 넘어간다면
        if L2[mid] + v > C:
            # end = mid로 하여, L2의 더 작은 값들을 찾게 함
            end = mid
        # 만약 L2[mid]의 값에 v를 더했을 때 가방의 최대 용량(C)을 넘어가지 않는다면
        else:
            # start = mid + 1로 하여, L2의 더 큰 값들을 찾게 함
            start = mid + 1

    # 이분 탐색이 끝나면 start를 정답으로 함
    # 처음부터 start지점까지는 물건을 가방에 넣을 수 있는 방법이라고 볼 수 있기 때문
    ANS += start

print(ANS)
