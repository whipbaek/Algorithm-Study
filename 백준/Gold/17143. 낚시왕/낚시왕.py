import sys
from sys import stdin
from collections import deque

import copy
import heapq

def getShark(loca):
    global sea, R, answer
    for i in range(R):
        if sea[i][loca][0] != 0:
            shark = sea[i][loca][0]
            answer += shark
            sea[i][loca] = [0, 0, 0]
            return

def changeDirection(d):
    if d == 0: return 1
    if d == 1: return 0
    if d == 2: return 3
    if d == 3: return 2

def moveShark():
    global sea, R, C

    t_sea = [[0] * C for _ in range(R)]
    for i in range(R):
        for j in range(C):
            t_sea[i][j] = [0, 0, 0]

    for i in range(R):
        for j in range(C):
            x, y = i, j
            z, d, s = sea[x][y]
            # 상어가 존재한다면
            if z != 0:
                for _ in range(s):
                    nx = x + dx[d]
                    ny = y + dy[d]

                    # 범위를 벗어난다면 방향을 변경해서 한 칸 가야한다.
                    if nx < 0 or nx >= R or ny < 0 or ny >= C:
                        d = changeDirection(d)
                        nx = x + dx[d]
                        ny = y + dy[d]

                    x = nx
                    y = ny

                if t_sea[x][y][0] != 0:
                    if t_sea[x][y][0] < z:
                        t_sea[x][y] = [z, d, s]
                else:
                    t_sea[x][y] = [z, d, s]
    return t_sea

# 위 아래 오른쪽 왼쪽
dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]
answer = 0

R, C, M = map(int, stdin.readline().rstrip().split())
sea = [[0] * C for _ in range(R)]

for i in range(R):
    for j in range(C):
        sea[i][j] = [0, 0, 0]

for i in range(M):
    x,y,s,d,z = map(int, stdin.readline().rstrip().split())
    # 특정 위치에 크기, 방향, 속도 저장한다.
    sea[x-1][y-1] = [z, d-1, s]


for i in range(C):
    getShark(i)
    sea = moveShark()

print(answer)