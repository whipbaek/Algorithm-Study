import copy
from sys import stdin
from collections import deque

'''
1. l을 입력받는다.
2. 0, 0 부터 2**n 범위를 잡고 시계회전을 시킨다. => 결과배열을 둬도 좋을 거 같다.
3. 각 칸들을 모두 확인하면서 인접한 칸이 3개 미만이면 해당값을 -1 해준다. (0이면 확인하지 않는다.)
4. 1 ~ 3 번을 q번 반복하면 된다.

5. 각 칸들을 모두 확인하며 sum으로 값을 구한다.
6. bfs로 가장 큰 범위의 얼음을 구한다.


'''
# n이 4이고, l이 1일때 -> 16번 나눔
# 64 / 4 = 16
# 한 행은 64번을 16번으로 나눈다. = > 16 / 4 = 4

# n이 4이고 l이 2일때 -> 4번 나눔
# 64 / 16 = 4


def bfs(x, y):
    global dx, dy, board
    visited = [[False] * (2 ** n) for _ in range(2 ** n)]
    ox, oy = x, y
    res = 0
    q = deque()
    visited[x][y] = True
    q.append((x, y))

    while q :
        x, y = q.popleft()
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]
            if 0<=nx<2**n and 0<=ny<2**n and not visited[nx][ny] and board[nx][ny] != 0:
                visited[nx][ny] = True
                q.append((nx, ny))

    for i in range(2**n):
        for j in range(2**n):
            if visited[i][j]: res +=1

    return res

def rotate(temp):
    return list(map(list, zip(*temp[::-1])))

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
n, q = map(int, input().split())
board = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(2**n) ]


l_list = list(map(int, stdin.readline().rstrip().split()))
for l in l_list :
    # 격자 개수만큼 실행한다.
    x, y = 0, 0
    for _ in range(1,  ((2**n)**2 // (2**l)**2)+1 ):
        if y >= 2**n:
            x += 2 **l
            y = 0
        temp = []
        for i in range(x, x + 2**l):
            t = []
            for j in range(y, y + 2 ** l):
                t.append(board[i][j])
            temp.append(t)
        temp = rotate(temp)

        k = m = 0
        for i in range(x, x + 2**l):
            for j in range(y, y + 2 ** l):
                board[i][j] = temp[k][m]
                m += 1
            k += 1
            m = 0
        y += 2**l


    # 각 칸들의 인접 얼음칸을 확인한다.
    t_board = copy.deepcopy(board)
    for i in range(2**n):
        for j in range(2**n):
            if t_board[i][j] == 0: continue

            cnt = 0
            for k in range(4):
                nx = i + dx[k]
                ny = j + dy[k]

                if 0<=nx<2**n and 0<=ny<2**n and t_board[nx][ny] != 0:
                    cnt +=1

            if cnt < 3:
                board[i][j] -= 1

# 합을 구한다.
answer = 0
for i in range(2**n):
    for j in range(2**n):
        answer += board[i][j]

maxSize = 0
# 가장 큰 대륙을 구한다.
for i in range(2**n):
    for j in range(2**n):
        if board[i][j] != 0:
            maxSize = max(bfs(i, j), maxSize)

print(answer)
print(maxSize)