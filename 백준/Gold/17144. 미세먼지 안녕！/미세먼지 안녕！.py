from sys import stdin
from collections import deque
import sys
import heapq
import copy

# 어느 방향으로 바람이 부는지 초기화 한다.


def getAirLocation():
    global A, r, c
    temp = []
    for i in range(r):
        for j in range(c):
            if A[i][j] == -1:
                temp.append((i,j))
    return temp

def initDirection():
    global A, direction, r, c, air

    # 공기청정기 위치는 7로 처리
    direction[air[0][0]][air[0][1]+1] = 7
    direction[air[1][0]][air[1][1]+1] = 7

    # 공기청정기 상단 처리
    x, y = air[0]
    y += 1
    while True:
        if y == c-1: break
        direction[x][y] = 0
        y += dy[0]
    while True:
        if x == 0: break
        direction[x][y] = 1
        x += dx[1]
    while True:
        if y == 0: break
        direction[x][y] = 2
        y += dy[2]
    while True:
        if x == air[0][0]: break
        direction[x][y] = 3
        x += dx[3]

    # 공기청정기 하단 처리
    x, y = air[1]
    y += 1
    while True:
        if y == c-1: break
        direction[x][y] = 0
        y += dy[0]
    while True:
        if x == r-1: break
        direction[x][y] = 3
        x += dx[3]
    while True:
        if y == 0 : break
        direction[x][y] = 2
        y += dy[2]
    while True:
        if x == air[1][0]: break
        direction[x][y] = 1
        x += dx[1]

# 미세먼지를 퍼트리고, 결과 배열을 return 한다.
def spread():
    global A, r, c
    # -> 각 좌표에 퍼진 값, 그리고 각 좌표에 남은값을 따로 저장해두자.
    res = [[0] * c for _ in range(r)]

    for x in range(r):
        for y in range(c):
            if A[x][y] == -1: res[x][y] = -1

            # 해당 칸이 미세먼지 칸이라면
            if A[x][y] != 0 and A[x][y] != -1:
                val = A[x][y] // 5
                count = 0
                for k in range(4):
                    nx = x + dx[k]
                    ny = y + dy[k]

                    # 퍼질려는곳이 범위 안이면서, 공기청정기가 위치한곳이 아니라면
                    # -> 확산 횟수 카운트 및, 나누기 5한 값을 저장해준다.
                    if 0 <= nx < r and 0 <= ny < c and A[nx][ny] != -1:
                        count += 1
                        res[nx][ny] += val
                res[x][y] += (A[x][y] - (val * count))
    return res

# 바람이 분다.
def blow():
    global A, air
    for position in air:
        before = 0
        x, y = position
        y += 1
        while True:
            # 공기청정기라면, 갱신해주지 않고 빠져나온다.
            if A[x][y] == -1:
                break

            now = A[x][y]
            A[x][y] = before
            before = now

            nx = x + dx[direction[x][y]]
            ny = y + dy[direction[x][y]]
            x = nx
            y = ny


# Initialize -> 우, 상, 좌, 하
dx = [0, -1, 0, 1]
dy = [1, 0, -1, 0]

r, c, t = map(int,input().split())
A = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(r) ]
direction = [[9] * c for _ in range(r)]
air = getAirLocation()
initDirection()



for i in range(t):
    A = spread()
    blow()



answer = 0
for i in range(r):
    for j in range(c):
        if A[i][j] != -1:
            answer += A[i][j]
print(answer)