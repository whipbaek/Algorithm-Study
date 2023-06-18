import itertools
import sys
import copy
from sys import stdin
from collections import deque

# multiple BFS
def bfs(starts):
    global lab, empty, n
    count = 0
    dis = [[0] * n for _ in range(n) ]
    dx = [-1, 1, 0, 0]
    dy = [0, 0, 1, -1]
    # 방문 여부 초기화
    visited = [ [False] * n for _ in range(n)]
    q = deque()

    # 시작점 세팅
    for s in starts:
        a, b = s[0], s[1]
        q.append((a, b))
        visited[a][b] = True
        count += 1

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]

            if 0 <= nx < n and 0 <= ny < n :
                # 방문하지 않았고, 벽이 아니라면 거리를 갱신해준다.
                if not visited[nx][ny] and lab[nx][ny] != 1:
                    q.append((nx, ny))
                    dis[nx][ny] = dis[x][y] + 1
                    visited[nx][ny] = True
                    count +=1


    # 빈칸의 개수와, 바이러스 퍼지는 개수가 같으면 모두 퍼진것이다.
    if empty == count:
        return max(map(max, dis))
    else:
        return sys.maxsize


def dfs(vals, idx):
    global s_len, s_points, answer, m
    t_val = copy.deepcopy(vals)

    if len(vals) == m:
        answer = min(answer, bfs(vals))
        return

    if idx >= s_len:
        return

    dfs(vals, idx + 1)
    t_val.append(s_points[idx])
    dfs(t_val, idx + 1)


n, m = map(int, input().split())
lab = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(n) ]
s_points = []
empty = 0
answer = sys.maxsize
for i in range(n):
    for j in range(n):
        if lab[i][j] != 1:
            empty += 1
        if lab[i][j] == 2:
            s_points.append((i, j))

s_len = len(s_points)
dfs([], 0)

if answer == sys.maxsize:
    print(-1)
else:
    print(answer)