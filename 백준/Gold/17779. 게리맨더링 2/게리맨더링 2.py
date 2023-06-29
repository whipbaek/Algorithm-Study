import itertools
from sys import stdin
import sys


def getFifth(x, y, d1, d2, area):
    fifth = 0
    start = end = y
    cnt1 = 0
    cnt2 = 0
    d1_cnt = 0
    d2_cnt = 0
    for i in range(x, x+d1+d2+1):
        for j in range(start+cnt1, end+cnt2+1):
            area[i][j] = 5
            fifth += people[i][j]

        # d1만큼 이동했다면, 앞에 좌표는 더해준다. 그전에는 빼주고
        if d1_cnt < d1:
            cnt1 -= 1
        else:
            cnt1 += 1

        if d2_cnt < d2:
            cnt2 += 1
        else:
            cnt2 -= 1

        d1_cnt +=1
        d2_cnt +=1

    return area, fifth
def getFirst(x, y, d1, d2, area):
    global people
    # x + d1번 반복하면 된다.
    # 만약 5를 만나면 break, 아니라면 y까지 해주면 된다.
    first = 0
    for i in range(x+d1):
        for j in range(y+1):
            if area[i][j] == 5 or j >= y+1:
                break
            else:
                area[i][j] = 1
                first += people[i][j]
    return area, first
def getSecond(x, y, d1, d2, area):
    global people
    second = 0
    for i in range(x+d2+1):
        for j in range(y+1, n):
            if area[i][j] == 5: continue
            else:
                area[i][j] = 2
                second += people[i][j]
    return area, second
def getThird(x, y, d1, d2, area):
    global people
    third = 0
    for i in range(x+d1, n):
        for j in range(y-d1+d2):
            if area[i][j] == 5 : break
            else:
                area[i][j] = 3
                third += people[i][j]
    return area, third
def getFourth(x, y, d1, d2, area):
    global people
    fourth = 0
    for i in range(n-1, x+d2, -1):
        for j in range(y-d1+d2, n):
            if area[i][j] == 5: continue
            else:
                area[i][j] = 4
                fourth += people[i][j]
    return area, fourth

def getPartValue(x, y, d1, d2):
    global people, n, answer
    area = [ [0] * n for _ in range(n) ]
    if 0<= x+d1 < n and 0 <= y-d1 < n and \
            0<= x+d1+d2 < n and 0 <= y-d1+d2 < n and \
            0<= x+d2 < n and 0 <= y+d2 < n:

        area, fifth = getFifth(x, y, d1, d2, area)
        area, first = getFirst(x, y, d1, d2, area)
        area, second = getSecond(x, y, d1, d2, area)
        area, third = getThird(x, y, d1, d2, area)
        area, fourth = getFourth(x, y, d1, d2, area)

        maximum = max(first, second, third, fourth, fifth)
        minimum = min(first, second, third, fourth, fifth)
        answer = min(answer, maximum - minimum)
    else:
        return

answer = sys.maxsize
n = int(input())
people = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(n) ]
boundary = []
for i in range(1, (n//2)+2):
    boundary.append(i)
permu = itertools.product(boundary, repeat=2)

for p in permu:
    for i in range(0, n-2):
        for j in range(1, n-1):
            getPartValue(i, j, p[0], p[1])

print(answer)
