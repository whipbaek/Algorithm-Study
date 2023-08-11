import copy

def changeDir(val):
    if val == 1: return 8
    return val-1

def copyFish():
    global board
    temp = []
    for i in range(4):
        for j in range(4):
            # 방향 모두를 체크한다.
            for k in range(1, 9):
                if board[i][j][k] == 0: continue
                # board[i][j][k] 의 수를 기억한다.
                temp.append([i, j, k, board[i][j][k]])
    return temp

def moveFish():
    global board, smell, dx, dy, sp
    tempBoard = [[[0 for _ in range(9)] for _ in range(1) for _ in range(4)] for _ in range(4)]

    for i in range(4):
        for j in range(4):
            for k in range(1, 9):

                direction = k
                moved = False
                for _ in range(8):
                    nx = i + dx[direction]
                    ny = j + dy[direction]
                    # 격자에 들어가면서, 상어가 없고, 냄새가 없는칸으로 물고기가 이동이 가능하다.
                    if 0<=nx<4 and 0<=ny<4 and smell[nx][ny] == 0 and not (nx == sp[0] and ny == sp[1]) :
                        tempBoard[nx][ny][direction] += board[i][j][k]
                        moved = True
                        break
                    else:
                        direction = changeDir(direction)

                if not moved:
                    tempBoard[i][j][k] += board[i][j][k]
    return tempBoard


def dfs(x, y, dis, way, positions):
    global board, globalWay, globalFishCnt, globalPositions

    if dis == 3:
        fishCnt = 0
        # 지나온 길 중복을 막기 위해 set을 사용
        setPosition = set()
        for p in positions:
            setPosition.add(p)

        # 지나온 길에 존재했던 총 물고기의 개수를 체크한다.
        for p in setPosition:
            for v in board[p[0]][p[1]]:
                fishCnt += v

        if fishCnt > globalFishCnt:
            globalFishCnt = fishCnt
            globalWay = int(way)
            globalPositions = copy.deepcopy(positions)
        elif fishCnt == globalFishCnt and int(way) < globalWay:
            globalFishCnt = fishCnt
            globalWay = int(way)
            globalPositions = copy.deepcopy(positions)
        return

    # 상어는 상하좌우만 가능하다.
    for i in square:
        nx = x + dx[i]
        ny = y + dy[i]

        if 0<=nx<4 and 0<=ny<4:
            positions.append((nx, ny))
            dfs(nx, ny, dis+1, way+transition[i], positions)
            positions.pop()

def moveShark():
    global board, sp, smell, globalWay, globalFishCnt, globalPositions

    # globalPositions => 상어가 지나온 좌표들 (좌표만 기억하면 된..다)
    # globalFishCnt => 상어가 지나온 좌표들에 존재했던 물고기 개수
    # globalWay => 상어가 지나온 길, 사전순 정렬 필요

    dfs(sp[0], sp[1], 0, '', copy.deepcopy(globalPositions))

    # 상어가 지나온 길 중 물고기가 있다면 해당 위치엔 냄새를 추가해주고, 물고기를 비워준다.
    for p in globalPositions:
        for v in board[p[0]][p[1]]:
            if v >= 1:
                board[p[0]][p[1]] = [0] * 9
                smell[p[0]][p[1]] = 3
                break

    # 상어 위치를 이동시켜준다.
    sp = (globalPositions[-1][0], globalPositions[-1][1])

def fadeSmell():
    global smell
    for i in range(4):
        for j in range(4):
            if smell[i][j] != 0: smell[i][j] -=1

def excuteMagic(copyValue):
    global board
    for x, y, k, val in copyValue:
        board[x][y][k] += val

def getFish():
    global board
    ans = 0
    for i in range(4):
        for j in range(4):
            for k in range(1, 9):
                ans += board[i][j][k]
    return ans

globalWay = 999
globalFishCnt = 0
globalPositions = []

transition = {1:'2', 3:'1', 5:'4', 7:'3'}
dx = [0, 0, -1, -1, -1, 0, 1, 1, 1]
dy = [0, -1, -1, 0, 1, 1, 1, 0, -1]
square = [1,3,5,7]

m, s = map(int, input().split())

# [x][y][n방향으로 바라보는 물고기의 수]
board = [ [ [ 0 for _ in range(9) ] for _ in range(1) for _ in range(4)] for _ in range(4) ]
smell = [ [0] * 4 for _ in range(4) ]

# 초기화
for _ in range(m):
    x, y, d = map(int, input().split())
    board[x-1][y-1][d] += 1
x,y = map(int, input().split())
sp = (x - 1, y - 1)


for _ in range(s):
    globalWay = 999
    globalFishCnt = 0
    globalPositions = []

    # 1. 모든 물고기를 복제한다.
    copyValue = copyFish()

    # 2. 모든 물고기는 반시계 방향으로 돌면서 이동 위치를 찾고 이동한다.
    board = moveFish()


    # 3. 상어가 최적의 위치로 이동한다.
    moveShark()

    # 4. 냄새가 옅어진다.
    fadeSmell()

    # 5. 복제마법이 수행된다.
    excuteMagic(copyValue)

print(getFish())


