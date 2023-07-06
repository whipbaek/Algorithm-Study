import copy
from sys import stdin
from collections import deque

def bfs(x, y):
    global dx, dy, board, n_size
    visited = [[False] * (n_size) for _ in range(n_size)]
    res = 0
    q = deque()
    visited[x][y] = True
    q.append((x, y))

    while q :
        x, y = q.popleft()
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]
            if 0<=nx<n_size and 0<=ny<n_size and not visited[nx][ny] and board[nx][ny] != 0:
                visited[nx][ny] = True
                q.append((nx, ny))

    for i in range(n_size):
        for j in range(n_size):
            if visited[i][j]: res +=1
    return res

def rotate(temp):
    return list(map(list, zip(*temp[::-1])))


# 초기화
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
n, q = map(int, input().split())
n_size = 2**n
board = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(n_size) ]
l_list = list(map(int, stdin.readline().rstrip().split()))

for l in l_list :
    x, y = 0, 0
    l_size = 2**l
    
    # 격자 개수만큼 실행한다.
    for _ in range(1,  (n_size**2) // (l_size**2)+1 ):
        if y >= n_size:
            x += l_size
            y = 0
        temp = []
        for i in range(x, x + l_size):
            t = []
            for j in range(y, y + l_size):
                t.append(board[i][j])
            temp.append(t)
        temp = rotate(temp)

        k = m = 0
        for i in range(x, x + l_size):
            for j in range(y, y + l_size):
                board[i][j] = temp[k][m]
                m += 1
            k += 1
            m = 0
        y += 2**l


    # 각 칸들의 인접 얼음칸을 확인한다.
    t_board = copy.deepcopy(board)
    for i in range(n_size):
        for j in range(n_size):
            if t_board[i][j] == 0: continue

            cnt = 0
            for k in range(4):
                nx = i + dx[k]
                ny = j + dy[k]

                if 0<=nx<n_size and 0<=ny<n_size and t_board[nx][ny] != 0:
                    cnt +=1

            if cnt < 3:
                board[i][j] -= 1

# 합을 구한다.
answer = 0
for i in range(n_size):
    for j in range(n_size):
        answer += board[i][j]

maxSize = 0
# 가장 큰 대륙을 구한다.
for i in range(n_size):
    for j in range(n_size):
        if board[i][j] != 0:
            maxSize = max(bfs(i, j), maxSize)

print(answer)
print(maxSize)