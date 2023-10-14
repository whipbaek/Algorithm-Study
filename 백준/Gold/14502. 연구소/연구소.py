import copy
from collections import deque
from sys import stdin

# 특정한 세곳의 위치에 벽을 세우고, 안전 영역을 체크한다.
# 벽 생성 -> 바이러스 전파 -> 안전 영역 체크

def getVirusPosition():
    temp = []
    for i in range(n):
        for j in range(m):
            if board[i][j] == 2:
                temp.append((i, j))
    return temp


def setWall(cnt):
    # 벽을 3개 세웠다면, 바이러스 전파
    if cnt == 3:
        spreadVirus()
        return

    for i in range(n):
        for j in range(m):
            if board[i][j] ==  0:
                board[i][j] = 1
                setWall(cnt+1)
                board[i][j] = 0

def spreadVirus():
    global answer
    temp = copy.deepcopy(board)

    q = deque()
    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]

    for v in virusPosition:
        q.append(v)

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0<=nx<n and 0<=ny<m and temp[nx][ny] == 0:
                temp[nx][ny] = 2
                q.append((nx, ny))

    res = 0

    for i in range(n):
        for j in range(m):
            if temp[i][j] == 0:
                res += 1

    answer = max(res, answer)


n, m = map(int, input().split())
board = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(n)]
virusPosition = getVirusPosition()
answer = -1
setWall(0)
print(answer)