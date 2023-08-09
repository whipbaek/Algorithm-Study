import copy
from sys import stdin

def initPosition():
    for i in range(n):
        if i % 2 == 0:
            setPosition(i, 0, -1)
            setPosition(i, 1, 0)
        else:
            setPosition(i, 0, 1)
            setPosition(i, -1, 0)

def setPosition(cnt, tx, ty):
    global x, y, n, position, idx
    for _ in range(cnt + 1):
        x += tx
        y += ty
        if x<0 or y < 0: break
        position[idx] = [x, y]
        idx+=1

def increaseExplosionMarble(value):
    global breakOne, breakTwo, breakThree
    if value == 1: breakOne += 1
    if value == 2: breakTwo += 1
    if value == 3: breakThree += 1

def getTotalMarble():
    global board
    temp = 0
    for b in board:
        for v in b:
            if v != 0: temp += 1
    return temp

def magicBreak(direction, distance):
    global board, n, totalMarble
    x = y = n // 2
    breakMarbles = 0

    # 마법으로 구슬을 파괴한다.
    for i in range(distance):
        nx = x + dx[direction]
        ny = y + dy[direction]

        # 범위 안에 들어간다면, 구슬을 파괴한다.
        if 0 <= nx < n and 0 <= ny < n and board[nx][ny] != 0:
            board[nx][ny] = 0
            breakMarbles += 1
            x = nx
            y = ny
        else:
            break
    return breakMarbles

def readjustBoard():
    global totalMarble, board, position

    targetIdx = 1
    for i in range(1, totalMarble):
        x = position[i][0]
        y = position[i][1]

        # 해당 칸이 비어있다면
        if board[x][y] == 0:

            tx = ty = 0
            # 값이 있는위치를 찾는다.
            while True:
                if targetIdx <= i:
                    targetIdx += 1
                    continue

                if targetIdx > totalMarble: break

                tx = position[targetIdx][0]
                ty = position[targetIdx][1]

                if board[tx][ty] != 0: break
                targetIdx += 1

            if targetIdx <= totalMarble:
                board[x][y] = board[tx][ty]
                board[tx][ty] = 0

def explosionMarble():
    global board, position, n, totalMarble
    while True:
        # 1번째부터 시작한다.
        baseIdx = 1
        breakMarbles = 0
        # 부숴짐이 있었는지 체크한다.

        # 한 사이클을 돈다.
        while True:
            if baseIdx > totalMarble: break
            continuous = 0
            savedPosition = []
            marbleNum = board[position[baseIdx][0]][position[baseIdx][1]]

            # 같은 값을 모두 체크한다.
            while True:
                if baseIdx > totalMarble: break

                if marbleNum == board[position[baseIdx][0]][position[baseIdx][1]]:
                    continuous += 1
                    savedPosition.append([position[baseIdx][0], position[baseIdx][1]])
                    baseIdx += 1
                else:
                    break

            # 4개 이상이라면 모두 파괴해준다.
            if continuous >= 4:
                for _ in range(continuous) : increaseExplosionMarble(marbleNum)
                for ox, oy in savedPosition: board[ox][oy] = 0
                breakMarbles += continuous

        if breakMarbles == 0: break

        # 부서진게 있다면 재조정을 해주고 다시 사이클을 돈다.
        readjustBoard()
        totalMarble -= breakMarbles

def remakeBoard():
    global totalMarble, board, position, n
    tempBoard = [[0] * n for _ in range(n)]

    originIdx = 1
    tempIdx = 1

    while True:
        if originIdx > totalMarble: break
        if tempIdx >= n**2: break
        continuous = 0

        marbleNum = board[position[originIdx][0]][position[originIdx][1]]
        while True:
            if originIdx > totalMarble: break

            if marbleNum == board[position[originIdx][0]][position[originIdx][1]]:
                continuous += 1
                originIdx += 1
            else:
                break

        tempBoard[position[tempIdx][0]][position[tempIdx][1]] = continuous
        tempIdx += 1

        if tempIdx >= n**2: break
        tempBoard[position[tempIdx][0]][position[tempIdx][1]] = marbleNum
        tempIdx += 1

    board = copy.deepcopy(tempBoard)
    totalMarble = getTotalMarble()

dx = [-1, -1, 1, 0, 0]
dy = [-1, 0, 0, -1, 1]

position = {}
n, m = map(int, input().split())
board = [list(map(int, stdin.readline().rstrip().split())) for _ in range(n)]
magics = [list(map(int, stdin.readline().rstrip().split())) for _ in range(m)]

idx = 0
x = y = n//2
position[idx] = [x, y]
idx+=1
initPosition()

totalMarble = getTotalMarble()
breakOne = 0
breakTwo = 0
breakThree = 0

for magic in magics:
    direction = magic[0]
    distance = magic[1]

    # 1. 마법으로 구슬을 부순다.
    breakMables = magicBreak(direction, distance)

    # 2. 당겨온다.
    readjustBoard()
    totalMarble -= breakMables

    # 3. 4개씩 부수는 과정을 반복한다.
    explosionMarble()

    # 4. 새로운 배열을 구성한다.
    remakeBoard()

print(breakOne + (breakTwo * 2) + (breakThree * 3))
