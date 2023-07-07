import copy
from sys import stdin
from collections import deque

# 해당 그룹의 정보들을 탐색 및 반환한다.
# 성공여부, 블록그룹의 사이즈, 무지개 블록의 개수, 기준블록 좌표
def getGroupsInfo(x, y):
    global normalVisited, dx, dy, normalVisited
    blockNum = board[x][y]
    groupSize = 1
    rainbowNum = 0
    visited = [ [False for _ in range(n)] for _ in range(n) ]
    deletePostions = [(x, y)]
    q = deque()
    q.append((x, y))
    visited[x][y] = True

    while q :
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0<=nx<n and 0<=ny<n and not visited[nx][ny]:
                if board[nx][ny] == 0 or board[nx][ny] == blockNum:

                    if board[nx][ny] == 0 :
                        rainbowNum += 1
                    else:
                        normalVisited[nx][ny] = True

                    groupSize += 1
                    q.append((nx, ny))
                    visited[nx][ny] = True
                    deletePostions.append((nx, ny))
    if groupSize < 2:
        return False, groupSize, rainbowNum, deletePostions
    return True, groupSize, rainbowNum, deletePostions

def update(t_groupSize, t_rainbowNum, t_position, t_deletePositions):
    global  position, groupSize, rainbowNum, deletePositions
    position = t_position
    groupSize = t_groupSize
    rainbowNum = t_rainbowNum
    deletePositions = copy.deepcopy(t_deletePositions)


# 조건에 따라 갱신해준다.
def updateValues(t_groupSize, t_rainbowNum, t_position, t_deletePositions):
    global position, groupSize, rainbowNum, deletePositions

    if t_groupSize > groupSize:
        update(t_groupSize, t_rainbowNum, t_position, t_deletePositions)

    elif t_groupSize == groupSize:
        if t_rainbowNum > rainbowNum:
            update(t_groupSize, t_rainbowNum, t_position, t_deletePositions)

        elif t_rainbowNum == rainbowNum:
            if t_position[0] > position[0]:
                update(t_groupSize, t_rainbowNum, t_position, t_deletePositions)

            elif t_position[0] == position[0]:
                if t_position[1] > position[1]:
                    update(t_groupSize, t_rainbowNum, t_position, t_deletePositions)

def deleteBlocks():
    global deletePositions
    for val in deletePositions:
        board[val[0]][val[1]] = -9


# 특정칸이 빈칸이라면 -> 내 위 칸들을 모두 검사하면서 -1이 아니라면 내 위치에 두게 한다.
# 찾은값은 -9로 변경시켜줘야한다.
def gravity():
    global board
    # 열을 하나씩, 밑에서부터 검사하면서 해당 칸이 비어있다면 값을 당겨온다.
    # 이전칸이 -1이라면 break한다.
    # 해당칸이 비어있지 않으면 다음칸으로 올라간다.
    # 만약 0 이라면 break한다.

    for i in range(n):
        for j in range(n-1, 0, -1):

            # 해당칸이 빈칸이라면 위에 있는 친구를 땡겨준다.
            if board[j][i] == -9:
                # 윗칸을 순회한다.
                for k in range(j-1, -1, -1):
                    # 검은색블록이면 못 당겨온다.
                    if board[k][i] == -1:
                        break
                    # 빈칸이 아닌 다른값이라면 당겨준다.
                    if board[k][i] != -9 :
                        board[j][i] = board[k][i]
                        board[k][i] = -9
                        break

def rotate():
    global board
    board = list(map(list, zip(*board)))[::-1]


# Initialize
# normalVisited => 같은 블록그룹은 한번만 방문하도록 하기위함
n, m = map(int, input().split())
board = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(n) ]
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
answer = 0
while True:
    normalVisited = [[False for _ in range(n)] for _ in range(n)]
    isExist = False
    position = (0, 0)
    groupSize = 0
    rainbowNum = 0
    deletePositions = []
    for i in range(n):
        for j in range(n):
            # 무지개블록이나, 검은색 블록이 아니고, 빈칸이 아니고, 방문하지 않았다면
            if board[i][j] != -1 and board[i][j] != 0 and board[i][j] != -9 and not normalVisited[i][j]:
                normalVisited[i][j] = True
                t_position = (i, j)
                t_possible, t_groupSize, t_rainbowNum, t_deletePositions = getGroupsInfo(i, j)
                if not t_possible:
                    continue
                # 만족하는 그룹이 한개라도 있다면 True, 그리고 조건에 따른 갱신
                isExist = True
                updateValues(t_groupSize, t_rainbowNum, t_position, t_deletePositions)
    if not isExist:
        break
    answer += groupSize ** 2

    # 해당 칸 삭제 -> 중력 작용 -> 반시계 회전 -> 중력 작용
    deleteBlocks()
    # print('삭제 후')
    # for b in board:
    #     print(b)
    # print()

    gravity()
    # print('중력 후')
    # for b in board:
    #     print(b)
    # print()

    rotate()
    # print('회전 후')
    # for b in board:
    #     print(b)
    # print()

    gravity()
    # print('중력 후')
    # for b in board:
    #     print(b)
    # print()
print(answer)