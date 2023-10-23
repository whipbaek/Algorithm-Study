import sys

def solution(a):
    front = 0
    back = len(a) - 1
    minVal = min(a)
    answer = 1 # min 값

    # minVal 왼쪽
    rangeMin = sys.maxsize
    while a[front] != minVal:
        if a[front] < rangeMin:  # 문제 발생!
            rangeMin = a[front]
            answer += 1
        front += 1
            

    # minVal 오른쪽
    rangeMin = sys.maxsize
    while a[back] != minVal:
        if a[back] < rangeMin:
            rangeMin = a[back]
            answer += 1
        back -= 1
            
    return answer
