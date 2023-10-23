import sys

def solution(a):
    front = 0
    back = len(a) - 1
    minVal = min(a)
    answer = 1 # min 값

    # minVal 왼쪽
    rangeMin = sys.maxsize
    while a[front] != minVal:
        if a[front] > rangeMin:  # 문제 발생!
            front += 1
        else:  # 내가 더 작다면
            rangeMin = a[front]
            front += 1
            answer += 1

    # minVal 오른쪽
    rangeMin = sys.maxsize
    while a[back] != minVal:
        if a[back] > rangeMin:
            back -= 1
        else:
            rangeMin = a[back]
            back -= 1
            answer += 1

    return answer
