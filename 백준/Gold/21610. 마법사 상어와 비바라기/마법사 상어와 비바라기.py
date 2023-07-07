import copy
from sys import stdin

def changePosition(x, y):

    if x < 0:
        x = n - ((-x)%n)
        if x == n: x = 0
    if y < 0:
        y = n - ((-y) % n)
        if y == n: y = 0
    return x % n, y % n


def moveCloud(cloud, direct, speed):
    tempCloud = []
    for val in cloud:

        nx = val[0] + dx[direct] * speed
        ny = val[1] + dy[direct] * speed
        tempCloud.append(changePosition(nx, ny))

    return tempCloud


dx = [0, -1, -1, -1, 0, 1, 1, 1]
dy = [-1, -1, 0, 1, 1, 1, 0, -1]
dia = [1, 3, 5, 7]
n, m = map(int, input().split())
board = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(n) ]
# 구름의 위치를 저장한 배열
cloud = [(n-1, 0), (n-1, 1), (n-2, 0), (n-2, 1)]
command = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(m) ]
for d, s in command:
    d -= 1
    # 이동한다.
    resultCloud = moveCloud(cloud, d, s)

    # 물의 양을 증가시켜준다.
    for val in resultCloud:
        board[val[0]][val[1]] += 1

    # 대각선 활용한다.
    for val in resultCloud:
        for i in range(4):
            nx = val[0] + dx[dia[i]]
            ny = val[1] + dy[dia[i]]

            # 대각선에 바구니가 있다면 값을 증가시켜준다.
            if 0 <= nx < n and 0 <= ny < n and board[nx][ny] != 0:
               board[val[0]][val[1]] += 1

    # 원래 구름있던 위치말고 2이상인곳에 구름이 새로 생기고, 2만큼 감소해준다.
    tempCloud = []
    for i in range(n):
        for j in range(n):
            if (i, j) not in resultCloud:
                if board[i][j] >= 2:
                    tempCloud.append((i, j))
                    board[i][j] -= 2
    cloud = copy.deepcopy(tempCloud)

answer = 0
for i in range(n):
    for j in range(n):
        answer += board[i][j]
print(answer)
