def solution(sticker):
    l = len(sticker)

    if l == 1: return sticker[0]
    if l == 2: return max(sticker)

    d = [0] * l
    d_copy = [0] * l

    # 첫번째 스티커를 사용하는 경우
    d[0] = sticker[0]
    d[1] = d[0]
    for i in range(2, l-1):
        d[i] = max(d[i-2] + sticker[i], d[i-1])

    # 두번째 스티커를 사용하는 경우
    d_copy[1] = sticker[1]
    for i in range(1, l):
        d_copy[i] = max(d_copy[i-2] + sticker[i], d_copy[i-1])

    return max(d[l-2], d_copy[l-1])



