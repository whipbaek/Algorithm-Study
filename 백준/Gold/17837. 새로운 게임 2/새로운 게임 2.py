from sys import stdin
from collections import deque
'''

이동시, 자기 위의 친구들과 함께 어떻게 잘 이동할것인지를 생각해야함.

'''

def whiteProcess(x, y, nx, ny, seq):
    global colors, board, position
    tempDeque = deque()
    while board[x][y]:
        val = board[x][y].pop()
        tempDeque.appendleft(val)
        position[val] = (nx, ny, position[val][2])
        if val == seq: break
    while tempDeque:
        board[nx][ny].append(tempDeque.popleft())

    return len(board[nx][ny])

def redProcess(x, y, nx, ny, seq):

    global colors, board, position

    while board[x][y]:
        val = board[x][y].pop()
        board[nx][ny].append(val)
        position[val] = (nx, ny, position[val][2])
        if val == seq: break

    return len(board[nx][ny])

def changeDir(d):
    if d == 0 : return 1
    if d == 1 : return 0
    if d == 2 : return 3
    if d == 3 : return 2

# →, ←, ↑, ↓
dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]

n, k = map(int, input().split())

colors = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(n) ]
position = {}
board = [ [deque() for _ in range(n)] for _ in range(n) ]

for num in range(1, k + 1):
    x, y, d = map(int, stdin.readline().rstrip().split())
    board[x-1][y-1].append(num)
    position[num] = (x-1, y-1, d-1)

seq = 1
turn = 1
while True:
    if seq > k:
        turn += 1
        seq = 1

    if turn > 1000:
        break

    x, y, d = position[seq]
    # 이동할려는 위치
    nx = x + dx[d]
    ny = y + dy[d]

    totalSize = 0

    # 범위 벗어나거나, 값이 2라면
    if nx < 0 or nx >= n or ny < 0 or ny >= n or colors[nx][ny] == 2:
        # 방향을 변경시켜준다.
        position[seq] = (x, y, changeDir(d))
        # 목적 위치를 새로 갱신한다.
        tx = x - dx[d]
        ty = y - dy[d]

        # 새로운 목적지도 범위가 벗어나거나, 파란색이라면 방향만 바꿔주고 그냥 둔다.
        if tx < 0 or tx >= n or ty < 0 or ty >= n or colors[tx][ty] == 2:
            pass
        # 둘 다 아니라면 빨강이나 흰색을 판단하고 같은 로직을 실행해준다.
        else:
            if colors[tx][ty] == 0:
                totalSize = whiteProcess(x, y, tx, ty, seq)
            elif colors[tx][ty] == 1:
                totalSize = redProcess(x, y, tx, ty, seq)

    # 범위내에 있으며, 파란색이 아닐 경우에
    else:
        if colors[nx][ny] == 0:
            totalSize = whiteProcess(x, y, nx, ny, seq)
        elif colors[nx][ny] == 1:
            totalSize = redProcess(x, y, nx, ny, seq)

    if totalSize >= 4:
        break

    # for b in board:
    #     print(b)
    # print()

    seq += 1

print(turn) if turn <= 1000 else print(-1)