import sys
from sys import stdin
from collections import deque

def moveEast():
    global dice
    temp = dice[0]
    dice[0] = dice[4]
    dice[4] = dice[2]
    dice[2] = dice[5]
    dice[5] = temp

def moveWest():
    global dice
    temp = dice[0]
    dice[0] = dice[5]
    dice[5] = dice[2]
    dice[2] = dice[4]
    dice[4] = temp
    return dice

def moveNorth():
    global dice
    temp = dice[0]
    dice[0] = dice[1]
    dice[1] = dice[2]
    dice[2] = dice[3]
    dice[3] = temp

def moveSouth():
    global dice
    temp = dice[0]
    dice[0] = dice[3]
    dice[3] = dice[2]
    dice[2] = dice[1]
    dice[1] = temp

def getDirection(val, t):
    # 시계 방향
    if t == 0:
        if val == 3: return 0
        return val+1
    # 반시계 방향
    if t == 1:
        if val == 0: return 3
        return val-1
    # 반대 방향
    if t == 2:
        if val == 0: return 2
        if val == 1: return 3
        if val == 2: return 0
        if val == 3: return 1

def rotateDice(d):
    if d == 0 : moveEast()
    if d == 1 : moveSouth()
    if d == 2 : moveWest()
    if d == 3 : moveNorth()

def bfs(s, num):
    global n,m, board
    visited = [[False] * m for _ in range(n)]
    cnt = 1
    q = deque()
    visited[s[0]][s[1]] = True
    q.append(s)

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0<=nx<n and 0 <=ny<m and not visited[nx][ny] and board[nx][ny] == num:
                visited[nx][ny] = True
                cnt+=1
                q.append((nx, ny))
    return cnt


dice = [1,5,6,2,4,3]
# 동 남 서 북
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

n, m, k = map(int, input().split())
board = [list(map(int, stdin.readline().rstrip().split())) for _ in range(n)]
x = y = 0
direction = 0
answer = 0
for _ in range(k):
    nx = x + dx[direction]
    ny = y + dy[direction]

    # 범위 밖이라면 => 반대 방향으로 틀어준다.
    if nx < 0 or nx >= n or ny < 0 or ny >= m:
        direction = getDirection(direction, 2)
        nx = x + dx[direction]
        ny = y + dy[direction]

    x = nx
    y = ny
    rotateDice(direction)
    # 점수 획득
    answer += (board[nx][ny] * bfs((nx, ny), board[nx][ny]))
    if dice[2] > board[nx][ny]:
        direction = getDirection(direction, 0)
    elif dice[2] < board[nx][ny]:
        direction = getDirection(direction, 1)

print(answer)