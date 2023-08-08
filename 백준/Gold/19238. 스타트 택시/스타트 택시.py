import sys
from sys import stdin
from collections import deque

dx = [-1, 0, 0, 1]
dy = [0, -1, 1, 0]

def bfs(s):
    global fuel, n, board, dx, dy, endPoint
    visited = [[False] * n for _ in range(n)]
    dis = [[0] * n for _ in range(n)]
    visited[s[0]][s[1]] = True
    q = deque()
    q.append(s)

    # 시작점에 사람이 있다면
    if board[s[0]][s[1]] != -9 :
        num = board[s[0]][s[1]]
        if fuel >= distance[num]:
            fuel -= distance[num]
            fuel += distance[num] * 2
            board[s[0]][s[1]] = -9
            return endPoint[num]

    ansNum = sys.maxsize
    ansDis = sys.maxsize
    r = c = sys.maxsize

    # 시작점에 사람이 없는 경우
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0<=nx <n and 0<=ny<n and board[nx][ny] != -1 and not visited[nx][ny]:

                # 해당칸에 사람이 있는 경우
                if board[nx][ny] != -9:
                    num = board[nx][ny]

                    # 우선적으로 거리를 검사해서 넘으면 바로 retur 한다.
                    if ansDis < dis[x][y]+1:
                        fuel -= (ansDis + distance[ansNum])
                        fuel += distance[ansNum] * 2
                        board[r][c] = -9
                        return endPoint[ansNum]

                    # 해당 사람이 원하는 도착지에 갈 수 있다면.
                    if fuel >= dis[x][y]+1 + distance[num]:

                        # # 이전의 거리보다 멀다면, 연산 후 return 해주면 된다.
                        # if ansDis < dis[x][y]+1:
                        #     fuel -= (ansDis + distance[ansNum])
                        #     fuel += distance[ansNum] * 2
                        #     board[r][c] = -9
                        #
                        #     return endPoint[ansNum]
                        # 이전의 거리보다 작다면 갱신해준다.
                        if ansDis > dis[x][y]+1:
                            ansDis = dis[x][y]+1
                            ansNum = board[nx][ny]
                            r = nx
                            c = ny

                        # 이전의 거리와 같다면, 행 열 비교 해준다.
                        elif ansDis == dis[x][y]+1:
                            if nx < r:
                                ansDis = dis[x][y] + 1
                                ansNum = board[nx][ny]
                                r = nx
                                c = ny
                            elif nx == r and ny < c:
                                ansDis = dis[x][y] + 1
                                ansNum = board[nx][ny]
                                r = nx
                                c = ny

                        dis[nx][ny] = dis[x][y] + 1
                        visited[nx][ny] = True
                        q.append((nx, ny))

                    # 도착지에 갈 수 없다면 (단, 이전에 값이 없을 경우에만 해당)
                    else:
                        return -1

                # 사람이 없는 경우 => 택시가 승객을 계속 찾는다.
                else:
                    dis[nx][ny] = dis[x][y] + 1
                    visited[nx][ny] = True
                    q.append((nx, ny))

    if ansNum != sys.maxsize:
        fuel -= (ansDis + distance[ansNum])
        fuel += distance[ansNum] * 2
        board[r][c] = -9

        return endPoint[ansNum]

    return -1

def getDistance(s, e):
    global fuel, n, board, dx, dy
    visited = [[False] * n for _ in range(n)]
    dis = [[0] * n for _ in range(n)]
    q = deque()
    q.append(s)
    visited[s[0]][s[1]] = True

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            # 범위안에 들어가면서 갈 수 있고, 방문하지 않았다면
            if 0 <= nx < n and 0 <= ny < n and board[nx][ny] != -1 and not visited[nx][ny]:
                if nx == e[0] and ny == e[1]:
                    return dis[x][y] + 1
                else:
                    dis[nx][ny] = dis[x][y] + 1
                    visited[nx][ny] = True
                    q.append((nx, ny))

    # 못가는 경우
    return sys.maxsize

n, m, fuel = map(int, input().split())
distance = []
board = [list(map(int, stdin.readline().rstrip().split())) for _ in range(n)]
endPoint = []
# 벽은 -1로 세팅, 빈칸은 -9로 세팅
for i in range(n):
    for j in range(n):
        board[i][j] = -1 if board[i][j] == 1 else -9

start = list(map(int, input().split()))
start[0] -= 1
start[1] -= 1

cnt = 0
for _ in range(m):
    temp = list(map(int, stdin.readline().rstrip().split()))
    for i in range(len(temp)) : temp[i] -= 1
    board[temp[0]][temp[1]] = cnt

    distance.append(getDistance((temp[0], temp[1]), (temp[2], temp[3])))
    endPoint.append([temp[2], temp[3]])
    cnt += 1
flag = True
for _ in range(m):
    res = bfs(start)
    if res == -1:
        flag = False
        break
    else:
        start = res

if not flag:
    print(-1)
else:
    print(fuel)


