import itertools
import sys
from sys import stdin
from collections import deque

def bfs(positions):
    global dx, dy, lab, answer, n, numOfZero
    distance = [[0] * n for _ in range(n)]
    visited = [[False] * n for _ in range(n)]
    q = deque()
    m_value = 0
    # multiple start
    for p in positions:
        q.append((p[0], p[1]))
        visited[p[0]][p[1]] = True

    flag = False
    zeroCnt = 0
    while q:
        x, y = q.popleft()
        for i in range(4):
            if numOfZero == zeroCnt:
                break

            nx = x+dx[i]
            ny = y+dy[i]

            # 범위에 들어가고, 벽이 아니고, 방문하지 않았다면
            if 0 <= nx < n and 0 <= ny < n and lab[nx][ny] != 1:
                if distance[nx][ny] == 0 and not visited[nx][ny]:

                    if lab[nx][ny] != 2:
                        zeroCnt +=1
                    visited[nx][ny] = True
                    distance[nx][ny] = distance[x][y] +1
                    m_value = max(m_value, distance[nx][ny])
                    q.append((nx, ny))

        # 모든 칸들이 점령 되었다면
        if numOfZero == zeroCnt:
            flag = True
            break

    if not flag:
        return

    answer = min(answer, m_value)


n, m = map(int, stdin.readline().rstrip().split())
lab = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(n) ]
check = [[False] * n for _ in range(n)]
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
answer = sys.maxsize
numOfZero = 0
virusPositions = []
for i in range(n):
    for j in range(n):
        if lab[i][j] == 0:
            numOfZero +=1
        if lab[i][j] == 2:
            virusPositions.append([i, j])

combi = itertools.combinations(virusPositions, m)
for c in combi:
    bfs(c)

print(answer) if answer != sys.maxsize else print(-1)