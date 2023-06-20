from collections import deque

# 방향 변환
def getdir(d, turn):
    if turn == 'L':
        if d == 0:
            return 3
        else:
            return d-1
    else:
        if d == 3:
            return 0
        else:
            return d+1

# 방향 순서 ← ↑ → ↓
dx = [0, -1, 0, 1]
dy = [-1, 0, 1, 0]

n = int(input())
k = int(input())

# 사과의 위치를 저장한다.
board = [[0] * n for _ in range(n)]

# 뱀의 위치를 저장한다.
snake = [[0] * n for _ in range(n)]
snake[0][0] = 1
position = deque()
position.append((0, 0))

# 사과의 위치를 입력 받는다.
for _ in range(k):
    a, b = map(int, input().split())
    board[a-1][b-1] = 1


l = int(input())

# 첫 방향 위치
direction = 2
answer = 0
x = y = 0
lx = ly = 0
flag = True

times = []

for _ in range(l):
    time, d = map(str, input().split())
    times.append([int(time), d])
i = 0
# 시간순으로 정렬해준다.
times.sort()

time, turn = times.pop(0)
while True:
    if i == time:
        direction = getdir(direction, turn)
        if times:
            time, turn = times.pop(0)

    nx = x+dx[direction]
    ny = y+dy[direction]
    # 다음칸이 범위 안에 들어오면서, 뱀의 몸이 아닌경우에
    if 0 <= nx < n and 0 <= ny < n and snake[nx][ny] == 0:

        # 이동하는 곳의 위치를 체크 및 deque에 넣어준다.
        snake[nx][ny] = 1
        position.appendleft((nx, ny))
        # 사과가 있다면 먹어준다
        if board[nx][ny] == 1:
            board[nx][ny] = 0

        # 사과가 없다면 뱀의 꼬리를 삭제해준다.
        else:
            lx, ly = position.pop()
            snake[lx][ly] = 0

        x = nx
        y = ny
        answer += 1

    # 부딪혔다면 게임을 끝내준다.
    else:
        flag = False
        break

    i+=1

print(answer+1)