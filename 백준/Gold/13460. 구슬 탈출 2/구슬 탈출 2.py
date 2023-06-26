import sys
import copy
from sys import stdin

answer = sys.maxsize

def getFromWay(val):
    if val == 0: return 1
    if val == 1: return 0
    if val == 2: return 3
    if val == 3: return 2
    return -1

def isBlueFirst(board, x, y, i):
    global dx, dy
    while True:
        if board[x][y] == 'B':
            return True
        if board[x][y] == '#':
            return False

        x += dx[i]
        y += dy[i]

def dfs(origin, red, blue, way, count):
    global dx, dy, answer

    # 횟수가 10번이 넘어가면 return
    if count > 10:
        return

    # 4방향 모두 굴려본다.
    for i in range(4):
        # 온곳으로 되돌아갈 필요는 없다.
        if i == getFromWay(way): continue
        board = copy.deepcopy(origin)

        rx = red[0] + dx[i]
        ry = red[1] + dy[i]

        bx = blue[0] + dx[i]
        by = blue[1] + dy[i]

        r_possible = False
        b_possible = False


        # 빨간색 구슬이 이동할려는 위치에 파란 구슬이 있다면 -> 파란구슬부터 이동시켜준다.
        if isBlueFirst(board, rx, ry, i):

            # 파란구슬 이동처리
            while True:
                if board[bx][by] == '#':
                    board[blue[0]][blue[1]] = '.'
                    before = getFromWay(i)
                    bx = bx + dx[before]
                    by = by + dy[before]
                    board[bx][by] = 'B'
                    break
                elif board[bx][by] == 'O':
                    board[blue[0]][blue[1]] = '.'
                    b_possible = True
                    break

                bx = bx + dx[i]
                by = by + dy[i]

            # 빨간구슬 이동처리
            while True:
                if board[rx][ry] == '#' or board[rx][ry] == 'B':
                    board[red[0]][red[1]] = '.'
                    before = getFromWay(i)
                    rx = rx + dx[before]
                    ry = ry + dy[before]
                    board[rx][ry] = 'R'
                    break
                elif board[rx][ry] == 'O':
                    board[red[0]][red[1]] = '.'
                    r_possible = True
                    break

                rx = rx + dx[i]
                ry = ry + dy[i]

            # 안됐을 시, 되돌려 준 후에, 남은 방향들을 탐색해봐야한다.
            if b_possible:
                continue

            # 됐을경우에는 바로 return한다.
            if r_possible:
                answer = min(answer, count)
                return

        # 빨간구슬부터 움직인다.
        else:
            # 빨간구슬 이동처리
            while True:
                if board[rx][ry] == '#':
                    board[red[0]][red[1]] = '.'
                    before = getFromWay(i)
                    rx = rx + dx[before]
                    ry = ry + dy[before]
                    board[rx][ry] = 'R'
                    break
                if board[rx][ry] == 'O':
                    board[red[0]][red[1]] = '.'
                    r_possible = True
                    break

                rx = rx + dx[i]
                ry = ry + dy[i]

            # 파란구슬 이동처리
            while True:
                if board[bx][by] == '#' or board[bx][by] == 'R':
                    board[blue[0]][blue[1]] = '.'
                    before = getFromWay(i)
                    bx = bx + dx[before]
                    by = by+ dy[before]
                    board[bx][by] = 'B'
                    break
                if board[bx][by] == 'O':
                    board[blue[0]][blue[1]] = '.'
                    b_possible = True
                    break

                bx = bx + dx[i]
                by = by + dy[i]

            # 안됐을 시, 되돌려 준 후에, 남은 방향들을 탐색해봐야한다.
            if b_possible:
                continue

            # 됐을시에도 바로 return 하지 말고, 남은 방향들을 탐색해봐야한다.
            if r_possible:
                answer = min(answer, count)
                return

        # 아무 이동도 없다면 depth를 늘려주지 말고, 다른 방향으로 바로 돌려주면 된다.
        if red[0] == rx and red[1] == ry and blue[0] == bx and blue[1] == by:
            continue

        dfs(board, (rx, ry), (bx, by), i, count+1)

# Initialize
n, m = map(int, input().split())
original = [list(stdin.readline().rstrip()) for _ in range(n)]

# 좌, 우, 하, 상
dx = [0, 0, 1, -1]
dy = [-1, 1, 0, 0]

r_position = 0
b_position = 0
for x in range(n):
    for y in range(m):
        if original[x][y] == 'R':
            r_position = (x, y)
        if original[x][y] == 'B':
            b_position = (x, y)

dfs(original, r_position, b_position, -1, 1)

print(answer) if answer != sys.maxsize else print(-1)
